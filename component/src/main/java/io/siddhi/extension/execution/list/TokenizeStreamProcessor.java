/*
 * Copyright (c) 2019, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package io.siddhi.extension.execution.list;

import io.siddhi.annotation.Example;
import io.siddhi.annotation.Extension;
import io.siddhi.annotation.Parameter;
import io.siddhi.annotation.ParameterOverload;
import io.siddhi.annotation.ReturnAttribute;
import io.siddhi.annotation.util.DataType;
import io.siddhi.core.config.SiddhiQueryContext;
import io.siddhi.core.event.ComplexEventChunk;
import io.siddhi.core.event.stream.MetaStreamEvent;
import io.siddhi.core.event.stream.StreamEvent;
import io.siddhi.core.event.stream.StreamEventCloner;
import io.siddhi.core.event.stream.holder.StreamEventClonerHolder;
import io.siddhi.core.event.stream.populater.ComplexEventPopulater;
import io.siddhi.core.exception.SiddhiAppRuntimeException;
import io.siddhi.core.executor.ExpressionExecutor;
import io.siddhi.core.query.processor.ProcessingMode;
import io.siddhi.core.query.processor.Processor;
import io.siddhi.core.query.processor.stream.StreamProcessor;
import io.siddhi.core.util.config.ConfigReader;
import io.siddhi.core.util.snapshot.state.State;
import io.siddhi.core.util.snapshot.state.StateFactory;
import io.siddhi.query.api.definition.AbstractDefinition;
import io.siddhi.query.api.definition.Attribute;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Implementation class for list:tokenize().
 */
@Extension(
        namespace = "list",
        name = "tokenize",
        description = "Tokenize the list and return each key, value as new attributes in events",
        parameters = {
                @Parameter(
                        name = "list",
                        description = "Array list which needs to be tokenized",
                        type = DataType.OBJECT,
                        dynamic = true
                )
        },
        parameterOverloads = {
                @ParameterOverload(parameterNames = {"list"}),
                @ParameterOverload(parameterNames = {"list", "..."})
        },
        returnAttributes = {
                @ReturnAttribute(
                        name = "index",
                        description = "Index of an entry consisted in the list",
                        type = {DataType.INT}
                ),
                @ReturnAttribute(
                        name = "value",
                        description = "Value of an entry consisted in the list",
                        type = {DataType.OBJECT}
                )
        },
        examples = {
                @Example(
                        syntax = "list:tokenize(customList)",
                        description = "If custom list contains ('WSO2', 'IBM', 'XYZ') elements, " +
                                "then tokenize function will return 3 events with value attributes WSO2, IBM and XYZ " +
                                "respectively."
                )
        }
)
public class TokenizeStreamProcessor extends StreamProcessor<State> {
   private List<Attribute> attributesList = new ArrayList<>(2);
   private ExpressionExecutor[] expressionExecutor;
   private int numList;

    @Override
    protected StateFactory<State> init(MetaStreamEvent metaStreamEvent, AbstractDefinition abstractDefinition,
                                       ExpressionExecutor[] expressionExecutors, ConfigReader configReader,
                                       StreamEventClonerHolder streamEventClonerHolder,
                                       boolean outputExpectsExpiredEvents, boolean findToBeExecuted,
                                       SiddhiQueryContext siddhiQueryContext) {
        this.expressionExecutor = expressionExecutors;
        this.numList = expressionExecutors.length;

        this.attributesList.add(new Attribute("index", Attribute.Type.INT));
        this.attributesList.add(new Attribute("value", Attribute.Type.OBJECT));
        return null;
    }

    @Override
    protected void process(ComplexEventChunk<StreamEvent> complexEventChunk, Processor processor,
                           StreamEventCloner streamEventCloner, ComplexEventPopulater complexEventPopulater,
                           State state) {
        while (complexEventChunk.hasNext()) {
            StreamEvent event = complexEventChunk.next();

            List<List<Object>> dataList = new ArrayList<>(this.expressionExecutor.length);
            for (int i = 0; i < this.expressionExecutor.length; i++) {
                Object listObject = this.expressionExecutor[i].execute(event);
                if (!(listObject instanceof List)) {
                    throw new SiddhiAppRuntimeException("Attribute number, '" + (i + 1) + "' must be of type " +
                            "java.util.List, but found " + listObject.getClass().getCanonicalName());
                }
                dataList.add((List) listObject);
            }

            int maxIndex = 0;
            for (List<Object> dataElement : dataList) {
                if (dataElement.size() > maxIndex) {
                    maxIndex = dataElement.size();
                }
            }

            for (int i = 0; i < maxIndex; i++) {
                StreamEvent clonedEvent = streamEventCloner.copyStreamEvent(event);
                Object[] data;
                if (this.numList == 1) {
                    data = new Object[]{i, dataList.get(0).get(i)};
                } else {
                    List<Object> value = new LinkedList<>();
                    for (int j = 0; j < this.numList; j++) {
                        if (dataList.get(j).size() > i) {
                            value.add(dataList.get(j).get(i));
                        } else {
                            value.add(null);
                        }
                    }
                    data = new Object[]{i, value};
                }

                complexEventPopulater.populateComplexEvent(clonedEvent, data);
                complexEventChunk.insertBeforeCurrent(clonedEvent);
            }
            complexEventChunk.remove();
        }
        nextProcessor.process(complexEventChunk);
    }

    @Override
    public List<Attribute> getReturnAttributes() {
        return this.attributesList;
    }

    @Override
    public ProcessingMode getProcessingMode() {
        return ProcessingMode.SLIDE;
    }

    @Override
    public void start() {
    }

    @Override
    public void stop() {
    }
}

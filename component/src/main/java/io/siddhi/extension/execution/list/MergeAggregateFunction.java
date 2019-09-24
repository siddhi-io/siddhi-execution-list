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
import io.siddhi.core.exception.SiddhiAppRuntimeException;
import io.siddhi.core.executor.ExpressionExecutor;
import io.siddhi.core.query.processor.ProcessingMode;
import io.siddhi.core.query.selector.attribute.aggregator.AttributeAggregatorExecutor;
import io.siddhi.core.util.config.ConfigReader;
import io.siddhi.core.util.snapshot.state.State;
import io.siddhi.core.util.snapshot.state.StateFactory;
import io.siddhi.query.api.definition.Attribute;

import java.util.LinkedList;
import java.util.List;

/**
 * Implementation class for list:merge().
 */
@Extension(
        namespace = "list",
        name = "merge",
        description = "Collects multiple lists to merge as a single list.",
        parameters = {
                @Parameter(
                        name = "list",
                        description = "List to be merged",
                        type = DataType.OBJECT,
                        dynamic = true
                ),
                @Parameter(
                        name = "is.distinct",
                        description = "Whether to return list with distinct values",
                        type = DataType.BOOL,
                        optional = true,
                        defaultValue = "false",
                        dynamic = true
                )
        },
        parameterOverloads = {
                @ParameterOverload(parameterNames = "list"),
                @ParameterOverload(parameterNames = {"list", "is.distinct"})
        },
        returnAttributes = {
                @ReturnAttribute(
                        description = "List containing all the collected values.",
                        type = DataType.OBJECT
                )
        },
        examples = {
                @Example(
                        syntax = "from StockStream#window.lengthBatch(2)\n" +
                                "select list:merge(list) as stockSymbols\n" +
                                "insert into OutputStream;",
                        description = "For the window expiry of 2 events, the merge() function will collect " +
                                "attributes of `list` and merge them to a single list, returned as stockSymbols."
                )
        }
)
public class MergeAggregateFunction extends AttributeAggregatorExecutor<State> {
    @Override
    protected StateFactory<State> init(ExpressionExecutor[] attributeExpressionExecutors, ProcessingMode processingMode,
                                       boolean outputExpectsExpiredEvents, ConfigReader configReader,
                                       SiddhiQueryContext siddhiQueryContext) {
        return ListState::new;
    }

    @Override
    public Object processAdd(Object data, State state) {
        if (data instanceof List) {
            ((ListState) state).dataList.addAll((List<Object>) data);
            return ((ListState) state).getClonedDataList();
        }
        throw new SiddhiAppRuntimeException("First attribute value must be of type java.util.List, but found '" +
                data.getClass().getCanonicalName() + "'.");
    }

    @Override
    public Object processAdd(Object[] data, State state) {
        if (data[0] instanceof List) {
            ListState listState = (ListState) state;
            List<Object> givenList = (List<Object>) data[0];
            if (((boolean) data[1])) {
                for (Object element : givenList) {
                    if (!listState.dataList.contains(element)) {
                        listState.dataList.add(element);
                    }
                }
                return listState.getClonedDataList();
            }
            listState.dataList.addAll(givenList);
            return listState.getClonedDataList();
        }
        throw new SiddhiAppRuntimeException("First attribute value must be of type java.util.List, but found '" +
                data[0].getClass().getCanonicalName() + "'.");
    }

    @Override
    public Object processRemove(Object data, State state) {
        if (data instanceof List) {
            ((ListState) state).dataList.removeAll((List<Object>) data);
            return ((ListState) state).getClonedDataList();
        }
        throw new SiddhiAppRuntimeException("First attribute value must be of type java.util.List, but found '" +
                data.getClass().getCanonicalName() + "'.");
    }

    @Override
    public Object processRemove(Object[] data, State state) {
        if (data[0] instanceof List) {
            ((ListState) state).dataList.removeAll((List<Object>) data[0]);
            return ((ListState) state).getClonedDataList();
        }
        throw new SiddhiAppRuntimeException("First attribute value must be of type java.util.List, but found '" +
                data.getClass().getCanonicalName() + "'.");
    }

    @Override
    public Attribute.Type getReturnType() {
        return Attribute.Type.OBJECT;
    }

    @Override
    public Object reset(State state) {
        ((ListState) state).dataList = new LinkedList<>();
        return ((ListState) state).getClonedDataList();
    }
}

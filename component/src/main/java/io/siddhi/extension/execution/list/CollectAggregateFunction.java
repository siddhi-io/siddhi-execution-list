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
import io.siddhi.core.executor.ExpressionExecutor;
import io.siddhi.core.query.processor.ProcessingMode;
import io.siddhi.core.query.selector.attribute.aggregator.AttributeAggregatorExecutor;
import io.siddhi.core.util.config.ConfigReader;
import io.siddhi.core.util.snapshot.state.State;
import io.siddhi.core.util.snapshot.state.StateFactory;
import io.siddhi.query.api.definition.Attribute;

import java.util.LinkedList;

/**
 * Implementation class for list:collect().
 */
@Extension(
        namespace = "list",
        name = "collect",
        description = "Collects multiple values to construct a list.",
        parameters = {
                @Parameter(
                        name = "value",
                        description = "Value of the list element",
                        type = {DataType.OBJECT, DataType.INT, DataType.LONG, DataType.FLOAT, DataType.DOUBLE,
                                DataType.BOOL, DataType.STRING},
                        dynamic = true
                ),
                @Parameter(
                        name = "is.distinct",
                        description = "If `true` only distinct elements are collected",
                        type = DataType.BOOL,
                        dynamic = true,
                        optional = true,
                        defaultValue = "false"
                )
        },
        parameterOverloads = {
                @ParameterOverload(parameterNames = {"value"}),
                @ParameterOverload(parameterNames = {"value", "is.distinct"})
        },
        returnAttributes = {
                @ReturnAttribute(
                        description = "List containing all the values.",
                        type = DataType.OBJECT
                )
        },
        examples = {
                @Example(
                        syntax = "from StockStream#window.lengthBatch(10)\n" +
                                 "select list:collect(symbol) as stockSymbols\n" +
                                 "insert into OutputStream;",
                        description = "For the window expiry of 10 events, the collect() function will collect " +
                                "attributes of `symbol` to a single list and return as stockSymbols."
                )
        }
)
public class CollectAggregateFunction extends AttributeAggregatorExecutor<State> {

    @Override
    protected StateFactory<State> init(ExpressionExecutor[] expressionExecutors, ProcessingMode processingMode,
                                       boolean outputExpectsExpiredEvents, ConfigReader configReader,
                                       SiddhiQueryContext siddhiQueryContext) {
        return ListState::new;
    }

    @Override
    public Object processAdd(Object object, State state) {
        ((ListState) state).dataList.add(object);
        return ((ListState) state).getClonedDataList();
    }

    @Override
    public Object processAdd(Object[] objects, State state) {
        ListState listState = (ListState) state;
        if (((boolean) objects[1])) {
            if (!listState.dataList.contains(objects[0])) {
                listState.dataList.add(objects[0]);
            }
        } else {
            (listState).dataList.add(objects[0]);
        }
        return (listState).getClonedDataList();
    }

    @Override
    public Object processRemove(Object object, State state) {
        ((ListState) state).dataList.remove(object);
        return ((ListState) state).getClonedDataList();
    }

    @Override
    public Object processRemove(Object[] objects, State state) {
        ((ListState) state).dataList.remove(objects[0]);
        return ((ListState) state).getClonedDataList();
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

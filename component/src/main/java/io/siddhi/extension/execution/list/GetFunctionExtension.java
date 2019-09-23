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
import io.siddhi.core.executor.function.FunctionExecutor;
import io.siddhi.core.util.config.ConfigReader;
import io.siddhi.core.util.snapshot.state.State;
import io.siddhi.core.util.snapshot.state.StateFactory;
import io.siddhi.query.api.definition.Attribute;

import java.util.List;

/**
 * This is a implementation of list:get().
 */
@Extension(
        name = "get",
        namespace = "list",
        description = "Function returns the value at the specific index, null if index is out of range.",
        parameters = {
                @Parameter(name = "list",
                        description = "Attribute containing the list",
                        type = {DataType.OBJECT},
                        dynamic = true
                ),
                @Parameter(name = "index",
                        description = "Index of the element",
                        type = {DataType.INT},
                        dynamic = true
                )
        },
        returnAttributes = {
                @ReturnAttribute(
                        description = "Values of the element in the given index",
                        type = {DataType.INT, DataType.LONG, DataType.FLOAT, DataType.DOUBLE,
                                DataType.BOOL, DataType.STRING}
                )
        },
        parameterOverloads = {
                @ParameterOverload(parameterNames = {"list", "index"})
        },
        examples = {
                @Example(
                        syntax = "list:get(stockSymbols, 1)",
                        description = "This returns the element in the 1st index in the stockSymbols list."
                )
        }
)
public class GetFunctionExtension extends FunctionExecutor<State> {

    @Override
    protected StateFactory<State> init(ExpressionExecutor[] expressionExecutors, ConfigReader configReader,
                                SiddhiQueryContext siddhiQueryContext) {
        return null;
    }

    @Override
    protected Object execute(Object[] data, State state) {
        List list;
        if (data[0] instanceof List) {
            list = (List) data[0];
        } else {
            throw new SiddhiAppRuntimeException("First attribute value must be of type java.util.List, but found '" +
                    data[0].getClass().getCanonicalName() + "'.");
        }
        try {
            return list.get((int) data[1]);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    protected Object execute(Object data, State state) {
        // Cannot be invoked
        return null;
    }

    @Override
    public Attribute.Type getReturnType() {
        return Attribute.Type.OBJECT;
    }

}

/*
 * Copyright (c)  2019, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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
 * add(LinkedList , value) implementation.
 */
@Extension(
        name = "add",
        namespace = "list",
        description = "Function returns the updated list after adding the given value.",
        parameters = {
                @Parameter(name = "list",
                        description = "The list to which the value should be added.",
                        type = DataType.OBJECT,
                        dynamic = true
                ),
                @Parameter(name = "value",
                        description = "The value to be added.",
                        type = {DataType.OBJECT, DataType.INT, DataType.LONG, DataType.FLOAT, DataType.DOUBLE,
                                DataType.BOOL, DataType.STRING},
                        dynamic = true
                ),
                @Parameter(name = "index",
                        description = "The index in which the value should to be added.",
                        type = {DataType.INT},
                        dynamic = true,
                        optional = true,
                        defaultValue = "last"
                )
        },
        parameterOverloads = {
                @ParameterOverload(parameterNames = {"list", "value"}),
                @ParameterOverload(parameterNames = {"list", "value", "index"})
        },
        returnAttributes =
                @ReturnAttribute(
                        description = "Returns the updated list with the value added.",
                        type = DataType.OBJECT
                ),
        examples = {
                @Example(
                        syntax = "list:add(stockSymbols, 'IBM')",
                        description = "Function returns the updated list after adding the value `IBM` " +
                                "in the last index."
                ),
                @Example(
                        syntax = "list:add(stockSymbols, 'IBM', 0)",
                        description = "Function returns the updated list after adding the value " +
                                "`IBM` in the 0th index`.")
        }
)
public class AddFunctionExtension extends FunctionExecutor<State> {
    private Attribute.Type returnType = Attribute.Type.OBJECT;

    @Override
    protected StateFactory<State> init(ExpressionExecutor[] attributeExpressionExecutors,
                                ConfigReader configReader,
                                SiddhiQueryContext siddhiQueryContext) {
        return null;
    }

    @Override
    protected Object execute(Object[] data, State state) {
        List<Object> list;
        if (data[0] instanceof List) {
            list = (List) data[0];
        } else {
            throw new SiddhiAppRuntimeException("First attribute value must be of type java.util.List, but found '" +
                    data[0].getClass().getCanonicalName() + "'.");
        }
        if (data.length == 2) {
            list.add(data[1]);
        } else {
            list.add(((int) data[2]), data[1]);
        }
        return list;
    }

    @Override
    protected Object execute(Object data, State state) {
        //Since the list:add() function takes in 3 parameters, this method does not get called. Hence, not implemented.
        return null;
    }

    @Override
    public Attribute.Type getReturnType() {
        return returnType;
    }
}

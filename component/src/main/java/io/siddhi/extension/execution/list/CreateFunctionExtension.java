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
import io.siddhi.core.executor.function.FunctionExecutor;
import io.siddhi.core.util.config.ConfigReader;
import io.siddhi.core.util.snapshot.state.State;
import io.siddhi.core.util.snapshot.state.StateFactory;
import io.siddhi.query.api.definition.Attribute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This is a implementation of list:create().
 */
@Extension(
        name = "create",
        namespace = "list",
        description = "Function creates a list containing all values provided.",
        parameters = {
                @Parameter(name = "value1",
                        description = "Value 1",
                        type = {DataType.INT, DataType.LONG, DataType.FLOAT, DataType.DOUBLE,
                                DataType.BOOL, DataType.STRING},
                        dynamic = true,
                        optional = true,
                        defaultValue = "<EMPTY_STRING>"
                ),
        },
        returnAttributes = {
                @ReturnAttribute(
                        description = "List of values given in the function parameter",
                        type = DataType.OBJECT
                )
        },
        parameterOverloads = {
                @ParameterOverload(),
                @ParameterOverload(parameterNames = {"value1"}),
                @ParameterOverload(parameterNames = {"value1", "..."}),
        },
        examples = {
                @Example(
                        syntax = "list:create(1, 2, 3, 4, 5, 6)",
                        description = "This returns a list with values `1`, `2`, `3`, `4`, `5` and `6`."
                ),
                @Example(
                        syntax = "list:create()",
                        description = "This returns an empty list.")
        }
)

public class CreateFunctionExtension extends FunctionExecutor<State> {

    @Override
    protected StateFactory<State> init(ExpressionExecutor[] expressionExecutors, ConfigReader configReader,
                                SiddhiQueryContext siddhiQueryContext) {
        return null;
    }

    @Override
    protected Object execute(Object[] data, State state) {
        return new ArrayList<>(Arrays.asList(data));
    }

    @Override
    protected Object execute(Object data, State state) {
        if (data == null) {
            return new ArrayList<>();
        }
        List<Object> list = new ArrayList<>();
        list.add(data);
        return list;
    }

    @Override
    public Attribute.Type getReturnType() {
        return Attribute.Type.OBJECT;
    }

}

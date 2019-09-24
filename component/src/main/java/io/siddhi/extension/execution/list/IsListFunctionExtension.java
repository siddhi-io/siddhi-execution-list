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
import io.siddhi.core.executor.ExpressionExecutor;
import io.siddhi.core.executor.function.FunctionExecutor;
import io.siddhi.core.util.config.ConfigReader;
import io.siddhi.core.util.snapshot.state.State;
import io.siddhi.core.util.snapshot.state.StateFactory;
import io.siddhi.query.api.definition.Attribute;

import java.util.List;

/**
 * isList(Object) implementation.
 */
@Extension(
        name = "isList",
        namespace = "list",
        description = "Function checks if the object is type of a list.",
        parameters = {
                @Parameter(name = "arg",
                        description = "The argument the need to be determined whether it's a list or not.",
                        type = {DataType.OBJECT, DataType.INT, DataType.LONG, DataType.FLOAT, DataType.DOUBLE,
                                DataType.BOOL, DataType.STRING},
                        dynamic = true
                )
        },
        parameterOverloads = {
                @ParameterOverload(parameterNames = {"arg"})
        },
        returnAttributes = @ReturnAttribute(
                description = "Returns `true` if the arg is a list (`java.util.List`) and `false` if otherwise.",
                type = DataType.BOOL),
        examples = @Example(
                syntax = "list:isList(stockSymbols)",
                description = "Returns 'true' if the stockSymbols is and an instance of `java.util.List` else " +
                        "it returns `false`.")
)
public class IsListFunctionExtension extends FunctionExecutor<State> {
    private Attribute.Type returnType = Attribute.Type.BOOL;

    @Override
    protected StateFactory<State> init(ExpressionExecutor[] attributeExpressionExecutors,
                                ConfigReader configReader, SiddhiQueryContext siddhiQueryContext) {
        return null;
    }

    @Override
    protected Object execute(Object[] data, State state) {
        return null;
    }

    @Override
    protected Boolean execute(Object data, State state) {
        if (data instanceof List) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public Attribute.Type getReturnType() {
        return returnType;
    }
}

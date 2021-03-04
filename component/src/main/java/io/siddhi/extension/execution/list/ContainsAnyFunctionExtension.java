/*
 * Copyright (c) 2021, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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

import java.util.Collections;
import java.util.List;

/**
 * containsAny(Object list, Object givenList) implementation.
 */
@Extension(
        name = "containsAny",
        namespace = "list",
        description = "Function checks whether the two specified lists have any common elements.",
        parameters = {
                @Parameter(name = "list",
                        description = "The list that needs to be checked on whether it contains any of the " +
                                "values or not.",
                        type = {DataType.OBJECT},
                        dynamic = true
                ),
                @Parameter(name = "given.list",
                        description = "The list which contains any of the values to be checked.",
                        type = {DataType.OBJECT},
                        dynamic = true
                )
        },
        parameterOverloads = {
                @ParameterOverload(parameterNames = {"list", "given.list"})
        },
        returnAttributes =
        @ReturnAttribute(
                description = "Returns `true` if the list contains any of the values and `false` if otherwise.",
                type = DataType.BOOL
        ),
        examples = @Example(
                syntax = "list:containsAny(stockSymbols, latestStockSymbols)",
                description = "Returns 'true' if the stockSymbols list contains any value in latestStockSymbols " +
                        "else it returns `false`.")
)
public class ContainsAnyFunctionExtension extends FunctionExecutor<State> {
    private Attribute.Type returnType = Attribute.Type.BOOL;

    @Override
    protected StateFactory<State> init(ExpressionExecutor[] attributeExpressionExecutors,
                                       ConfigReader configReader,
                                       SiddhiQueryContext siddhiQueryContext) {
        return null;
    }

    @Override
    protected Object execute(Object[] data, State state) {
        if (data[0] instanceof List) {
            if (data[1] instanceof List) {
                return !Collections.disjoint((List) data[0], (List) data[1]);
            }
            throw new SiddhiAppRuntimeException("Second attribute value must be of type java.util.List, but found '" +
                    data[1].getClass().getCanonicalName() + "'.");
        }
        throw new SiddhiAppRuntimeException("First attribute value must be of type java.util.List, but found '" +
                data[0].getClass().getCanonicalName() + "'.");
    }

    @Override
    protected Object execute(Object data, State state) {
        return null;
    }

    @Override
    public Attribute.Type getReturnType() {
        return returnType;
    }
}

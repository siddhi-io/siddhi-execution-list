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
 * clear(LinkedList) implementation.
 */
@Extension(
        name = "clear",
        namespace = "list",
        description = "Function returns the cleared list. ",
        parameters = {
                @Parameter(name = "list",
                        description = "The list which needs to be cleared",
                        type = DataType.OBJECT,
                        dynamic = true
                )
        },
        parameterOverloads = {
                @ParameterOverload(parameterNames = {"list"})
        },
        returnAttributes =
                @ReturnAttribute(
                        description = "Returns the cleared list.",
                        type = DataType.OBJECT
                ),
        examples =
                @Example(
                        syntax = "list:clear(stockDetails)",
                        description = "Returns an empty list."
        )
)
public class ClearFunctionExtension extends FunctionExecutor<State> {

    private static final long serialVersionUID = -1969315779590868090L;
    private Attribute.Type returnType = Attribute.Type.OBJECT;

    @Override
    protected StateFactory<State> init(ExpressionExecutor[] attributeExpressionExecutors,
                                ConfigReader configReader,
                                SiddhiQueryContext siddhiQueryContext) {
        return null;
    }

    @Override
    protected Object execute(Object[] data, State state) {
        //Since the list:clear() function takes in 1 parameter, this method does not get called. Hence, not implemented.
        return null;
    }

    @Override
    protected Object execute(Object data, State state) {
        if (data instanceof List) {
            List<Object> list = (List<Object>) data;
            list.clear();
            return list;
        }
        throw new SiddhiAppRuntimeException("First attribute value must be of type java.util.List, but found '" +
                data.getClass().getCanonicalName() + "'");
    }

    @Override
    public Attribute.Type getReturnType() {
        return returnType;
    }
}

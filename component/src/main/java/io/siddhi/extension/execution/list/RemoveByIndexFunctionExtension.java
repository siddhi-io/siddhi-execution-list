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
 * removeByIndex(ArrayList , index)
 * Returns the updated array list
 * Accept Type(s): (ArrayList , ValidAttributeType)
 * Return Type(s): ArrayList
 */
@Extension(
        name = "removeByIndex",
        namespace = "list",
        description = "Function returns the updated list after removing the element with the specified index.",
        parameters = {
                @Parameter(name = "list",
                        description = "The list that needs to be updated.",
                        type = DataType.OBJECT,
                        dynamic = true
                ),
                @Parameter(name = "index",
                        description = "The index of the element that needs to removed.",
                        type = {DataType.INT},
                        dynamic = true
                )
        },
        parameterOverloads = {
                @ParameterOverload(parameterNames = {"list", "index"})
        },
        returnAttributes = @ReturnAttribute(
                description = "Returns the updated list after removing the value at the index.",
                type = DataType.OBJECT),
        examples = @Example(
                syntax = "list:removeByIndex(stockSymbols, 0)",
                description = "This returns the updated list, stockSymbols after removing value at 0 th index."
        )
)
public class RemoveByIndexFunctionExtension extends FunctionExecutor<State> {
    private Attribute.Type returnType = Attribute.Type.OBJECT;

    @Override
    protected StateFactory<State> init(ExpressionExecutor[] attributeExpressionExecutors,
                                ConfigReader configReader,
                                SiddhiQueryContext siddhiQueryContext) {
        return null;
    }

    @Override
    protected Object execute(Object[] data, State state) {
        if (data[0] instanceof List) {
            List<Object> list = (List<Object>) data[0];
            list.remove(((int) data[1]));
            return list;
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

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
 * removeAll(LinkedList , LinkedList)
 * Returns the updated array list
 * Accept Type(s): (LinkedList , LinkedList)
 * Return Type(s): LinkedList
 */
@Extension(
        name = "retainAll",
        namespace = "list",
        description = "Function returns the updated list after retaining all the elements in the specified list.",
        parameters = {
                @Parameter(name = "list",
                        description = "The list that needs to be updated.",
                        type = DataType.OBJECT,
                        dynamic = true
                ),
                @Parameter(name = "given.list",
                        description = "The list with all the elements that needs to reatined.",
                        type = {DataType.OBJECT},
                        dynamic = true
                )
        },
        parameterOverloads = {
                @ParameterOverload(parameterNames = {"list", "given.list"})
        },
        returnAttributes = @ReturnAttribute(
                description = "Returns the updated list after retaining all the values in the given list.",
                type = DataType.OBJECT),
        examples = @Example(
                syntax = "list:retainAll(stockSymbols, latestStockSymbols)",
                description = "This returns the updated list, stockSymbols after retaining all the values " +
                        "in latestStockSymbols."
        )
)
public class RetainAllFunctionExtension extends FunctionExecutor<State> {
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
            if (data[1] instanceof List) {
                List<Object> list1 = (List<Object>) data[0];
                List<Object> list2 = (List<Object>) data[1];
                list1.retainAll(list2);
                return list1;
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

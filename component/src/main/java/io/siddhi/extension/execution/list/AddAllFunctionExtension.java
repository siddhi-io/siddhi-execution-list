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
 * addAll(list , list) implementation.
 */
@Extension(
        name = "addAll",
        namespace = "list",
        description = "Function returns the updated list after adding all the values from the given list.",
        parameters = {
                @Parameter(name = "to.list",
                        description = "The list into which the values need to copied.",
                        type = DataType.OBJECT,
                        dynamic = true
                ),
                @Parameter(name = "from.list",
                        description = "The list from which the values are copied.",
                        type = DataType.OBJECT,
                        dynamic = true
                ),
                @Parameter(name = "is.distinct",
                        description = "If `true` returns list with distinct values",
                        type = DataType.BOOL,
                        optional = true,
                        defaultValue = "false",
                        dynamic = true
                )
        },
        parameterOverloads = {
                @ParameterOverload(parameterNames = {"to.list", "from.list"}),
                @ParameterOverload(parameterNames = {"to.list", "from.list", "is.distinct"})
        },
        returnAttributes =
                @ReturnAttribute(
                        description = "Returns the updated list after adding all the values.",
                        type = DataType.OBJECT
                ),
        examples = {
                @Example(
                        syntax = "list:putAll(toList, fromList)",
                        description =
                                "If `toList` contains values ('IBM', 'WSO2), and if `fromList` " +
                                "contains values ('IBM', 'XYZ') then the function returns updated `toList` " +
                                "with values ('IBM', 'WSO2', 'IBM', 'XYZ')."
                        ),
                @Example(
                        syntax = "list:putAll(toList, fromList, true)",
                        description =
                                "If `toList` contains values ('IBM', 'WSO2), and if `fromList` " +
                                "contains values ('IBM', 'XYZ') then the function returns updated `toList` " +
                                "with values ('IBM', 'WSO2', 'XYZ')."
                        )
                }
)
public class AddAllFunctionExtension extends FunctionExecutor<State> {

    private static final long serialVersionUID = -5832032635507353427L;
    private Attribute.Type returnType = Attribute.Type.OBJECT;
    private boolean isDistinctCheck = false;

    @Override
    protected StateFactory<State> init(ExpressionExecutor[] attributeExpressionExecutors,
                                ConfigReader configReader, SiddhiQueryContext siddhiQueryContext) {
        this.isDistinctCheck = attributeExpressionExecutors.length == 3;
        return null;
    }

    @Override
    protected Object execute(Object[] data, State state) {
        if (!(data[0] instanceof List)) {
            throw new SiddhiAppRuntimeException("First attribute value must be of type java.util.List, but found '" +
                    data[0].getClass().getCanonicalName() + "'.");
        }
        if (!(data[1] instanceof List)) {
            throw new SiddhiAppRuntimeException("Second attribute value must be of type java.util.List, but found '" +
                    data[1].getClass().getCanonicalName() + "'.");
        }
        List<Object> list1 = (List<Object>) data[0];
        List<Object> list2 = (List<Object>) data[1];

        if (this.isDistinctCheck && (boolean) data[2]) {
            list2.forEach((element) -> {
                if (!list1.contains(element)) {
                    list1.add(element);
                }
            });
        } else {
            list1.addAll(list2);
        }
        return list1;
    }

    @Override
    protected Object execute(Object data, State state) {
        //Since the list:addAll() function takes in 2 parameters, this method does not get called.
        // Hence, not implemented.
        return null;
    }

    @Override
    public Attribute.Type getReturnType() {
        return returnType;
    }
}

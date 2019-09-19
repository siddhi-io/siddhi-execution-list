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

import java.util.ArrayList;
import java.util.List;

/**
 * addAll(arrayList , arrayList)
 * Returns the updated arrayList.
 * Accept Type(s): (arrayList , arrayList)
 * Return Type(s): arrayList
 */
@Extension(
        name = "addAll",
        namespace = "list",
        description = "Function returns the updated list after adding all the values from another list.",
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
                )
        },
        parameterOverloads = {
                @ParameterOverload(parameterNames = {"to.list", "from.list"})
        },
        returnAttributes = @ReturnAttribute(
                description = "Returns the updated list after adding all the values.",
                type = DataType.OBJECT),
        examples = @Example(
                syntax = "list:putAll(toList, fromList)",
                description = "If `toList` contains values ('IBM', 'WSO2), and if `fromList` contains values " +
                        "('IBM', 'XYZ') then the function returns updated `toList` with values " +
                        "('IBM', 'WSO2', 'IBM', 'XYZ').")
)
public class AddAllFunctionExtension extends FunctionExecutor<State> {
    private Attribute.Type returnType = Attribute.Type.OBJECT;

    @Override
    protected StateFactory<State> init(ExpressionExecutor[] attributeExpressionExecutors,
                                ConfigReader configReader, SiddhiQueryContext siddhiQueryContext) {
        return null;
    }

    @Override
    protected Object execute(Object[] data, State state) {
        if (!(data[0] instanceof List)) {
            throw new SiddhiAppRuntimeException("First attribute value must be of type java.util.List.");
        }
        if (!(data[1] instanceof List)) {
            throw new SiddhiAppRuntimeException("Second attribute value must be of type java.util.List.");
        }
        ArrayList<Object> list1 = (ArrayList<Object>) data[0];
        ArrayList<Object> list2 = (ArrayList<Object>) data[1];
        list1.addAll(list2);
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

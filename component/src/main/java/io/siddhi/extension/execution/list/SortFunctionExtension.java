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
import io.siddhi.core.exception.SiddhiAppCreationException;
import io.siddhi.core.exception.SiddhiAppRuntimeException;
import io.siddhi.core.executor.ConstantExpressionExecutor;
import io.siddhi.core.executor.ExpressionExecutor;
import io.siddhi.core.executor.function.FunctionExecutor;
import io.siddhi.core.util.config.ConfigReader;
import io.siddhi.core.util.snapshot.state.State;
import io.siddhi.core.util.snapshot.state.StateFactory;
import io.siddhi.query.api.definition.Attribute;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * Implementation of sort() function
 */
@Extension(
        name = "sort",
        namespace = "list",
        description = "Function returns lists sorted in ascending or descending order.",
        parameters = {
                @Parameter(name = "list",
                        description = "The list which should be sorted.",
                        type = DataType.OBJECT,
                        dynamic = true
                ),
                @Parameter(name = "order",
                        description = "Order in which the list needs to be sorted (ASC/DESC/REV).",
                        type = {DataType.STRING},
                        optional = true,
                        defaultValue = "REV"
                )
        },
        parameterOverloads = {
                @ParameterOverload(parameterNames = {"list"}),
                @ParameterOverload(parameterNames = {"list", "order"})
        },
        returnAttributes = @ReturnAttribute(
                description = "Returns the sorted list.",
                type = DataType.OBJECT),
        examples = {
                @Example(
                        syntax = "list:sort(stockSymbols)",
                        description = "Function returns the sorted list in ascending order."
                ),
                @Example(
                        syntax = "list:sort(stockSymbols, 'DESC')",
                        description = "Function returns the sorted list in descending  order.")
        }
)
public class SortFunctionExtension extends FunctionExecutor<State> {
    private Attribute.Type returnType = Attribute.Type.OBJECT;
    private SortType sortType = SortType.ASC;

    @Override
    protected StateFactory<State> init(ExpressionExecutor[] attributeExpressionExecutors,
                                       ConfigReader configReader,
                                       SiddhiQueryContext siddhiQueryContext) {
        int length = attributeExpressionExecutors.length;
        if (length == 2) {
            String order = ((ConstantExpressionExecutor) attributeExpressionExecutors[1]).getValue().toString();
            switch (order.toUpperCase(Locale.ENGLISH)) {
                case "ASC":
                    sortType = SortType.ASC;
                    break;
                case "DESC":
                    sortType = SortType.DESC;
                    break;
                case "REV":
                    sortType = SortType.REV;
                    break;
                default:
                    throw new SiddhiAppCreationException("Sort types can be of types 'ASC/DESC/REV', however, " +
                            "found '" + order + "'.");
            }
        }
        return null;
    }

    @Override
    protected Object execute(Object[] data, State state) {

        if (!(data[0] instanceof List)) {
            throw new SiddhiAppRuntimeException("First attribute needs be an instance of java.util.List, but found '" +
                    data.getClass().getCanonicalName() + "'.");
        }

        List dataList = (List) data[0];
        switch (sortType) {
            case ASC:
                sortList(dataList);
                break;
            case DESC:
                sortList(dataList);
                Collections.reverse(dataList);
                break;
            case REV:
                Collections.reverse(dataList);
                break;
            default:
                // cannot happen
        }
        return dataList;
    }

    private void sortList(List dataList) {
        if (!dataList.isEmpty()) {
            Object firstElement = dataList.get(0);
            if (firstElement instanceof String) {
                ((List<String>) dataList).sort(String.CASE_INSENSITIVE_ORDER);
            } else if (firstElement instanceof Integer || firstElement instanceof Long ||
                                        firstElement instanceof Double || firstElement instanceof Float ) {
                dataList.sort(Comparator.naturalOrder());
            } else {
                throw new SiddhiAppRuntimeException("list:sort() only supported for lists of type <STRING|INT|" +
                        "LONG|DOUBLE|FLOAT> but found '" + firstElement.getClass().getCanonicalName() + "'.");
            }
        }
    }

    @Override
    protected Object execute(Object data, State state) {
        if (data instanceof List) {
            Collections.reverse(((List) data));
            return data;
        }
        throw new SiddhiAppRuntimeException("First attribute needs be an instance of java.util.List, but found '" +
                data.getClass().getCanonicalName() + "'.");
    }

    @Override
    public Attribute.Type getReturnType() {
        return returnType;
    }

    private enum SortType {
        REV, ASC, DESC
    }

}

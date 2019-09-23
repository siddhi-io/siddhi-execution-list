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

import io.siddhi.core.util.snapshot.state.State;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * State to maintained collected list
 */
public class ListState extends State {

    public List<Object> dataList = new ArrayList<>();

    @Override
    public boolean canDestroy() {
        return dataList.isEmpty();
    }

    @Override
    public Map<String, Object> snapshot() {
        Map<String, Object> state = new HashMap<>();
        state.put("dataList", dataList);
        return state;        }

    @Override
    public void restore(Map<String, Object> state) {
        dataList = ((ArrayList) state.get("dataList"));
    }

    public List<Object> getClonedDataList() {
        return new ArrayList<>(dataList);
    }
}

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

import io.siddhi.core.SiddhiAppRuntime;
import io.siddhi.core.SiddhiManager;
import io.siddhi.core.event.Event;
import io.siddhi.core.exception.SiddhiAppCreationException;
import io.siddhi.core.stream.output.StreamCallback;
import io.siddhi.core.util.EventPrinter;
import io.siddhi.core.util.SiddhiTestHelper;
import org.apache.log4j.Logger;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class AddAllFunctionExtensionTestCase {
    private static final Logger log = Logger.getLogger(AddAllFunctionExtensionTestCase.class);
    private AtomicInteger count = new AtomicInteger(0);
    private volatile boolean eventArrived;

    @BeforeMethod
    public void init() {
        count.set(0);
        eventArrived = false;
    }

    @Test
    public void testAddAllFunctionExtension() throws InterruptedException {
        log.info("AddAllFunctionExtension TestCase");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "\ndefine trigger startTrigger at 'start';";
        String query = ("@info(name = 'query1') from startTrigger "
                + "select list:create('one' , 'two' , 'three') as list1, "
                + "list:create('four' , 'five' , 'six') as list2 insert into tmpStream;"
                + "@info(name = 'query2') from tmpStream  select list:addAll(list1, list2)" +
                " as newList"
                + " insert into outputStream;"

        );

        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(
                inStreamDefinition + query);
        siddhiAppRuntime.addCallback("outputStream", new StreamCallback() {
            @Override
            public void receive(Event[] events) {
                EventPrinter.print(events);
                for (Event event : events) {
                    count.incrementAndGet();
                    ArrayList list = (ArrayList) event.getData(0);
                    AssertJUnit.assertEquals("one", list.get(0));
                    AssertJUnit.assertEquals("six", list.get(5));
                    eventArrived = true;
                }
            }
        });
        siddhiAppRuntime.start();
        SiddhiTestHelper.waitForEvents(100, 1, count, 60000);
        AssertJUnit.assertEquals(1, count.get());
        AssertJUnit.assertTrue(eventArrived);
        siddhiAppRuntime.shutdown();
    }

    @Test
    public void testAddAllFunctionExtension2() throws InterruptedException {
        log.info("AddAllFunctionExtension TestCase");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "\ndefine trigger startTrigger at 'start';";
        String query = ("@info(name = 'query1') from startTrigger "
                + "select list:create(1 ,  2 , 3 ) as list1, list:create(4 , 5 , 6 ) as list2, "
                + "list:create(7 ,  8 , 9) as list3 "
                + "insert into tmpStream;"
                + "@info(name = 'query2') from tmpStream  "
                + "select list:addAll(list:addAll(list1, list2), list3) as newList "
                + "insert into outputStream;"
        );

        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(
                inStreamDefinition + query);
        siddhiAppRuntime.addCallback("outputStream", new StreamCallback() {
            @Override
            public void receive(Event[] events) {
                for (Event event : events) {
                    EventPrinter.print(events);
                    count.incrementAndGet();
                    ArrayList list = (ArrayList) event.getData(0);
                    AssertJUnit.assertEquals(1, list.get(0));
                    AssertJUnit.assertEquals(6, list.get(5));
                    AssertJUnit.assertEquals(9, list.get(8));
                    eventArrived = true;
                }
            }
        });
        siddhiAppRuntime.start();
        SiddhiTestHelper.waitForEvents(100, 1, count, 60000);
        AssertJUnit.assertEquals(1, count.get());
        AssertJUnit.assertTrue(eventArrived);
        siddhiAppRuntime.shutdown();
    }

    @Test(expectedExceptions = SiddhiAppCreationException.class)
    public void testAddAllFunctionExtension1() {
        log.info("AddAllFunctionExtension TestCase with test attributeExpressionExecutors length");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "\ndefine trigger startTrigger at 'start';";
        String query = ("@info(name = 'query1') from startTrigger "
                + "select list:create() as list1, list:create() as list2, list:create() as list3 "
                + "insert into tmpStream;"
                + "@info(name = 'query2') from tmpStream select list1, list:addAll(list1, list2, list3) as newList "
                + " insert into outputStream;"
        );
        siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);
    }
}

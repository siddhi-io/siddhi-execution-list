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
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class SortFunctionExtensionTestCase {
    private static final Logger log = Logger.getLogger(SortFunctionExtensionTestCase.class);
    private AtomicInteger count = new AtomicInteger(0);
    private volatile boolean eventArrived;
    private List<Object[]> inEventsList;

    @BeforeMethod
    public void init() {
        count.set(0);
        eventArrived = false;
        inEventsList = new ArrayList<>();
    }

    @Test
    public void testSortFunctionExtension1() throws InterruptedException {
        log.info("Sort Function Extension TestCase");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "\ndefine trigger startTrigger at 'start';";
        String query = (
                " @info(name = 'query1') "
                + "from startTrigger "
                + "select list:create('one', 'two', 'three', 'four' , 'five' , 'six') as list "
                + "insert into tmpStream;"
                + "@info(name = 'query2') "
                + "from tmpStream "
                + "select list:sort(list, 'rev') as sortedList "
                + "insert into TokenizedStream; "
        );

        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(
                inStreamDefinition + query);
        siddhiAppRuntime.addCallback("TokenizedStream", new StreamCallback() {
            @Override
            public void receive(Event[] events) {
                EventPrinter.print(events);
                for (Event event : events) {
                    eventArrived = true;
                    count.incrementAndGet();
                    inEventsList.add(event.getData());
                    List data = (List) event.getData(0);
                    AssertJUnit.assertEquals(data.get(0), "six");
                    AssertJUnit.assertEquals(data.get(2), "four");
                    AssertJUnit.assertEquals(data.get(5), "one");


                }
            }
        });
        siddhiAppRuntime.start();
        SiddhiTestHelper.waitForEvents(100, 1, count, 60000);

        AssertJUnit.assertTrue(eventArrived);
        AssertJUnit.assertEquals(1, count.get());

        siddhiAppRuntime.shutdown();
    }

    @Test(expectedExceptions = SiddhiAppCreationException.class)
    public void testSortFunctionExtension2() {
        log.info("Sort Function Extension TestCase - More than one attribute");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "\ndefine trigger startTrigger at 'start';";
        String query = (
                "@info(name = 'query1') "
                        + "from startTrigger "
                        + "select list:create(1, 2,3) as list1 "
                        + "insert into tmpStream;"
                        + "@info(name = 'query2') "
                        + "from tmpStream#list:sort(list1, '2') "
                        + "select key, value "
                        + "insert into TokenizedStream; "

        );

        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(
                inStreamDefinition + query);
        siddhiAppRuntime.shutdown();
    }

    @Test(expectedExceptions = SiddhiAppCreationException.class)
    public void testSortFunctionExtension3() throws InterruptedException {
        log.info("Sort Function Extension TestCase - Use string object");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "\ndefine trigger startTrigger at 'start';";
        String query = (
                "@info(name = 'query1') "
                + "from startTrigger "
                + "select list:create('one', 'two', 'three', 'seven') as list1, " +
                        "list:create('four','five','six') as list2 "
                + "insert into tmpStream;"
                + "@info(name = 'query2') "
                + "from tmpStream#list:sort(list1, list2) "
                + "select index, value "
                + "insert into TokenizedStream; "

        );
        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(
                inStreamDefinition + query);
        siddhiAppRuntime.start();
        siddhiAppRuntime.shutdown();
    }

    @Test
    public void testSortFunctionExtension4() throws InterruptedException {
        log.info("Sort Function Extension TestCase");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "\ndefine trigger startTrigger at 'start';";
        String query = (
                " @info(name = 'query1') "
                        + "from startTrigger "
                        + "select list:create(1, 2, 3, 6, 5, 8) as list "
                        + "insert into tmpStream;"
                        + "@info(name = 'query2') "
                        + "from tmpStream "
                        + "select list:sort(list, 'asc') as sortedList "
                        + "insert into TokenizedStream; "
        );

        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(
                inStreamDefinition + query);
        siddhiAppRuntime.addCallback("TokenizedStream", new StreamCallback() {
            @Override
            public void receive(Event[] events) {
                EventPrinter.print(events);
                for (Event event : events) {
                    eventArrived = true;
                    count.incrementAndGet();
                    inEventsList.add(event.getData());
                    List data = (List) event.getData(0);
                    AssertJUnit.assertEquals(data.get(0), 1);
                    AssertJUnit.assertEquals(data.get(2), 3);
                    AssertJUnit.assertEquals(data.get(5), 8);


                }
            }
        });
        siddhiAppRuntime.start();
        SiddhiTestHelper.waitForEvents(100, 1, count, 60000);

        AssertJUnit.assertTrue(eventArrived);
        AssertJUnit.assertEquals(1, count.get());

        siddhiAppRuntime.shutdown();
    }

    @Test
    public void testSortFunctionExtension5() throws InterruptedException {
        log.info("Sort Function Extension TestCase");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "\ndefine trigger startTrigger at 'start';";
        String query = (
                " @info(name = 'query1') "
                        + "from startTrigger "
                        + "select list:create(1l, 2l, 3l, 6l, 5l, 8l) as list "
                        + "insert into tmpStream;"
                        + "@info(name = 'query2') "
                        + "from tmpStream "
                        + "select list:sort(list, 'asc') as sortedList "
                        + "insert into TokenizedStream; "
        );

        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(
                inStreamDefinition + query);
        siddhiAppRuntime.addCallback("TokenizedStream", new StreamCallback() {
            @Override
            public void receive(Event[] events) {
                EventPrinter.print(events);
                for (Event event : events) {
                    eventArrived = true;
                    count.incrementAndGet();
                    inEventsList.add(event.getData());
                    List data = (List) event.getData(0);
                    AssertJUnit.assertEquals(data.get(0), 1L);
                    AssertJUnit.assertEquals(data.get(2), 3L);
                    AssertJUnit.assertEquals(data.get(5), 8L);


                }
            }
        });
        siddhiAppRuntime.start();
        SiddhiTestHelper.waitForEvents(100, 1, count, 60000);

        AssertJUnit.assertTrue(eventArrived);
        AssertJUnit.assertEquals(1, count.get());

        siddhiAppRuntime.shutdown();
    }

    @Test
    public void testSortFunctionExtension6() throws InterruptedException {
        log.info("Sort Function Extension TestCase");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "\ndefine trigger startTrigger at 'start';";
        String query = (
                " @info(name = 'query1') "
                        + "from startTrigger "
                        + "select list:create(1d, 2d, 3d, 6d, 5d, 8d) as list "
                        + "insert into tmpStream;"
                        + "@info(name = 'query2') "
                        + "from tmpStream "
                        + "select list:sort(list, 'asc') as sortedList "
                        + "insert into TokenizedStream; "
        );

        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(
                inStreamDefinition + query);
        siddhiAppRuntime.addCallback("TokenizedStream", new StreamCallback() {
            @Override
            public void receive(Event[] events) {
                EventPrinter.print(events);
                for (Event event : events) {
                    eventArrived = true;
                    count.incrementAndGet();
                    inEventsList.add(event.getData());
                    List data = (List) event.getData(0);
                    AssertJUnit.assertEquals(data.get(0), 1.0);
                    AssertJUnit.assertEquals(data.get(2), 3.0);
                    AssertJUnit.assertEquals(data.get(5), 8.0);


                }
            }
        });
        siddhiAppRuntime.start();
        SiddhiTestHelper.waitForEvents(100, 1, count, 60000);

        AssertJUnit.assertTrue(eventArrived);
        AssertJUnit.assertEquals(1, count.get());

        siddhiAppRuntime.shutdown();
    }

    @Test
    public void testSortFunctionExtension7() throws InterruptedException {
        log.info("Sort Function Extension TestCase");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "\ndefine trigger startTrigger at 'start';";
        String query = (
                " @info(name = 'query1') "
                        + "from startTrigger "
                        + "select list:create(1.0f, 1.1f, 2.0f, 3.0f, 6.0f, 5.0f, 8.0f) as list "
                        + "insert into tmpStream;"
                        + "@info(name = 'query2') "
                        + "from tmpStream "
                        + "select list:sort(list, 'asc') as sortedList "
                        + "insert into TokenizedStream; "
        );

        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(
                inStreamDefinition + query);
        siddhiAppRuntime.addCallback("TokenizedStream", new StreamCallback() {
            @Override
            public void receive(Event[] events) {
                EventPrinter.print(events);
                for (Event event : events) {
                    eventArrived = true;
                    count.incrementAndGet();
                    inEventsList.add(event.getData());
                    List data = (List) event.getData(0);
                    AssertJUnit.assertEquals(data.get(0), 1.0f);
                    AssertJUnit.assertEquals(data.get(2), 2.0f);
                    AssertJUnit.assertEquals(data.get(5), 6.0f);


                }
            }
        });
        siddhiAppRuntime.start();
        SiddhiTestHelper.waitForEvents(100, 1, count, 60000);

        AssertJUnit.assertTrue(eventArrived);
        AssertJUnit.assertEquals(1, count.get());

        siddhiAppRuntime.shutdown();
    }
}

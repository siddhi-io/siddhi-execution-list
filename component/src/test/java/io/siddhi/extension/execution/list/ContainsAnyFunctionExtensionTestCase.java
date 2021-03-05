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

import io.siddhi.core.SiddhiAppRuntime;
import io.siddhi.core.SiddhiManager;
import io.siddhi.core.event.Event;
import io.siddhi.core.exception.SiddhiAppCreationException;
import io.siddhi.core.stream.input.InputHandler;
import io.siddhi.core.stream.output.StreamCallback;
import io.siddhi.core.util.EventPrinter;
import io.siddhi.core.util.SiddhiTestHelper;
import org.apache.log4j.Logger;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Testcases for ContainsAnyFunctionExtension.
 */
public class ContainsAnyFunctionExtensionTestCase {
    private static final Logger log = Logger.getLogger(ContainsAllFunctionExtensionTestCase.class);
    private AtomicInteger count = new AtomicInteger(0);
    private volatile boolean eventArrived;

    @BeforeMethod
    public void init() {
        count.set(0);
        eventArrived = false;
    }

    @Test
    public void testContainsAnyFunctionExtension1() throws InterruptedException {
        log.info("ContainsAnyFunctionExtension TestCase1: Asserts [[a, b, c], [a], -> true]");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "\ndefine stream inputStream (symbol string, price long, volume long);";
        String query = (
                "@info(name = 'query1') " +
                 "from inputStream " +
                "select symbol, price, list:create() as tmpList1, list:create() as tmpList2 " +
                " insert into tmpStream;" +

                "@info(name = 'query2') " +
                "from tmpStream  " +
                "select symbol, price, " +
                " list:add(list:add(list:add(tmpList1,'a'),'b'),'c') as list1, list:add(tmpList2,'a') as list2 " +
                " insert into outputStream;" +

                "@info(name = 'query3') " +
                "from outputStream " +
                "select list:containsAny(list1, list2) as doesContains1 " +
                " insert into outputStream2;"
        );

        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(
                inStreamDefinition + query);

        siddhiAppRuntime.addCallback("outputStream2", new StreamCallback() {
            @Override
            public void receive(Event[] events) {
                EventPrinter.print(events);
                count.incrementAndGet();
                AssertJUnit.assertEquals(Boolean.TRUE, events[0].getData(0));
                eventArrived = true;
            }
        });

        InputHandler inputHandler = siddhiAppRuntime.getInputHandler("inputStream");
        siddhiAppRuntime.start();

        inputHandler.send(new Object[]{"IBM", 100, 100L});
        SiddhiTestHelper.waitForEvents(100, 1, count, 60000);
        AssertJUnit.assertEquals(1, count.get());
        AssertJUnit.assertTrue(eventArrived);
        siddhiAppRuntime.shutdown();
    }

    @Test
    public void testContainsAnyFunctionExtension2() throws InterruptedException {
        log.info("ContainsAnyFunctionExtension TestCase2: Asserts [[a, b, c], [z], -> false]");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "\ndefine stream inputStream (symbol string, price long, volume long);";
        String query = (
                "@info(name = 'query1') " +
                        "from inputStream " +
                        "select symbol, price, list:create() as tmpList1, list:create() as tmpList2 " +
                        " insert into tmpStream;" +

                        "@info(name = 'query2') " +
                        "from tmpStream  " +
                        "select symbol, price, " +
                        "list:add(list:add(list:add(tmpList1,'a'),'b'),'c') as list1, " +
                        "list:add(tmpList2,'z') as list2 " +
                        " insert into outputStream;" +

                        "@info(name = 'query3') " +
                        "from outputStream " +
                        "select list:containsAny(list1, list2) as doesContains1 " +
                        " insert into outputStream2;"
        );

        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(
                inStreamDefinition + query);

        siddhiAppRuntime.addCallback("outputStream2", new StreamCallback() {
            @Override
            public void receive(Event[] events) {
                EventPrinter.print(events);
                count.incrementAndGet();
                AssertJUnit.assertEquals(Boolean.FALSE, events[0].getData(0));
                eventArrived = true;
            }
        });

        InputHandler inputHandler = siddhiAppRuntime.getInputHandler("inputStream");
        siddhiAppRuntime.start();

        inputHandler.send(new Object[]{"IBM", 100, 100L});
        SiddhiTestHelper.waitForEvents(100, 1, count, 60000);
        AssertJUnit.assertEquals(1, count.get());
        AssertJUnit.assertTrue(eventArrived);
        siddhiAppRuntime.shutdown();
    }

    @Test
    public void testContainsAnyFunctionExtension3() throws InterruptedException {
        log.info("ContainsAnyFunctionExtension TestCase3: Asserts [[a, b, c], [a, b], -> true]");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "\ndefine stream inputStream (symbol string, price long, volume long);";
        String query = (
                "@info(name = 'query1') " +
                        "from inputStream " +
                        "select symbol, price, list:create() as tmpList1, list:create() as tmpList2 " +
                        " insert into tmpStream;" +

                        "@info(name = 'query2') " +
                        "from tmpStream  " +
                        "select symbol, price, " +
                        "list:add(list:add(list:add(tmpList1,'a'),'b'),'c') as list1, " +
                        "list:add(list:add(tmpList2,'a'),'b') as list2 " +
                        " insert into outputStream;" +

                        "@info(name = 'query3') " +
                        "from outputStream " +
                        "select list:containsAny(list1, list2) as doesContains1 " +
                        " insert into outputStream2;"
        );

        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(
                inStreamDefinition + query);

        siddhiAppRuntime.addCallback("outputStream2", new StreamCallback() {
            @Override
            public void receive(Event[] events) {
                EventPrinter.print(events);
                count.incrementAndGet();
                AssertJUnit.assertEquals(Boolean.TRUE, events[0].getData(0));
                eventArrived = true;
            }
        });

        InputHandler inputHandler = siddhiAppRuntime.getInputHandler("inputStream");
        siddhiAppRuntime.start();

        inputHandler.send(new Object[]{"IBM", 100, 100L});
        SiddhiTestHelper.waitForEvents(100, 1, count, 60000);
        AssertJUnit.assertEquals(1, count.get());
        AssertJUnit.assertTrue(eventArrived);
        siddhiAppRuntime.shutdown();
    }

    @Test
    public void testContainsAnyFunctionExtension4() throws InterruptedException {
        log.info("ContainsAnyFunctionExtension TestCase4: Asserts [[a, b, c], [a, z], -> true]");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "\ndefine stream inputStream (symbol string, price long, volume long);";
        String query = (
                "@info(name = 'query1') " +
                        "from inputStream " +
                        "select symbol, price, list:create() as tmpList1, list:create() as tmpList2 " +
                        " insert into tmpStream;" +

                        "@info(name = 'query2') " +
                        "from tmpStream  " +
                        "select symbol, price, " +
                        "list:add(list:add(list:add(tmpList1,'a'),'b'),'c') as list1, " +
                        "list:add(list:add(tmpList2,'a'),'z') as list2 " +
                        " insert into outputStream;" +

                        "@info(name = 'query3') " +
                        "from outputStream " +
                        "select list:containsAny(list1, list2) as doesContains1 " +
                        " insert into outputStream2;"
        );

        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(
                inStreamDefinition + query);

        siddhiAppRuntime.addCallback("outputStream2", new StreamCallback() {
            @Override
            public void receive(Event[] events) {
                EventPrinter.print(events);
                count.incrementAndGet();
                AssertJUnit.assertEquals(Boolean.TRUE, events[0].getData(0));
                eventArrived = true;
            }
        });

        InputHandler inputHandler = siddhiAppRuntime.getInputHandler("inputStream");
        siddhiAppRuntime.start();

        inputHandler.send(new Object[]{"IBM", 100, 100L});
        SiddhiTestHelper.waitForEvents(100, 1, count, 60000);
        AssertJUnit.assertEquals(1, count.get());
        AssertJUnit.assertTrue(eventArrived);
        siddhiAppRuntime.shutdown();
    }

    @Test
    public void testContainsAnyFunctionExtension5() throws InterruptedException {
        log.info("ContainsAnyFunctionExtension TestCase5: Asserts [[a, b, c], [a, b, c], -> true]");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "\ndefine stream inputStream (symbol string, price long, volume long);";
        String query = (
                "@info(name = 'query1') " +
                        "from inputStream " +
                        "select symbol, price, list:create() as tmpList1, list:create() as tmpList2 " +
                        " insert into tmpStream;" +

                        "@info(name = 'query2') " +
                        "from tmpStream  " +
                        "select symbol, price, " +
                        "list:add(list:add(list:add(tmpList1,'a'),'b'),'c') as list1, " +
                        "list:add(list:add(list:add(tmpList1,'a'),'b'),'c') as list2 " +
                        " insert into outputStream;" +

                        "@info(name = 'query3') " +
                        "from outputStream " +
                        "select list:containsAny(list1, list2) as doesContains1 " +
                        " insert into outputStream2;"
        );

        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(
                inStreamDefinition + query);

        siddhiAppRuntime.addCallback("outputStream2", new StreamCallback() {
            @Override
            public void receive(Event[] events) {
                EventPrinter.print(events);
                count.incrementAndGet();
                AssertJUnit.assertEquals(Boolean.TRUE, events[0].getData(0));
                eventArrived = true;
            }
        });

        InputHandler inputHandler = siddhiAppRuntime.getInputHandler("inputStream");
        siddhiAppRuntime.start();

        inputHandler.send(new Object[]{"IBM", 100, 100L});
        SiddhiTestHelper.waitForEvents(100, 1, count, 60000);
        AssertJUnit.assertEquals(1, count.get());
        AssertJUnit.assertTrue(eventArrived);
        siddhiAppRuntime.shutdown();
    }

    @Test
    public void testContainsAnyFunctionExtension6() throws InterruptedException {
        log.info("ContainsAnyFunctionExtension TestCase6: Asserts [[a, b, c], [a, b, z], -> true]");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "\ndefine stream inputStream (symbol string, price long, volume long);";
        String query = (
                "@info(name = 'query1') " +
                        "from inputStream " +
                        "select symbol, price, list:create() as tmpList1, list:create() as tmpList2 " +
                        " insert into tmpStream;" +

                        "@info(name = 'query2') " +
                        "from tmpStream  " +
                        "select symbol, price, " +
                        "list:add(list:add(list:add(tmpList1,'a'),'b'),'c') as list1, " +
                        "list:add(list:add(list:add(tmpList1,'a'),'b'),'z') as list2 " +
                        " insert into outputStream;" +

                        "@info(name = 'query3') " +
                        "from outputStream " +
                        "select list:containsAny(list1, list2) as doesContains1 " +
                        " insert into outputStream2;"
        );

        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(
                inStreamDefinition + query);

        siddhiAppRuntime.addCallback("outputStream2", new StreamCallback() {
            @Override
            public void receive(Event[] events) {
                EventPrinter.print(events);
                count.incrementAndGet();
                AssertJUnit.assertEquals(Boolean.TRUE, events[0].getData(0));
                eventArrived = true;
            }
        });

        InputHandler inputHandler = siddhiAppRuntime.getInputHandler("inputStream");
        siddhiAppRuntime.start();

        inputHandler.send(new Object[]{"IBM", 100, 100L});
        SiddhiTestHelper.waitForEvents(100, 1, count, 60000);
        AssertJUnit.assertEquals(1, count.get());
        AssertJUnit.assertTrue(eventArrived);
        siddhiAppRuntime.shutdown();
    }

    @Test
    public void testContainsAnyFunctionExtension7() throws InterruptedException {
        log.info("ContainsAnyFunctionExtension TestCase7: Asserts [[a, b, c], [a, b, c, d], -> true]");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "\ndefine stream inputStream (symbol string, price long, volume long);";
        String query = (
                "@info(name = 'query1') " +
                        "from inputStream " +
                        "select symbol, price, list:create() as tmpList1, list:create() as tmpList2 " +
                        " insert into tmpStream;" +

                        "@info(name = 'query2') " +
                        "from tmpStream  " +
                        "select symbol, price, " +
                        "list:add(list:add(list:add(tmpList1,'a'),'b'),'c') as list1, " +
                        "list:add(list:add(list:add(list:add(tmpList1,'a'),'b'),'c'),'d') as list2 " +
                        " insert into outputStream;" +

                        "@info(name = 'query3') " +
                        "from outputStream " +
                        "select list:containsAny(list1, list2) as doesContains1 " +
                        " insert into outputStream2;"
        );

        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(
                inStreamDefinition + query);

        siddhiAppRuntime.addCallback("outputStream2", new StreamCallback() {
            @Override
            public void receive(Event[] events) {
                EventPrinter.print(events);
                count.incrementAndGet();
                AssertJUnit.assertEquals(Boolean.TRUE, events[0].getData(0));
                eventArrived = true;
            }
        });

        InputHandler inputHandler = siddhiAppRuntime.getInputHandler("inputStream");
        siddhiAppRuntime.start();

        inputHandler.send(new Object[]{"IBM", 100, 100L});
        SiddhiTestHelper.waitForEvents(100, 1, count, 60000);
        AssertJUnit.assertEquals(1, count.get());
        AssertJUnit.assertTrue(eventArrived);
        siddhiAppRuntime.shutdown();
    }

    @Test
    public void testContainsAnyFunctionExtension8() throws InterruptedException {
        log.info("ContainsAnyFunctionExtension TestCase8: Asserts [[a, z], [a, b, c, d], -> true]");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "\ndefine stream inputStream (symbol string, price long, volume long);";
        String query = (
                "@info(name = 'query1') " +
                        "from inputStream " +
                        "select symbol, price, list:create() as tmpList1, list:create() as tmpList2 " +
                        " insert into tmpStream;" +

                        "@info(name = 'query2') " +
                        "from tmpStream  " +
                        "select symbol, price, " +
                        "list:add(list:add(tmpList1,'a'),'z') as list1, " +
                        "list:add(list:add(list:add(list:add(tmpList1,'a'),'b'),'c'),'d') as list2 " +
                        " insert into outputStream;" +

                        "@info(name = 'query3') " +
                        "from outputStream " +
                        "select list:containsAny(list1, list2) as doesContains1 " +
                        " insert into outputStream2;"
        );

        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(
                inStreamDefinition + query);

        siddhiAppRuntime.addCallback("outputStream2", new StreamCallback() {
            @Override
            public void receive(Event[] events) {
                EventPrinter.print(events);
                count.incrementAndGet();
                AssertJUnit.assertEquals(Boolean.TRUE, events[0].getData(0));
                eventArrived = true;
            }
        });

        InputHandler inputHandler = siddhiAppRuntime.getInputHandler("inputStream");
        siddhiAppRuntime.start();

        inputHandler.send(new Object[]{"IBM", 100, 100L});
        SiddhiTestHelper.waitForEvents(100, 1, count, 60000);
        AssertJUnit.assertEquals(1, count.get());
        AssertJUnit.assertTrue(eventArrived);
        siddhiAppRuntime.shutdown();
    }

    @Test(expectedExceptions = SiddhiAppCreationException.class)
    public void testContainsAnyFunctionExtensionNegative() {
        log.info("ContainsAnyFunctionExtension Negative TestCase with wrong typed arguments");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "\n" +
                "define stream inputStream (symbol string, price long, volume long);";
        String query = ("" +
                "@info(name = 'query1') " +
                "from inputStream " +
                "select symbol,price,list:create() as tmpList" +
                " insert into tmpStream;" +

                "@info(name = 'query2') " +
                "from tmpStream  " +
                "select symbol,price,tmpList, list:add(tmpList,symbol) as list1 " +
                "insert into outputStream;" +

                "@info(name = 'query3') from outputStream " +
                "select list:containsAny(symbol,price) as isList2" +
                " insert into outputStream2;"
        );

        siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);
    }
}

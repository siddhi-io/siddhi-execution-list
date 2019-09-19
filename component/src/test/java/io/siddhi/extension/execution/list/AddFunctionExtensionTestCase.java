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
import io.siddhi.core.stream.input.InputHandler;
import io.siddhi.core.stream.output.StreamCallback;
import io.siddhi.core.util.EventPrinter;
import io.siddhi.core.util.SiddhiTestHelper;
import org.apache.log4j.Logger;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class AddFunctionExtensionTestCase {
    private static final Logger log = Logger.getLogger(AddFunctionExtensionTestCase.class);
    private AtomicInteger count = new AtomicInteger(0);
    private volatile boolean eventArrived;

    @BeforeMethod
    public void init() {
        count.set(0);
        eventArrived = false;
    }

    @Test
    public void testAddFunctionExtension() throws InterruptedException {
        log.info("AddFunctionExtension TestCase");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "\ndefine stream inputStream (symbol string, price long, volume long);";
        String query = ("@info(name = 'query1') from inputStream select symbol,price, "
                + "list:create() as tmpList insert into tmpStream;"
                + "@info(name = 'query2') from tmpStream  "
                + "select symbol,price,tmpList, list:add(tmpList, symbol, 0) as newList"
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
                    if (count.get() == 1) {
                        ArrayList map = (ArrayList) event.getData(3);
                        AssertJUnit.assertEquals("IBM", map.get(0));
                        eventArrived = true;
                    }
                    if (count.get() == 2) {
                        ArrayList map = (ArrayList) event.getData(3);
                        AssertJUnit.assertEquals("WSO2", map.get(0));
                        eventArrived = true;
                    }
                    if (count.get() == 3) {
                        ArrayList map = (ArrayList) event.getData(3);
                        AssertJUnit.assertEquals("XYZ", map.get(0));
                        eventArrived = true;
                    }
                }
            }
        });


        InputHandler inputHandler = siddhiAppRuntime.getInputHandler("inputStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Object[]{"IBM", 100, 100L});
        inputHandler.send(new Object[]{"WSO2", 200, 200L});
        inputHandler.send(new Object[]{"XYZ", 300, 200L});
        SiddhiTestHelper.waitForEvents(100, 3, count, 60000);
        AssertJUnit.assertEquals(3, count.get());
        AssertJUnit.assertTrue(eventArrived);
        siddhiAppRuntime.shutdown();
    }

    @Test
    public void testAddFunctionExtension1() throws InterruptedException {
        log.info("AddFunctionExtension TestCase");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "\ndefine stream inputStream (symbol string, index int, volume long);";
        String query = ("@info(name = 'query1') from inputStream select symbol,index, "
                + "list:create() as tmpList insert into tmpStream;"
                + "@info(name = 'query2') from tmpStream  select symbol,index,tmpList, " +
                "list:add(list:add(list:create(),'CHECK ID'), symbol,index) as newList"
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
                    ArrayList map = (ArrayList) event.getData(3);
                    AssertJUnit.assertEquals("CHECK ID", map.get(0));
                    if (count.get() == 1) {
                        AssertJUnit.assertEquals("IBM", map.get(1));
                        eventArrived = true;
                    }
                    if (count.get() == 2) {
                        AssertJUnit.assertEquals("WSO2", map.get(1));
                        eventArrived = true;
                    }
                    if (count.get() == 3) {
                        AssertJUnit.assertEquals("XYZ", map.get(1));
                        eventArrived = true;
                    }
                }
            }
        });


        InputHandler inputHandler = siddhiAppRuntime.getInputHandler("inputStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Object[]{"IBM", 1, 100L});
        inputHandler.send(new Object[]{"WSO2", 1, 200L});
        inputHandler.send(new Object[]{"XYZ", 1, 200L});
        SiddhiTestHelper.waitForEvents(100, 3, count, 60000);
        AssertJUnit.assertEquals(3, count.get());
        AssertJUnit.assertTrue(eventArrived);
        siddhiAppRuntime.shutdown();
    }

    @Test(expectedExceptions = SiddhiAppCreationException.class)
    public void testAddFunctionExtension2() {
        log.info("AddFunctionExtension TestCase with test attributeExpressionExecutors length");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "\ndefine stream inputStream (symbol string, price long, volume long);";
        String query = ("@info(name = 'query1') from inputStream select symbol,price, "
                + "list:create() as tmpList insert into tmpStream;"
                + "@info(name = 'query2') from tmpStream  select symbol,price,tmpList, list:add(tmpList) as newList"
                + " insert into outputStream;"
        );

        siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);
    }
}

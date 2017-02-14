/*
 * Copyright 2017 Huawei Technologies Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.openo.vnfsdk.functest.externalservice.entity;

import org.junit.Before;
import org.junit.Test;

public class ServiceRegisterEntityTest {

    private ServiceRegisterEntity serviceRegistry;

    @Before
    public void setUp() {
        serviceRegistry = new ServiceRegisterEntity();
    }

    @Test
    public void testSetSingleNode() {
        serviceRegistry.setSingleNode("127.0.0.1", "80", 10);
    }

    @Test
    public void testSetServiceName() {
        serviceRegistry.setServiceName("nfvo");

    }

    @Test
    public void testSetVersion() {
        serviceRegistry.setVersion("5.6");

    }

    @Test
    public void testSetProtocol() {
        serviceRegistry.setProtocol("http");
    }

    @Test
    public void testSetVisualRange() {
        serviceRegistry.setVisualRange("range");

    }

}

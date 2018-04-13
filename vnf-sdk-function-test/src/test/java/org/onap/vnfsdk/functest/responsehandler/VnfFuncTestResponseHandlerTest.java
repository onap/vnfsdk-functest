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

package org.onap.vnfsdk.functest.responsehandler;

import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.assertNotNull;

public class VnfFuncTestResponseHandlerTest {

    private VnfFuncTestResponseHandler vnfSdkFuncHandler;

    @Test
    public void testGetInstance() {
        vnfSdkFuncHandler = VnfFuncTestResponseHandler.getInstance();
        assertNotNull(vnfSdkFuncHandler);
    }

    @Test
    public void testLoadConfigurations() {
        try {
            Object vnfFuncTestResponseHandlerObj = VnfFuncTestResponseHandler.getInstance();
            Method m = vnfFuncTestResponseHandlerObj.getClass().getDeclaredMethod("loadConfigurations");
            m.setAccessible(true);
            m.invoke(vnfFuncTestResponseHandlerObj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
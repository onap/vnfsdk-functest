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

package org.onap.vnfsdk.functest.util;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class GsonUtil {

    private GsonUtil() {

    }

    public static String generateId() {
        return UUID.randomUUID().toString();
    }

    public static boolean isNotEmpty(String str) {
        return str != null && !"".equals(str) && str.length() > 0;
    }

    /**
     * change object to str.
     */
    public static String objectToString(Object obj) {
        Gson gson = new Gson();
        if (obj != null) {
            return gson.toJson(obj);
        } else {
            return null;
        }
    }

    public static String getNowTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
}

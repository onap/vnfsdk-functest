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

package org.onap.vnfsdk.functest.externalservice.entity;

public class OperationStatus {

    operResultCode oResultCode;
    private boolean operFinished = false;
    private String operResultMessage;


    public operResultCode getoResultCode() {
        return oResultCode;
    }

    public void setoResultCode(operResultCode oResultCode) {
        this.oResultCode = oResultCode;
    }

    public String getOperResultMessage() {
        return operResultMessage;
    }

    public void setOperResultMessage(String operResultMessage) {
        this.operResultMessage = operResultMessage;
    }

    public boolean isOperFinished() {
        return operFinished;
    }

    public void setOperFinished(boolean operFinished) {
        this.operFinished = operFinished;
    }

    public enum operResultCode {
        SUCCESS, FAILURE, NOTFOUND
    }
}

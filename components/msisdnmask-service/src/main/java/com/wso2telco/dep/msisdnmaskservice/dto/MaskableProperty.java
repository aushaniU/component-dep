/*
 * ******************************************************************************
 *  * Copyright  (c) 2019, WSO2.Telco Inc. (http://www.wso2telco.com) All Rights Reserved.
 *  *
 *  * WSO2.Telco Inc. licences this file to you under  the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *   http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *  *****************************************************************************
 */

package com.wso2telco.dep.msisdnmaskservice.dto;

import java.util.ArrayList;
import java.util.List;

public class MaskableProperty {


    private String maskablType ;
    private String payloadorUrl;
    private String propertyName ;
    private List<String> location =new ArrayList<String>();;
    private String algorithem;

    public String getMaskablType() {
        return maskablType;
    }

    public void setMaskablType(String maskablType) {
        this.maskablType = maskablType;
    }

    public String getPayloadorUrl() {
        return payloadorUrl;
    }

    public void setPayloadorUrl(String payloadorUrl) {
        this.payloadorUrl = payloadorUrl;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public List<String> getLocation() {
        return location;
    }

    public void setLocation(List<String> location) {
        this.location = location;
    }

    public String getAlgorithem() {
        return algorithem;
    }

    public void setAlgorithem(String algorithem) {
        this.algorithem = algorithem;
    }
}

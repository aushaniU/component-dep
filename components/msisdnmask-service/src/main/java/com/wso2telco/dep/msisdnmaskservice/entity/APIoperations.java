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

package com.wso2telco.dep.msisdnmaskservice.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "apioperations")
public class APIoperations {

    private String requesturl;
    private String maskablType;
    private String propertyPath;

    public String getRequesturl() {
        return requesturl;
    }

    @XmlElement(name = "requesturl")
    public void setRequesturl(String operationname) {
        this.requesturl = operationname;
    }

    public String getPropertyPath() {
        return propertyPath;
    }

    @XmlElement(name="propertypath")
    public void setPropertyPath(String propertypath) {
        this.propertyPath = propertypath;
    }

    public String getMaskablType() {
        return maskablType;
    }

    @XmlElement(name = "maskablType")
    public void setMaskablType(String maskablType) {
        this.maskablType = maskablType;
    }
}

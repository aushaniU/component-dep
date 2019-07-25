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
import java.util.List;

@XmlRootElement(name = "API")
public class API {

    private String apiName;
    private String regexPattern;
    private String maskAlgorithem;
    List<APIoperations> apIoperationsLists;

    public String getApiName() {
        return apiName;
    }

    @XmlElement(name = "apiname")
    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getRegexPattern() {
        return regexPattern;
    }

    @XmlElement(name = "regexPattern")
    public void setRegexPattern(String regexPattern) {
        this.regexPattern = regexPattern;
    }

    public String getMaskAlgorithem() {
        return maskAlgorithem;
    }

    @XmlElement(name = "maskAlgorithem")
    public void setMaskAlgorithem(String maskAlgorithem) {
        this.maskAlgorithem = maskAlgorithem;
    }

    public List<APIoperations> getApiOperationList() {
        return apIoperationsLists;
    }

    @XmlElement(name = "apioperations")
    public void setApiOperationList(List<APIoperations> apioperations) {
        this.apIoperationsLists = apioperations;
    }
}

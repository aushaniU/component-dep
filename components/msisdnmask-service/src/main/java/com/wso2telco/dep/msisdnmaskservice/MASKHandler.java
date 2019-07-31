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

package com.wso2telco.dep.msisdnmaskservice;

import com.wso2telco.dep.msisdnmaskservice.APIFactory.APIFactory;
import com.wso2telco.dep.msisdnmaskservice.APIFactory.APIGenaratable;
import com.wso2telco.dep.msisdnmaskservice.MSISDNMasker.AttributeBaseMasker;
import com.wso2telco.dep.msisdnmaskservice.MSISDNMasker.ListBaseMasker;
import com.wso2telco.dep.msisdnmaskservice.MSISDNMasker.MaskFactory;
import com.wso2telco.dep.msisdnmaskservice.ServiceFactory.APIServicable;
import com.wso2telco.dep.msisdnmaskservice.dto.APIDTO;
import com.wso2telco.dep.msisdnmaskservice.dto.APIListDTO;
import com.wso2telco.dep.msisdnmaskservice.dto.APIOperationDTO;
import com.wso2telco.dep.msisdnmaskservice.dto.MaskableProperty;
import com.wso2telco.dep.msisdnmaskservice.util.MaskPropertyReader;
import org.apache.synapse.core.axis2.Axis2MessageContext;
import org.apache.synapse.rest.AbstractHandler;
import org.apache.synapse.MessageContext;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class MASKHandler extends AbstractHandler {

    @Override
    public boolean handleRequest(MessageContext messageContext){

        String apidirection ="request";
        updateJsonObject(apidirection,messageContext);

        return true;
    }

    @Override
    public boolean handleResponse(MessageContext messageContext) {
        String apidirection ="response";
        updateJsonObject(apidirection,messageContext);
        return false;
    }

    private void updateJsonObject(String type, MessageContext messageContext) {
        Object headers = ((Axis2MessageContext) messageContext).getAxis2MessageContext().getProperty(org.apache.axis2.context.MessageContext.TRANSPORT_HEADERS);
        Map headersMap = (Map) headers;
        String resourcePath = (String) headersMap.get("RESOURCE");
        String apiContext = (String) headersMap.get("api.ut.context");
        String apiName = apiContext.split("/")[0];
        List<APIDTO> apidtoList = MaskPropertyReader.getInstance().getApiListDTO().getApiList();

        for (APIDTO apidto : apidtoList) {
            if (apidto.getApiName().equalsIgnoreCase(apiName)) {
                APIFactory apiFactory = new APIFactory();
                apiFactory.getAPIFactory(apiName).getAPIHandler(apidto,resourcePath).updateJsonPayload(type,messageContext,apidto);

            }

        }
    }
}

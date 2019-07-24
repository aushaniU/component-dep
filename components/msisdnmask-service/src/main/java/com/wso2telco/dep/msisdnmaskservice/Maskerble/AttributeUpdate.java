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

package com.wso2telco.dep.msisdnmaskservice.Maskerble;


import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.wso2telco.core.maskservice.MaskerService.MaskingHandler;
import com.wso2telco.dep.msisdnmaskservice.dto.MaskableProperty;
import org.apache.synapse.MessageContext;
import org.apache.synapse.commons.json.JsonUtil;
import org.apache.synapse.core.axis2.Axis2MessageContext;
import org.apache.synapse.transport.passthru.util.RelayUtils;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public class AttributeUpdate implements MsisdnMaskable {

    public void updateRequestData(MaskableProperty maskableProperty, MessageContext messageContext) throws IOException, XMLStreamException {

        MaskingHandler maskingHandler =new MaskingHandler();
        org.apache.axis2.context.MessageContext axis2MessageContext = ((Axis2MessageContext) messageContext)
                .getAxis2MessageContext();
        RelayUtils.buildMessage(axis2MessageContext);
        String jsonString = JsonUtil.jsonPayloadToString(((Axis2MessageContext) messageContext).getAxis2MessageContext());

        String mainJSONObject = "{\"outboundSMSMessageRequest\":{\"senderAddress\":\"tel:+94773906141\"," +
                "\"transactionOperationStatus\":\"Charged\",\"clientCorrelator\":\"TES35cctrd25\",\"referenceCode\":\"REF-TEce2dfdwe\"," +
                "\"paymentAmount\":{\"chargingInformation\":{\"amount\":\"100\",\"description\":\"Alien Invaders Game\"," +
                "\"currency\":\"USD\"},\"chargingMetaData\":{\"channel\":\"sms\",\"onBehalfOf\":\"Merchant\",\"taxAmount\":\"0\"}}}}\n";
        String encryptedValue = maskingHandler.getEncryptedValue(JsonPath.read(mainJSONObject,maskableProperty.getLocation()),"BCMask");
        String directory = "$.".concat(maskableProperty.getLocation());
        DocumentContext doc = JsonPath.parse(mainJSONObject).set(directory,encryptedValue);
        System.out.println(doc.jsonString());

    }
}

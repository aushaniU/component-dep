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

import org.apache.axis2.context.MessageContext;
import org.apache.synapse.commons.json.JsonUtil;
import org.apache.synapse.core.axis2.Axis2MessageContext;;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

public class MASKHandlerTest {

    @Test
    public void updateRequestData_test() {
        MASKHandler maskHandler =new MASKHandler();

        MessageContext mockMessageContext = Mockito.mock(MessageContext.class);
        Axis2MessageContext mockAxis2MessageContext = Mockito.mock(Axis2MessageContext.class);
        when(mockAxis2MessageContext.getAxis2MessageContext()).thenReturn(mockMessageContext);

        Map<String, String> headersMap = new HashMap<>();
        headersMap.put("RESOURCE", "outbound/tel:+7555/requests");
        headersMap.put("api.ut.context","smsmessaging/v1");

        when(mockMessageContext.getProperty(org.apache.axis2.context.MessageContext.TRANSPORT_HEADERS)).thenReturn(headersMap);

        String jsonObject = "{\"outboundSMSMessageRequest\":{\"senderAddress\":\"tel:+94773906149\"," +
                "\"transactionOperationStatus\":\"Charged\",\"clientCorrelator\":\"SSTES35cctrd25\",\"referenceCode\":\"REF-TEce2dfdwe\"," +
                "\"paymentAmount\":{\"chargingInformation\":{\"amount\":\"100\",\"description\":\"Alien Invaders Game\"," +
                "\"currency\":\"USD\"},\"chargingMetaData\":{\"channel\":\"sms\",\"onBehalfOf\":\"Merchant\",\"taxAmount\":\"0\"}}}}\n";

        when(mockMessageContext.getProperty("org.apache.synapse.commons.json.JsonInputStream")).thenReturn(new ByteArrayInputStream(jsonObject.getBytes()));

        boolean result = maskHandler.handleRequest(mockAxis2MessageContext);
        Assert.assertEquals(result, true);
    }
}
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

import com.wso2telco.dep.msisdnmaskservice.MSISDNMasker.AttributeBaseMasker;
import com.wso2telco.dep.msisdnmaskservice.MSISDNMasker.ListBaseMasker;
import com.wso2telco.dep.msisdnmaskservice.MSISDNMasker.MaskFactory;
import com.wso2telco.dep.msisdnmaskservice.dto.APIDTO;
import com.wso2telco.dep.msisdnmaskservice.dto.APIListDTO;
import com.wso2telco.dep.msisdnmaskservice.dto.APIOperationDTO;
import com.wso2telco.dep.msisdnmaskservice.dto.MaskableProperty;
import com.wso2telco.dep.msisdnmaskservice.util.MaskPropertyReader;
import org.apache.synapse.core.axis2.Axis2MessageContext;
import org.apache.synapse.rest.AbstractHandler;
import org.apache.synapse.MessageContext;
import org.wso2.carbon.apimgt.impl.utils.APIUtil;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;


/**
 *
 */
public class MASKHandler extends AbstractHandler {

    private void updateRequestData (MaskableProperty property,MessageContext messageContext) {

        MaskFactory maskFactory= null;

        switch(property.getMaskablType()){
            case "Attribute":
                maskFactory =  new AttributeBaseMasker();
            break;
            case "List":
                maskFactory = new ListBaseMasker();
            break;
        }

        try {
            maskFactory.getMasker().updateRequestData(property,messageContext);
        } catch (XMLStreamException xmlSEx){
                System.out.println("Error while getting validator class"+ xmlSEx);
            } catch (IOException ioEx){
            System.out.println("Error while getting validator class"+ ioEx);
            }


    }

    @Override
    public boolean handleRequest(MessageContext messageContext){

       /* Object headers = ((Axis2MessageContext) messageContext).getAxis2MessageContext().getProperty(org.apache.axis2.context.MessageContext.TRANSPORT_HEADERS);
        Map headersMap = (Map) headers;
        String resourcePath = (String)headersMap.get("RESOURCE");
        String apiContext = (String) messageContext.getProperty("api.ut.context");
        String apiName = apiContext.substring(apiContext.indexOf("/") + 1);
*/
        String apiName = "smsmessaging";
        String resourcePath = "outbound/requests";
        MaskableProperty maskPropertyReader = getmaskableProperties(apiName ,resourcePath);

        if(maskPropertyReader!=null){
            updateRequestData(maskPropertyReader,messageContext);
        } else {
            System.out.println("Not a maskable msisdn");
        }


        return true;
    }

    @Override
    public boolean handleResponse(MessageContext messageContext) {
        return false;
    }

    private MaskableProperty getmaskableProperties(String apiName,String resourcePath){
       MaskPropertyReader maskPropertyReader = MaskPropertyReader.getInstance();
       APIListDTO apiListDTO = maskPropertyReader.getApiListDTO();
       List<APIDTO> apidtoList = apiListDTO.getApiList();


        MaskableProperty maskableProperty = null;
        for(APIDTO apidto: apidtoList){
            if(apidto.getApiName().equals(apiName)){
                List<APIOperationDTO> apiOperationDTOS = apidto.getApiOperationList();
                for(APIOperationDTO apiOperationDTO: apiOperationDTOS){
                    String requesturlpattern = apiOperationDTO.getOperantionName();
                    Pattern pattern = Pattern.compile(requesturlpattern);
                    Matcher matcher = pattern.matcher(resourcePath);
                    if (matcher.matches()) {
                        maskableProperty = new MaskableProperty();
                        maskableProperty.setLocation(apiOperationDTO.getPropertyPath());
                        maskableProperty.setMaskablType(apiOperationDTO.getMaskablType());
                     break;
                    }
                }

            }
        }

        return maskableProperty;
    }
}

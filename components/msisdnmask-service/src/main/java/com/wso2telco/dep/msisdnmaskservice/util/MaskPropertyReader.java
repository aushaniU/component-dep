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

package com.wso2telco.dep.msisdnmaskservice.util;

import com.wso2telco.dep.msisdnmaskservice.dto.APIDTO;
import com.wso2telco.dep.msisdnmaskservice.dto.APIListDTO;
import com.wso2telco.dep.msisdnmaskservice.dto.APIOperationDTO;
import com.wso2telco.dep.msisdnmaskservice.entity.API;
import com.wso2telco.dep.msisdnmaskservice.entity.APIList;
import com.wso2telco.dep.msisdnmaskservice.entity.APIoperations;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Iterator;

public class MaskPropertyReader {

    private static MaskPropertyReader maskPropertyReader;

    String configPath =  "/home/mintuser/Documents/Projecttask/ProductTask/HUB/Numbermasking/maskingProperties.xml";
    JAXBContext jaxbContext;
    Unmarshaller jaxbUnmarshaller;
    File file  = new File(configPath);

    APIListDTO apiListDTO ;

    private MaskPropertyReader(){
        this.apiListDTO = readMaskAPIList();
    }

    public static MaskPropertyReader getInstance(){
        if (maskPropertyReader == null){ //if there is no instance available... create new one
            maskPropertyReader = new MaskPropertyReader();
        }
        return maskPropertyReader;
    }

    private APIListDTO readMaskAPIList(){
        APIList apiList = null;
        try {
            jaxbContext = JAXBContext.newInstance(APIList.class);
            jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            apiList = (APIList) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException jx){
            System.out.println("file reading error" + jx);
        }

        apiListDTO= new APIListDTO();

        for (Iterator iterator = apiList.getApis().iterator(); iterator.hasNext();){
            API api = (API) iterator.next();
            APIDTO apidto = new APIDTO();
            apidto.setApiName(api.getApiName());
            apidto.setRegexPattern(api.getRegexPattern());

            System.out.println(apidto.getApiName()+apidto.getRegexPattern());

            for(APIoperations apiOperation: api.getApiOperationList()) {

                APIOperationDTO apiOperationDTO = new APIOperationDTO();

                apiOperationDTO.setOperantionName(apiOperation.getOperantionName());
                apiOperationDTO.setPropertyPath(apiOperation.getPropertyPath());
                apiOperationDTO.setMaskablType(apiOperation.getMaskablType());

                apidto.getApiOperationList().add(apiOperationDTO);
            }

            apiListDTO.getApiList().add(apidto);
        }

        return apiListDTO;
    }


    public APIListDTO getApiListDTO() {
        return apiListDTO;
    }

  /*  public void setApiListDTO(APIListDTO apiListDTO) {
        this.apiListDTO = apiListDTO;
    }*/
}

/*******************************************************************************
 * Copyright  (c) 2015-2016, WSO2.Telco Inc. (http://www.wso2telco.com) All Rights Reserved.
 * <p>
 * WSO2.Telco Inc. licences this file to you under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.wso2telco.dep.ratecardservice.resource;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.wso2telco.core.dbutils.exception.BusinessException;
import com.wso2telco.core.dbutils.exception.ServiceError;
import com.wso2telco.dep.ratecardservice.dao.model.ErrorDTO;
import com.wso2telco.dep.ratecardservice.dao.model.TaxDTO;
import com.wso2telco.dep.ratecardservice.service.TaxService;

@Path("/taxes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TaxResource {

	private final Log log = LogFactory.getLog(TaxResource.class);
	private TaxService taxService = new TaxService();

	@GET
	public Response getTaxes() {

		List<TaxDTO> taxes = null;
		Status responseCode = null;
		Object responseString = null;

		try {

			taxes = taxService.getTaxes();

			responseString = taxes;
			responseCode = Response.Status.OK;
		} catch (BusinessException e) {

			ErrorDTO error = new ErrorDTO();
			ErrorDTO.RequestError requestError = new ErrorDTO.RequestError();
			ErrorDTO.RequestError.ServiceException serviceException = new ErrorDTO.RequestError.ServiceException();

			serviceException.setMessageId(e.getErrorType().getCode());
			serviceException.setText(e.getErrorType().getMessage());
			requestError.setServiceException(serviceException);
			error.setRequestError(requestError);

			if (e.getErrorType().getCode() == ServiceError.NO_RESOURCES.getCode()) {

				responseCode = Response.Status.NOT_FOUND;
			} else {

				responseCode = Response.Status.BAD_REQUEST;
			}

			responseString = error;
		}

		log.debug("TaxResource getTaxes -> response code : " + responseCode);
		log.debug("TaxResource getTaxes -> response body : " + responseString);

		return Response.status(responseCode).entity(responseString).build();
	}

	@POST
	public Response addTax(TaxDTO tax) {

		TaxDTO newtax = null;
		Status responseCode = null;
		Object responseString = null;

		try {

			newtax = taxService.addTax(tax);

			if (newtax != null) {

				responseString = newtax;
				responseCode = Response.Status.CREATED;
			} else {

				log.error("Error in TaxResource addTax : tax can not insert to database ");
				throw new BusinessException(ServiceError.SERVICE_ERROR_OCCURED);
			}
		} catch (BusinessException e) {

			ErrorDTO error = new ErrorDTO();
			ErrorDTO.RequestError requestError = new ErrorDTO.RequestError();
			ErrorDTO.RequestError.ServiceException serviceException = new ErrorDTO.RequestError.ServiceException();

			serviceException.setMessageId(e.getErrorType().getCode());
			serviceException.setText(e.getErrorType().getMessage());
			requestError.setServiceException(serviceException);
			error.setRequestError(requestError);

			if (e.getErrorType().getCode() == ServiceError.NO_RESOURCES.getCode()) {

				responseCode = Response.Status.NOT_FOUND;
			} else {

				responseCode = Response.Status.BAD_REQUEST;
			}

			responseString = error;
		}

		log.debug("TaxResource addTax -> response code : " + responseCode);
		log.debug("TaxResource addTax -> response body : " + responseString);

		return Response.status(responseCode).entity(responseString).build();
	}
	
	@POST
	@Path("/add")
	public Response addTaxs(TaxDTO tax) {

		TaxDTO newtax = null;
		Status responseCode = null;
		Object responseString = null;

		try {

			newtax = taxService.addTax(tax);

			if (newtax != null) {

				responseString = newtax;
				responseCode = Response.Status.CREATED;
			} else {

				log.error("Error in TaxResource addTax : tax can not insert to database ");
				throw new BusinessException(ServiceError.SERVICE_ERROR_OCCURED);
			}
		} catch (BusinessException e) {

			ErrorDTO error = new ErrorDTO();
			ErrorDTO.RequestError requestError = new ErrorDTO.RequestError();
			ErrorDTO.RequestError.ServiceException serviceException = new ErrorDTO.RequestError.ServiceException();

			serviceException.setMessageId(e.getErrorType().getCode());
			serviceException.setText(e.getErrorType().getMessage());
			requestError.setServiceException(serviceException);
			error.setRequestError(requestError);

			if (e.getErrorType().getCode() == ServiceError.NO_RESOURCES.getCode()) {

				responseCode = Response.Status.NOT_FOUND;
			} else {

				responseCode = Response.Status.BAD_REQUEST;
			}

			responseString = error;
		}

		log.debug("TaxResource addTax -> response code : " + responseCode);
		log.debug("TaxResource addTax -> response body : " + responseString);

		return Response.status(responseCode).entity(responseString).build();
	}
	

	@GET
	@Path("/{taxId}")
	public Response getTax(@PathParam("taxId") int taxId) {

		TaxDTO tax = null;
		Status responseCode = null;
		Object responseString = null;

		try {

			tax = taxService.getTax(taxId);

			responseString = tax;
			responseCode = Response.Status.OK;
		} catch (BusinessException e) {

			ErrorDTO error = new ErrorDTO();
			ErrorDTO.RequestError requestError = new ErrorDTO.RequestError();
			ErrorDTO.RequestError.ServiceException serviceException = new ErrorDTO.RequestError.ServiceException();

			serviceException.setMessageId(e.getErrorType().getCode());
			serviceException.setText(e.getErrorType().getMessage());
			requestError.setServiceException(serviceException);
			error.setRequestError(requestError);

			if (e.getErrorType().getCode() == ServiceError.NO_RESOURCES.getCode()) {

				responseCode = Response.Status.NOT_FOUND;
			} else {

				responseCode = Response.Status.BAD_REQUEST;
			}

			responseString = error;
		}

		log.debug("TaxResource getTax -> response code : " + responseCode);
		log.debug("TaxResource getTax -> response body : " + responseString);

		return Response.status(responseCode).entity(responseString).build();
	}

	@Path("/{taxId}/ratetaxes")
	public RateTaxResource getRateTaxResource() {

		return new RateTaxResource();
	}
	
	@POST
	@Path("/insert")
	public Response insertTax(TaxDTO tax) {

		TaxDTO newtax = null;
		Status responseCode = null;
		Object responseString = null;

		try {

			newtax = taxService.insertTax(tax);

			if (newtax != null) {

				responseString = newtax;
				responseCode = Response.Status.CREATED;
			} else {

				log.error("Error in TaxResource addTax : tax can not insert to database ");
				throw new BusinessException(ServiceError.SERVICE_ERROR_OCCURED);
			}
		} catch (BusinessException e) {

			ErrorDTO error = new ErrorDTO();
			ErrorDTO.RequestError requestError = new ErrorDTO.RequestError();
			ErrorDTO.RequestError.ServiceException serviceException = new ErrorDTO.RequestError.ServiceException();

			serviceException.setMessageId(e.getErrorType().getCode());
			serviceException.setText(e.getErrorType().getMessage());
			requestError.setServiceException(serviceException);
			error.setRequestError(requestError);

			if (e.getErrorType().getCode() == ServiceError.NO_RESOURCES.getCode()) {

				responseCode = Response.Status.NOT_FOUND;
			} else {

				responseCode = Response.Status.BAD_REQUEST;
			}

			responseString = error;
		}

		log.debug("TaxResource addTax -> response code : " + responseCode);
		log.debug("TaxResource addTax -> response body : " + responseString);

		return Response.status(responseCode).entity(responseString).build();
	}
	
	
}

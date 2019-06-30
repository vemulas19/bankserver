package com.robobank.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.robobank.pojo.AccountInformation;
import com.robobank.pojo.PaymentInfo;
import com.robobank.pojo.ResponseException;
import com.robobank.pojo.TransactionStatus;
import com.robobank.service.exception.DaoException;
import com.robobank.service.exception.InsufficientFundsException;
import com.robobank.service.exception.InvalidInputException;
import com.robobank.service.impl.PaymentServiceDelegate;

@Path("/")
public class PaymentService {

	// http://localhost:8080/robobank/processPayment
	// {"cardNumber" : "545453"}
	@POST
	@Path("/processPayment")
	@Consumes("application/json")
	@Produces("application/json")
	public Response processPayment(PaymentInfo paymentInfo) {

		System.out.println("Entered into processPayment :: PaymentService!!");
		System.out.println(paymentInfo.getCardNumber());
		System.out.println(paymentInfo.getHolderName());
		
		PaymentServiceDelegate paymentDelegate = new PaymentServiceDelegate();
		try {
			return paymentDelegate.processPayment(paymentInfo);			
		} catch(InvalidInputException iie) {
			ResponseException res = new ResponseException();
			res.setErrorCode(iie.getReasonCode());
			res.setErrorMessage(iie.getMessage());
			return Response.status(500).entity(res).build();
		} catch(InsufficientFundsException isf) {
			ResponseException res = new ResponseException();
			res.setErrorCode(isf.getReasonCode());
			res.setErrorMessage(isf.getMessage());
			return Response.status(500).entity(res).build();
		} catch(DaoException de) {
			ResponseException res = new ResponseException();
			res.setErrorCode(de.getReasonCode());
			res.setErrorMessage(de.getMessage());
			return Response.status(500).entity(res).build();
		}
	}

	// http://localhost:8080/robobank/processData?name=Raju
	@Path("/processData")
	@POST
	public Response processData(@QueryParam("name") String name) {
		return null;
	}

}

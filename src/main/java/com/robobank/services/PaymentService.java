package com.robobank.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.robobank.pojo.PaymentInfo;
import com.robobank.pojo.ResponseException;
import com.robobank.pojo.TransactionStatus;

@Path("/")
public class PaymentService {

	//http://localhost:8080/robobank/processPayment
	//{"cardNumber" : "545453"}
	@POST
	@Path("/processPayment")
	@Consumes("application/json")
	@Produces("application/json")
	public Response processPayment(PaymentInfo paymentInfo) {
		System.out.println(paymentInfo.getCardNumber());
		System.out.println(paymentInfo.getHolderName());
		//update payment details in database
		TransactionStatus status = new TransactionStatus();
		try {
			status.setAvailableBalance(5000);
			status.setStatus("Success ");
			status.setTransactionId(123456);
			String s = null;
			System.out.println(s.length());			
		} catch(ArithmeticException ae) {
			ResponseException res = new ResponseException();
			res.setErrorCode(001);
			res.setErrorMessage("Exception occured while doing mathemetical operation!!");
			return Response.status(500).entity(res).build();
		} catch(NullPointerException npe) {
			ResponseException res = new ResponseException();
			res.setErrorCode(002);
			res.setErrorMessage("Exception occured while accessing null variable!!");
			return Response.status(500).entity(res).build();
		}
		return Response.status(200).entity(status).build();
	}
	
	//http://localhost:8080/robobank/processData?name=Raju
	@Path("/processData")
	@POST
	public Response processData(@QueryParam("name") String name) {
		return null;
	}
	
}

package com.robobank.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.robobank.pojo.AccountInformation;
import com.robobank.pojo.PaymentInfo;
import com.robobank.pojo.ResponseException;
import com.robobank.pojo.TransactionStatus;
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
		AccountInformation accountInfo = paymentDelegate.processPayment(paymentInfo);
		
		if(accountInfo != null) {
			TransactionStatus status = new TransactionStatus();
			status.setAvailableBalance(accountInfo.getBalance()-paymentInfo.getAmt());
			status.setTransactionId(12234456);
			status.setStatus("Success");
			return Response.status(200).entity(status).build();
		} else {
			TransactionStatus status = new TransactionStatus();
			status.setTransactionId(56234456);
			status.setStatus("Failed");
			return Response.status(200).entity(status).build();
		}
	}

	// http://localhost:8080/robobank/processData?name=Raju
	@Path("/processData")
	@POST
	public Response processData(@QueryParam("name") String name) {
		return null;
	}

}

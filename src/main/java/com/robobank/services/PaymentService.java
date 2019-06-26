package com.robobank.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.robobank.pojo.PaymentInfo;
import com.robobank.pojo.TransactionStatus;

@Path("/")
public class PaymentService {

	@POST
	@Path("/processPayment")
	@Consumes("application/json")
	@Produces("application/json")
	public Response processPayment(PaymentInfo paymentInfo) {
		System.out.println(paymentInfo.getCardNumber());
		System.out.println(paymentInfo.getHolderName());
		//update payment details in database
		
		TransactionStatus status = new TransactionStatus();
		status.setAvailableBalance(5000);
		status.setStatus("Success ");
		status.setTransactionId(123456);
		return Response.status(200).entity(status).build();
	}
	
}

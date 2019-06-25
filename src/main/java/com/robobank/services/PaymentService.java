package com.robobank.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.robobank.pojo.PaymentInfo;

@Path("/")
public class PaymentService {

	@POST
	@Path("/processPayment")
	@Consumes("application/json")
	public Response processPayment(PaymentInfo paymentInfo) {
		System.out.println(paymentInfo.getCardNumber());
		System.out.println(paymentInfo.getHolderName());
		//update payment details in database
		return Response.status(200).entity("success ").build();
	}
	
}

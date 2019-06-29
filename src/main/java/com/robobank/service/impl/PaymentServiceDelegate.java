package com.robobank.service.impl;

import javax.ws.rs.core.Response;

import com.robobank.pojo.AccountInformation;
import com.robobank.pojo.PaymentInfo;
import com.robobank.pojo.ResponseException;
import com.robobank.pojo.TransactionStatus;
import com.robobank.service.dao.impl.PaymentDaoImpl;
import com.robobank.service.dao.interf.IPaymentDao;

public class PaymentServiceDelegate {

	public Response processPayment(PaymentInfo paymentInfo){
		IPaymentDao dao = new PaymentDaoImpl();
		AccountInformation accountInfo = dao.processPayment(paymentInfo);
		
		if(accountInfo == null) {
			System.out.println("Invalid Card number entered!! We don't have entered card details!!");
			ResponseException res = new ResponseException();
			res.setErrorCode(001);
			res.setErrorMessage("Invalid Card number entered!! We don't have entered card details!!");
			return Response.status(500).entity(res).build();
		}		
		if(!accountInfo.getCvv().equals(paymentInfo.getCvv())){
			System.out.println("Invalid cvv entered!!");
			ResponseException res = new ResponseException();
			res.setErrorCode(002);
			res.setErrorMessage("Invalid cvv entered!!");
			return Response.status(500).entity(res).build();
		}
		
		if(!accountInfo.getExpDate().equals(paymentInfo.getExpDate())){
			System.out.println("Invalid expiry date entered!!");
			ResponseException res = new ResponseException();
			res.setErrorCode(003);
			res.setErrorMessage("Invalid expiry date entered!!");
			return Response.status(500).entity(res).build();
		}
		if(accountInfo.getBalance() < paymentInfo.getAmt()) {
			System.out.println("Insufficient funds in account!!");
			ResponseException res = new ResponseException();
			res.setErrorCode(004);
			res.setErrorMessage("Insufficient funds in account!!");
			return Response.status(500).entity(res).build();
		}
		
		TransactionStatus trans = new TransactionStatus();
		double remainingBalance = accountInfo.getBalance() - paymentInfo.getAmt();
		trans.setAvailableBalance(remainingBalance);
		trans.setStatus("Success");
		trans.setTransactionId(12345);
		
		return Response.status(200).entity(trans).build();
	}
}

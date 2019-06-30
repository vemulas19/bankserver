package com.robobank.service.impl;

import javax.ws.rs.core.Response;

import com.robobank.pojo.AccountInformation;
import com.robobank.pojo.PaymentInfo;
import com.robobank.pojo.ResponseException;
import com.robobank.pojo.TransactionStatus;
import com.robobank.service.dao.impl.PaymentDaoImpl;
import com.robobank.service.dao.interf.IPaymentDao;
import com.robobank.service.exception.InsufficientFundsException;
import com.robobank.service.exception.InvalidInputException;

public class PaymentServiceDelegate {

	public Response processPayment(PaymentInfo paymentInfo){
		IPaymentDao dao = new PaymentDaoImpl();
		AccountInformation accountInfo = dao.processPayment(paymentInfo);
		
		if(accountInfo == null) {
			System.out.println("Invalid Card number entered!! We don't have entered card details!!");
			throw new InvalidInputException(001, "Invalid Card number entered!! We don't have entered card details!!");
		}		
		if(!accountInfo.getCvv().equals(paymentInfo.getCvv())){
			System.out.println("Invalid cvv entered!!");
			throw new InvalidInputException(002, "Invalid cvv entered!!");
		}
		
		if(!accountInfo.getExpDate().equals(paymentInfo.getExpDate())){
			System.out.println("Invalid expiry date entered!!");
			throw new InvalidInputException(003, "Invalid expiry date entered!!");
		}
		if(accountInfo.getBalance() < paymentInfo.getAmt()) {
			System.out.println("Insufficient funds in account!!");
			throw new InsufficientFundsException(004, "Insufficient funds in account!!");
		}
		
		TransactionStatus trans = new TransactionStatus();
		double remainingBalance = accountInfo.getBalance() - paymentInfo.getAmt();
		//update balance to database table(account_details)
		
		boolean isUpdateBalance = dao.updateBalance(accountInfo.getCardNumber(), remainingBalance);
		if(isUpdateBalance) {
			trans.setAvailableBalance(remainingBalance);
			trans.setStatus("Success");
			trans.setTransactionId(12345);			
		} else {
			trans.setAvailableBalance(accountInfo.getBalance());
			trans.setStatus("Failed");
			trans.setTransactionId(12346);
		}
			
		return Response.status(200).entity(trans).build();
	}
}

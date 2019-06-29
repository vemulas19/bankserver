package com.robobank.service.impl;

import com.robobank.pojo.AccountInformation;
import com.robobank.pojo.PaymentInfo;
import com.robobank.service.dao.impl.PaymentDaoImpl;
import com.robobank.service.dao.interf.IPaymentDao;

public class PaymentServiceDelegate {

	public AccountInformation processPayment(PaymentInfo paymentInfo){
		//validation implement
		//cardNumber validation
		if(paymentInfo.getCardNumber().length() != 12) {
			System.out.println("Entered invalid card details");
		}
		
		if(paymentInfo.getCvv().length() != 3) {
			System.out.println("Entered invalid cvv");
		}
		
		if(!paymentInfo.getExpDate().contains("/")) {
			System.out.println("Entered invalid expDate");
		}
		
		IPaymentDao dao = new PaymentDaoImpl();
		AccountInformation accountInfo = dao.processPayment(paymentInfo);
		return accountInfo;
	}
}

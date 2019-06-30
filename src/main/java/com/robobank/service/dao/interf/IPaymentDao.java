package com.robobank.service.dao.interf;

import com.robobank.pojo.AccountInformation;
import com.robobank.pojo.PaymentInfo;

public interface IPaymentDao {

	public AccountInformation processPayment(PaymentInfo paymentInfo);
	
	public boolean updateBalance(String cardNumber, double remainingBalance);
}

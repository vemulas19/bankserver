package com.robobank.service.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import com.robobank.pojo.AccountInformation;
import com.robobank.pojo.PaymentInfo;
import com.robobank.service.dao.interf.IPaymentDao;

@Repository
public class PaymentDaoImpl implements IPaymentDao {

	public AccountInformation processPayment(PaymentInfo paymentInfo) {
		// get balance details from database
		
		Configuration configure = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sf = configure.buildSessionFactory();
		Session session = sf.openSession();
		
		Query query = session.createQuery("from AccountInformation where cardNumber = :cardNumber");
		query.setParameter("cardNumber", paymentInfo.getCardNumber());
		List<AccountInformation> list = query.list();
		if(!list.isEmpty()) {
			System.out.println("Balance in account : " + list.get(0).getBalance());
			return list.get(0);
		}
		return null;
	}

}

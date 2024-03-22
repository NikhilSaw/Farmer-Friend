package com.jsp.FarmerFriend_Team05.dao;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.FarmerFriend_Team05.entity.Payment;
import com.jsp.FarmerFriend_Team05.repo.PaymentRepo;

@Repository
public class PaymentDao {

	@Autowired
	private PaymentRepo paymentRepo;

	public Payment savePayment(Payment payment) {
		return paymentRepo.save(payment);
	}

	public Payment updatePayment(Payment payment) {
		Optional<Payment> db = paymentRepo.findById(payment.getId());
		if (db.isPresent()) {
			Payment p = db.get();
			if (payment.getMode() != null)
				p.setMode(payment.getMode());
			p.setPaymentTime(LocalDateTime.now());
			if (payment.getAmount() != 0)
				p.setAmount(payment.getAmount());
			return paymentRepo.save(p);
		} else {
			return null;
		}
	}

}

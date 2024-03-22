package com.jsp.FarmerFriend_Team05.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.FarmerFriend_Team05.entity.Payment;

public interface PaymentRepo extends JpaRepository<Payment, String> {

}

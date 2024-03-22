package com.jsp.FarmerFriend_Team05.service;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.FarmerFriend_Team05.dao.EquipmentDao;
import com.jsp.FarmerFriend_Team05.dao.PaymentDao;
import com.jsp.FarmerFriend_Team05.dao.RentalDao;
import com.jsp.FarmerFriend_Team05.entity.Equipment;
import com.jsp.FarmerFriend_Team05.entity.Payment;
import com.jsp.FarmerFriend_Team05.entity.Rental;
import com.jsp.FarmerFriend_Team05.exception.EquipmentNotFoundException;
import com.jsp.FarmerFriend_Team05.util.ResponseStructure;

@Service
public class RentalService {

	@Autowired
	private RentalDao dao;
	@Autowired
	private PaymentDao paymentDao;
	@Autowired
	private EquipmentDao equipmentDao;
	
	public ResponseEntity<ResponseStructure<Rental>> saveRentalDetails(String equipmentId, Rental rental) {
	    Equipment equipment = equipmentDao.fetchEquipmentById(equipmentId);
	    ResponseStructure<Rental> response = new ResponseStructure<>();
	    LocalDateTime startTime = rental.getStartTime();
	    LocalDateTime endTime = rental.getEndTime();
	    if (startTime.isAfter(endTime)) {
	        throw new IllegalArgumentException("Rental Start Time Cannot be after End Time");
	    }
	    if (equipment != null) {
	        Duration duration = Duration.between(startTime, endTime);
	        long hours = duration.toHours();
	        double amount = equipment.getCostPerHour() * hours;
	        Payment payment = new Payment();
	        payment.setAmount(amount);
	        rental.setPayment(payment);
	        paymentDao.savePayment(payment);
	        response.setData(dao.saveRental(rental));
	        response.setMessage("Rental Details Saved Successfully.");
	        response.setStatus(HttpStatus.OK.value());
	        return new ResponseEntity<ResponseStructure<Rental>>(response, HttpStatus.OK);
	    } else {
	        throw new EquipmentNotFoundException("Equipment Not Found with the given Id = " + equipmentId);
	    }
	}

}

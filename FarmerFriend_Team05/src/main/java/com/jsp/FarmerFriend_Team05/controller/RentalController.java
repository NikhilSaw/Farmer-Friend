package com.jsp.FarmerFriend_Team05.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.FarmerFriend_Team05.entity.Rental;
import com.jsp.FarmerFriend_Team05.service.RentalService;
import com.jsp.FarmerFriend_Team05.util.ResponseStructure;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class RentalController {

	@Autowired
	private RentalService service;
	
	@PostMapping("/saveRental")
	public ResponseEntity<ResponseStructure<Rental>> saveRentalDetails(@RequestParam String equipmentId, @RequestBody Rental rental) {
		return service.saveRentalDetails(equipmentId, rental);
	}
	
	
	
}

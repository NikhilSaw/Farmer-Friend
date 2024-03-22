package com.jsp.FarmerFriend_Team05.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.FarmerFriend_Team05.entity.Rental;
import com.jsp.FarmerFriend_Team05.repo.RentalRepo;

@Repository
public class RentalDao {

	@Autowired
	private RentalRepo rentalRepo;

	public Rental saveRental(Rental rental) {
		return rentalRepo.save(rental);
	}

}

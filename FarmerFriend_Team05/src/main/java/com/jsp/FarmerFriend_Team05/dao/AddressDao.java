package com.jsp.FarmerFriend_Team05.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.FarmerFriend_Team05.entity.Address;
import com.jsp.FarmerFriend_Team05.repo.AddressRepo;

@Repository
public class AddressDao {

	@Autowired
	private AddressRepo repo;
	
	public Address saveAddress(Address address) {
		return repo.save(address);
	}
	
}

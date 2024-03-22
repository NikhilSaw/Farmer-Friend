package com.jsp.FarmerFriend_Team05.entity;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

	@Id
    @GeneratedValue(generator = "custom-id")
    @GenericGenerator(name = "custom-id", type = com.jsp.FarmerFriend_Team05.util.CustomIdGenerator.class)
    private String id;

	private String houseNum;
	private String street;
	private String landmark;
	private String mandal;
	private String district;
	private String state;
	private int pinCode;

}

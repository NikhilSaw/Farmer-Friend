package com.jsp.FarmerFriend_Team05.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rental {
	
	@Id
    @GeneratedValue(generator = "custom-id")
    @GenericGenerator(name = "custom-id", type = com.jsp.FarmerFriend_Team05.util.CustomIdGenerator.class)
    private String id;
	
	@NotNull(message = "Start Time Should Not be null")
	private LocalDateTime startTime;
	@NotNull(message = "End Time Should Not be null")
	private LocalDateTime endTime;
	
	@ManyToOne
	private Equipment equipment;
	
	@OneToOne
	private Payment payment;
}

package com.jsp.FarmerFriend_Team05.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

	@Id
    @GeneratedValue(generator = "custom-id")
    @GenericGenerator(name = "custom-id", type = com.jsp.FarmerFriend_Team05.util.CustomIdGenerator.class)
    private String id;

	private String caption;
	private String location;
	private LocalDateTime dateTime;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Image image;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Comment> comments;
	
}

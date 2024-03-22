package com.jsp.FarmerFriend_Team05.entity;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {

	@Id
    @GeneratedValue(generator = "custom-id")
    @GenericGenerator(name = "custom-id", type = com.jsp.FarmerFriend_Team05.util.CustomIdGenerator.class)
    private String id;

	private String imageName;
	private String type;
	@Lob
    @Column(columnDefinition="LONGBLOB")
	private byte[] image;
}

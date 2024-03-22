 package com.jsp.FarmerFriend_Team05.entity;

import java.util.List;

import org.hibernate.annotations.GenericGenerator;
import com.jsp.FarmerFriend_Team05.enums.UserType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
    @GeneratedValue(generator = "custom-id")
    @GenericGenerator(name = "custom-id", type = com.jsp.FarmerFriend_Team05.util.CustomIdGenerator.class)
    private String id;

	@NotNull(message = "First Name is Mandatory")
	@NotBlank(message = "First Name is Mandatory")
	private String firstName;

	@NotNull(message = "Last Name is Mandatory")
	@NotBlank(message = "Last Name is Mandatory")
	private String lastName;

	private String gender;
	private int age;

	private Long phone;

	@Column(unique = true)
	private String email;
	@NotBlank(message = "Password is Required")
	@Size(min = 8, max = 16, message = "Password Length must be between 8 to 16 Characters")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,16}$", message = "The Password must contain at least One Uppercase Letter, One Lowercase Letter, One number, and One Special Character. Additionally, it must have a Minimum Length of 8 Characters.")
	private String password;
	@Enumerated(EnumType.STRING)
	private UserType type;

	@OneToOne(cascade = CascadeType.ALL)
	private Image image;
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Post> post;

}

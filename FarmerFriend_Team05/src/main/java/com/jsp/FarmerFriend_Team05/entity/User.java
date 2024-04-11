package com.jsp.FarmerFriend_Team05.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.jsp.FarmerFriend_Team05.enums.UserType;
import com.jsp.FarmerFriend_Team05.util.CustomIdGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	@GenericGenerator(name = "user_seq", strategy = "com.jsp.FarmerFriend_Team05.util.CustomIdGenerator", parameters = {
			@Parameter(name = CustomIdGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CustomIdGenerator.VALUE_PREFIX_PARAMETER, value = "User"),
			@Parameter(name = CustomIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
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

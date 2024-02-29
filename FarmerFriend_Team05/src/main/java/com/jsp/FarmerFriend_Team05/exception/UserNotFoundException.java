package com.jsp.FarmerFriend_Team05.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserNotFoundException extends RuntimeException {
	private String message;
}

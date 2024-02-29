package com.jsp.FarmerFriend_Team05.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordMismatchException extends RuntimeException{
	private String message;
}

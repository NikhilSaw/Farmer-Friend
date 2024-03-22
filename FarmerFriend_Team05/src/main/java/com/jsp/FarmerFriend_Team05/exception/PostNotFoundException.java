package com.jsp.FarmerFriend_Team05.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostNotFoundException extends RuntimeException{
	private String message;
}

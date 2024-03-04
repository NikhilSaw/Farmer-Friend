package com.jsp.FarmerFriend_Team05.util;

import lombok.Data;

@Data
public class ResponseStructure<T> {

	private T data;
	private String message;
	private int status;
	
}
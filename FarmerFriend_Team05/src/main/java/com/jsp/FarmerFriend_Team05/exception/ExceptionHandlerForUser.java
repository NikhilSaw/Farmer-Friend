package com.jsp.FarmerFriend_Team05.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.FarmerFriend_Team05.util.ResponseStructure;

@RestControllerAdvice
public class ExceptionHandlerForUser {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> userNotFound(UserNotFoundException ex) {
		ResponseStructure<String> m = new ResponseStructure<String>();
		m.setData("Sorry ! :( User Not Found");
		m.setMessage(ex.getMessage());
		m.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(m, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(EmailAlreadyRegisteredException.class)
	public ResponseEntity<ResponseStructure<String>> emailAlreadyPresent(EmailAlreadyRegisteredException ex) {
		ResponseStructure<String> m = new ResponseStructure<String>();
		m.setData("Sorry ! :( Given Email Already Present User Diffrent Email to Register");
		m.setMessage(ex.getMessage());
		m.setStatus(HttpStatus.UNAUTHORIZED.value());
		return new ResponseEntity<ResponseStructure<String>>(m, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(EmailNotSendException.class)
	public ResponseEntity<ResponseStructure<String>> emailNotSend(EmailNotSendException ex) {
		ResponseStructure<String> m = new ResponseStructure<String>();
		m.setData("Sorry ! :( Can't able to Send the Welcome Mail");
		m.setMessage(ex.getMessage());
		m.setStatus(HttpStatus.UNAUTHORIZED.value());
		return new ResponseEntity<ResponseStructure<String>>(m, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(PasswordMismatchException.class)
	public ResponseEntity<ResponseStructure<String>> passwordMismatch(PasswordMismatchException ex){
		ResponseStructure<String> m = new ResponseStructure<String>();
		m.setData("Please Match the Given Password with Confirm Password!");
		m.setMessage(ex.getMessage());
		m.setStatus(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<ResponseStructure<String>>(m, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ImageUploadException.class)
	public ResponseEntity<ResponseStructure<String>> imageUploadEx(ImageUploadException ex){
		ResponseStructure<String> m = new ResponseStructure<String>();
		m.setData("Error Occured while Uploading the Image please Try Again!");
		m.setMessage(ex.getMessage());
		m.setStatus(HttpStatus.NOT_MODIFIED.value());
		return new ResponseEntity<ResponseStructure<String>>(m, HttpStatus.NOT_MODIFIED);
	}
	
	@ExceptionHandler(ImageNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> imageNotFound(ImageNotFoundException ex) {
		ResponseStructure<String> m = new ResponseStructure<String>();
		m.setData("Sorry ! :( Image Not Found");
		m.setMessage(ex.getMessage());
		m.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(m, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PostNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> postNotFound(PostNotFoundException ex) {
		ResponseStructure<String> m = new ResponseStructure<String>();
		m.setData("Sorry ! :( Post Not Found");
		m.setMessage(ex.getMessage());
		m.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(m, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CommentNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> commentNotFound(CommentNotFoundException ex) {
		ResponseStructure<String> m = new ResponseStructure<String>();
		m.setData("Sorry ! :( Comment Not Found");
		m.setMessage(ex.getMessage());
		m.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(m, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(EquipmentNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> equipmentNotFound(EquipmentNotFoundException ex) {
		ResponseStructure<String> m = new ResponseStructure<String>();
		m.setData("Sorry ! :( Equipment Not Found");
		m.setMessage(ex.getMessage());
		m.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(m, HttpStatus.NOT_FOUND);
	}
	
}

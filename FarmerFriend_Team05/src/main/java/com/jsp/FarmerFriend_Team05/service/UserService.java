package com.jsp.FarmerFriend_Team05.service;

import java.util.Random;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.MimeMessageHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.jsp.FarmerFriend_Team05.dao.UserDao;
import com.jsp.FarmerFriend_Team05.entity.User;
import com.jsp.FarmerFriend_Team05.exception.EmailAlreadyRegisteredException;
import com.jsp.FarmerFriend_Team05.exception.EmailNotSendException;
import com.jsp.FarmerFriend_Team05.exception.PasswordMismatchException;
import com.jsp.FarmerFriend_Team05.exception.UserNotFoundException;
import com.jsp.FarmerFriend_Team05.util.ResponseStructure;

@Service
public class UserService {

	@Autowired
	private UserDao dao;
	@Autowired
	private JavaMailSender javaMailSender;

	public ResponseEntity<ResponseStructure<User>> saveUser(User user) {
		ResponseStructure<User> rs = new ResponseStructure<User>();
		if (dao.fetchUserbyEmail(user.getEmail()) == null) {
			User db = dao.saveUser(user);
			rs.setData(db);
			rs.setMessage("User Registered Successfully !");
			rs.setStatus(HttpStatus.CREATED.value());

			try {
				MimeMessage message = javaMailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(message, true);

				helper.setFrom("farmerfriendteam05@gmail.com");
				helper.setTo(user.getEmail());
				helper.setSubject("🌾 Welcome to Farmer Friend! 🌾");

				String emailContent = "<html><body><div style='text-align: center;'>"
						+ "<img src='cid:emailBanner' alt='Farmer Friend Banner' style='display: block; margin: 0 auto;'> </div><br>\n\n"
						+ "<p>Dear <strong><em>" + db.getFirstName() + " " + db.getLastName()
						+ ",</em></strong></p>\n\n"
						+ "<p>Welcome to Farmer Friend! We are thrilled to have you join our community of farmers dedicated to sustainable agriculture and growth.</p>\n\n"
						+ "<p>Your registration is the first step towards a journey of discovery, empowerment, and support. Whether you're seeking valuable insights, connecting with fellow farmers, or exploring innovative solutions, Farmer Friend is here to assist you every step of the way.</p>\n\n"
						+ "<p>As you embark on this exciting adventure, remember that you're not alone. Our team is committed to providing you with the resources, guidance, and encouragement you need to thrive in your agricultural endeavors.</p>\n\n"
						+ "<p>Feel free to explore our platform, engage with other members, and share your experiences. Together, we can cultivate a brighter future for agriculture.</p>\n\n"
						+ "<p>Once again, welcome aboard!</p>\n\n"
						+ "<p><strong>Best regards,</strong><br>Team - 05<br>Farmer Friend</p> </body></html>";

				helper.setText(emailContent, true);
				ClassPathResource banner = new ClassPathResource("Images/Email-Banner.png");
				helper.addInline("emailBanner", banner);
				javaMailSender.send(message);
				return new ResponseEntity<ResponseStructure<User>>(rs, HttpStatus.CREATED);
			} catch (MessagingException e) {
				System.out.println(e);
				throw new EmailNotSendException("Failed to send the Mail!");
			}
		} else {
			throw new EmailAlreadyRegisteredException("Email Already Registered ! Try New Email or Go for Login");
		}
	}

	public ResponseEntity<ResponseStructure<User>> loginUser(String email, String password) {
		User db = dao.fetchUserbyEmail(email);
		if (db != null) {
			ResponseStructure<User> rs = new ResponseStructure<User>();
			if (db.getPassword().equals(password)) {
				rs.setData(db);
				rs.setMessage("Login Successfull!");
				rs.setStatus(HttpStatus.ACCEPTED.value());
				return new ResponseEntity<ResponseStructure<User>>(rs, HttpStatus.ACCEPTED);
			} else {
				throw new PasswordMismatchException(
						"Incorrect Password! Please make sure you have entered the correct password. If you've forgotten your password, you can reset it using the 'Forgot Password' option on the login page.");
			}
		} else {
			throw new UserNotFoundException("User Not Found with E-mail = " + email);
		}
	}

	public ResponseEntity<ResponseStructure<Integer>> sendOtp(String email) {
		User db = dao.fetchUserbyEmail(email);
		if (db != null) {
			Random random = new Random();
			int otp = 100000 + random.nextInt(900000);
			ResponseStructure<Integer> rs = new ResponseStructure<Integer>();
			try {
				MimeMessage message = javaMailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(message, true);
				helper.setFrom("farmerfriendteam05@gmail.com");
				helper.setTo(email);
				helper.setSubject("Forgot Password OTP Verification!");
				String emailContent = "<html><body><div style='text-align: center;'><img src='cid:fpBanner' alt='Forgot Password Banner' style='display: block; margin: 0 auto;'> </div><br>\n\n"
						+ "<p>Dear <strong><em>" + db.getFirstName() + " " + db.getLastName()
						+ ",</em></strong></p>\n\n"
						+ "<p>You have requested to reset your password for Farmer Friend. To proceed, please use the following OTP verification code:</p>\n\n"
						+ "<p><strong>OTP:</strong> " + otp + " </p>\n\n"
						+ "<p>Please enter this OTP on the password reset page to verify your identity and continue with the password reset process. This OTP is valid for a single use and will expire shortly.</p>\n\n"
						+ "<p>If you did not request this password reset or have any concerns, please disregard this message.</p>\n\n"
						+ "<p>Thank you for using Farmer Friend.</p>\n\n"
						+ "<p><strong>Best regards,</strong><br>Team - 05<br>Farmer Friend</p> </body></html>";
				helper.setText(emailContent, true);
				ClassPathResource banner = new ClassPathResource("Images/ForgotPassword-Banner.png");
				helper.addInline("fpBanner", banner);
				javaMailSender.send(message);
				
				rs.setData(otp);
				rs.setMessage("OTP Sent Successfully");
				rs.setStatus(HttpStatus.OK.value());
				return new ResponseEntity<ResponseStructure<Integer>>(rs, HttpStatus.OK);
			} catch (MessagingException e) {
				System.out.println(e);
				throw new EmailNotSendException("Failed to Send the Otp Mail!");
			}
		}
		throw new UserNotFoundException("User Not Found with E-mail = " + email);
	}

	public ResponseEntity<ResponseStructure<User>> fetchUser(int id) {
		ResponseStructure<User> m = new ResponseStructure<User>();
		User user = dao.fetchUserById(id);
		if (user != null) {
			m.setData(user);
			m.setMessage("User Fetched Successfully !");
			m.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(m, HttpStatus.FOUND);
		} else
			throw new UserNotFoundException("User Not Found with ID = " + id);
	}

	public ResponseEntity<ResponseStructure<User>> deleteUser(int id) {
		ResponseStructure<User> rs = new ResponseStructure<User>();
		User user = dao.deleteUserById(id);
		if (user != null) {
			rs.setData(user);
			rs.setMessage("User Deleted Successfully !");
			rs.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(rs, HttpStatus.FOUND);
		} else
			throw new UserNotFoundException("User Not Found with ID = " + id);
	}

	public ResponseEntity<ResponseStructure<User>> updateUser(User user) {
		ResponseStructure<User> rs = new ResponseStructure<User>();
		User db = dao.fetchUserById(user.getId());
		if (db != null) {
			rs.setData(dao.updateUser(user));
			rs.setMessage("User Updated Successfully !");
			rs.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(rs, HttpStatus.FOUND);
		} else
			throw new UserNotFoundException("User Not Found with ID = " + user.getId());
	}

}

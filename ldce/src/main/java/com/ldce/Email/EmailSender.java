package com.ldce.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.ldce.Main.Token;

@Component
public class EmailSender {
	private JavaMailSender javaMailSender;

	@Autowired
	public EmailSender(JavaMailSender javaMailSender) {
		super();
		this.javaMailSender = javaMailSender;
	}

	@Async
	public void sendEmail(SimpleMailMessage mail) {
		javaMailSender.send(mail);
	}

	@Async
	public void createMail(String email, String tokenValue) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(email);
		mailMessage.setSubject("Complete Registration!");
		mailMessage.setFrom("iammavani25@gmail.com");
		mailMessage.setText("To confirm your account, please click here : "
				+ "http://192.168.0.109:8080/confirm-account?token=" + tokenValue);

		sendEmail(mailMessage);
	}
	@Async
	public void createResetPasswordMail(String email,String username,String password) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(email);
		mailMessage.setSubject("Reset Ldce Password!");
		mailMessage.setFrom("iammavani25@gmail.com");
		mailMessage.setText("Please Login With this Username And Password And Change Your"+"\nusername : "+username+"\npassword : "+password);
		sendEmail(mailMessage);
	}

}

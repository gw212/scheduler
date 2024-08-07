package com.gw.scheduler.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService
{


	public EmailService()
	{

	}

//	public void sendEmail(String to, String subject, String replyTo, String body)
//	{
//		SimpleMailMessage message = new SimpleMailMessage();
//		message.setTo(to);
//		message.setSubject(subject);
//		message.setText(body);
//		message.setReplyTo(replyTo);
//		mailSender.send(message);
//	}

	public void sendEmailFake(String to, String subject, String replyTo, String body)
	{
		log.debug("SMTP Server not set up so this is called instead to act as if an email is sent");
	}
}
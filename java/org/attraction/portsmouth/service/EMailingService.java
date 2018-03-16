package org.attraction.portsmouth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EMailingService {
	@Autowired
	private MailSender mail;

	public void sendEmailMsg(String sbj, String body,String to, String from ) {

		final SimpleMailMessage emailMsg = new SimpleMailMessage();
		
		emailMsg.setFrom(from);
		emailMsg.setTo(to);
		emailMsg.setSubject(sbj);
		emailMsg.setText(body);
		Thread thread = new Thread() {
			public void run() {
				mail.send(emailMsg);
			}
		};

		thread.start();

	}

}

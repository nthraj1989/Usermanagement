package com.razz.utils;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.razz.pojo.UserAccountDtl;

@Component
public class SendPasswordThroughEmail {

	@Autowired
	JavaMailSender javaMailSender;

	public boolean sendEmail(UserAccountDtl user) {

		try {

			MimeMessage mimeMsg = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMsg);

			helper.setTo(user.getEmail());
			helper.setSubject("Password");
			helper.setText(user.getPassword(), true);

			javaMailSender.send(mimeMsg);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}

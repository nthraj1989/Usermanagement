package com.razz.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.razz.pojo.UserAccountDtl;

@Component
public class EmailUtility {

	@Autowired
	JavaMailSender javaMailSender;

	public String readEmailBody(UserAccountDtl user) throws IOException {

		StringBuilder sb = new StringBuilder();

		FileReader fr = new FileReader("EMAIL-TEMPLATE.txt");
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();

		while (line != null) {
			sb.append(line);
			line = br.readLine();
		}

		br.close();

		String mailbody = sb.toString();
		mailbody = mailbody.replace("{FNAME}", user.getFirstName());
		mailbody = mailbody.replace("{LNAME}", user.getLastName());
		mailbody = mailbody.replace("{TEMP-PWD}", user.getPassword());
		mailbody = mailbody.replace("{EMAIL}", user.getEmail());
		return mailbody;
	}

	public boolean sendEmail(UserAccountDtl user) {

		// String body = "<h1>Hi This is email template send from application</h1>";

		try {

			MimeMessage mimeMsg = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMsg);

			helper.setTo(user.getEmail());
			helper.setSubject("Click the below link to unloack the account");
			helper.setText(readEmailBody(user), true);

			javaMailSender.send(mimeMsg);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}

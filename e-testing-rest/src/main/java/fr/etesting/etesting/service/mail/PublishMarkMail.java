package fr.etesting.etesting.service.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class PublishMarkMail {
	
	
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private MailContentBuilder mailContentBuilder;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public boolean sendMailPublishMarks(String studentFirstName, String studentLastName, String adminMail, String qcmName, double markQcm) {

		String recipientAddress = adminMail;
		MimeMessagePreparator messagePreparator = mimeMessage -> {

			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setTo(recipientAddress);
			messageHelper.setSubject("Notes d'un QCM");
			String content = mailContentBuilder.buildPublishMarks(studentFirstName, studentLastName, qcmName, markQcm);
			messageHelper.setText(content, true);
		};

		try {
			this.mailSender.send(messagePreparator);
			logger.error("Le mail a été envoyé");
			return true;
		} catch (MailException ex) {
			logger.error("Le mail n'a pas pu être envoyé");
			return false;
		}
	}

}
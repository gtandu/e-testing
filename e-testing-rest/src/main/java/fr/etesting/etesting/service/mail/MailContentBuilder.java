package fr.etesting.etesting.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailContentBuilder {

	@Autowired
	private TemplateEngine templateEngine;
	
	public String buildPublishMarks(String studentFirstName, String studentLastName, String qcmName, double markQcm){
		Context context = new Context();
		context.setVariable("studentFirstName", studentFirstName);
		context.setVariable("studentLastName", studentLastName);
		context.setVariable("qcmName", qcmName);
		context.setVariable("markQcm", markQcm);
		return templateEngine.process("publicationNoteTemplate", context);
	}
}


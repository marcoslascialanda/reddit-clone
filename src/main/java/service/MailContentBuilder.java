package service;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MailContentBuilder {

	
	public MailContentBuilder() {
		
	}

	private final TemplateEngine templateEngine = new TemplateEngine();
	
	String build (String message) {
		Context context = new Context();
		context.setVariable("message", message);
		
		return templateEngine.process("mailTemplate", context);
				
		
	}
	
	
}

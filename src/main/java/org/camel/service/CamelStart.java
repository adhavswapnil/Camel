package org.camel.service;

import java.util.Locale;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.processor.ThrowExceptionProcessor;
import org.camel.router.IntegrationRoute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class CamelStart {

	private static final Logger logger = LoggerFactory.getLogger(CamelStart.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/copy-files", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		try {
			CamelContext context = new DefaultCamelContext();
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
			context.addComponent("activemq", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
			context.addRoutes(new IntegrationRoute());
			context.start();

			Thread.sleep(3000);
			context.stop();
		} catch (Throwable err) {
			err.printStackTrace();
		}
		return "home";
	}

}

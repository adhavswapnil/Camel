package org.camel.router;

import org.apache.camel.builder.RouteBuilder;
import org.camel.process.LoggingProcessor;
import org.camel.transform.TransformationBean;

public class IntegrationRoute extends RouteBuilder {
	@Override
	public void configure() throws Exception {

		System.out.println("Test");
		from("activemq:queue:camelInput").process(new LoggingProcessor()).bean(new TransformationBean())
				.to("activemq:queue:camelOutput").to("activemq:queue:camelOutput2");

	}
}

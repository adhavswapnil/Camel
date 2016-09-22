package org.camel.process;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class LoggingProcessor implements Processor {
	@Override
	public void process(Exchange exchange) throws Exception {

		for (int i = 0; i < 4; i++) {

		}
	}
}

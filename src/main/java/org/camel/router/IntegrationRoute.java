package org.camel.router;

import org.apache.camel.builder.RouteBuilder;
import org.camel.process.LoggingProcessor;
import org.camel.transform.TransformationBean;

public class IntegrationRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
  
            
        from("activemq:queue:camelInput").
        
        
       /* sbbbhbdhf
        dsfd
        'dfkmdffhfuhyodffg]\dsfd][fkdifghuhohdfgffddsf'\]''
                fbsdhufsf*/
        process(new LoggingProcessor()).
        bean(new TransformationBean(), "makeUpperCase").
        to("activemq:queue:camelOutput").to("activemq:queue:camelOutput2");

    }
}

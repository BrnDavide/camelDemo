package com.camel.demo.exceptions;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteConfigurationBuilder;
import org.apache.camel.support.processor.PredicateValidationException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class CentralizedExceptions extends RouteConfigurationBuilder {

    @Override
    public void configuration() throws Exception {
        routeConfiguration()
                .onException(PredicateValidationException.class)
                .handled(true)
                .to("{{camel.endpoint.log.error}}")
                .process(new GenerateMessagePredicateValidationException())
                .removeHeaders("*")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(400))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"));

        routeConfiguration()
                .onException(Exception.class)
                .handled(true)
                .to("{{camel.endpoint.log.error}}")
                .removeHeaders("*")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(500))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .setBody(constant("Errore"));
    }
}

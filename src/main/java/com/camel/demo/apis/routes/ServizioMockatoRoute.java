package com.camel.demo.apis.routes;

import com.camel.demo.constants.Constants;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ServizioMockatoRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from(Constants.DIRECT_SERVIZIO_MOCKATO)
           .routeId(Constants.DIRECT_SERVIZIO_MOCKATO)
            //Setto qui tipo chiamata
           .setHeader(Exchange.HTTP_METHOD, constant("GET"))
           .toD("{{camel.endpoint.servizioMockato}}?codiceCliente=${header.codiceCliente}")
        .end();
    }
}

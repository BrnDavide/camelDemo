package com.camel.demo.apis.routes.clienti;

import com.camel.demo.beans.request.ClienteRequest;
import com.camel.demo.constants.Constants;
import com.camel.demo.predicates.ValidateCFClientePredicate;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class InserisciClienteRoute extends RouteBuilder {

    @Override
    public void configure() {

        from(Constants.DIRECT_INSERISCI_CLIENTE)
        .routeId(Constants.DIRECT_INSERISCI_CLIENTE)
           .validate(new ValidateCFClientePredicate())
           .setBody(new ClienteRequest())
           .log("Entrato in rotta Inserisci Cliente")
           .convertBodyTo(Map.class)
           .to("{{camel.endpoint.sql.inserisciCliente}}")
           .enrich("{{camel.endpoint.sql.recuperaDatiCliente}}")
        .end();
    }
}

package com.camel.demo.apis.routes.pratiche;

import com.camel.demo.aggregation.EsisteClienteAggregation;
import com.camel.demo.beans.request.PraticaRequest;
import com.camel.demo.constants.Constants;
import com.camel.demo.predicates.EsisteClientePredicate;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Qui:
 * 1. Controllo di inserire una pratica per un cliente esistente
 * 2. Inserisco pratica settando lo stato a RICEVUTA
 * 3. Indirizzo all'elaborazione per chiamare servizio mockato - validare - inserire in ESITI_ELABORAZIONI
 */
@Component
public class InserisciPraticaRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from(Constants.DIRECT_INSERISCI_PRATICA)
           .routeId(Constants.DIRECT_INSERISCI_PRATICA)
                .log(LoggingLevel.INFO,"Entrato in rotta Inserisci Pratica")
                .setHeader("codiceCliente", simple("${body.codiceContraente}"))
                .enrich(Constants.DIRECT_ESISTE_CLIENTE, new EsisteClienteAggregation())
                .validate(new EsisteClientePredicate())
                .setBody(new PraticaRequest())
                .convertBodyTo(Map.class)
                .to("{{camel.endpoint.sql.inserisciPratica}}")
                .log(LoggingLevel.INFO,"Esco da inserisci Pratica")
                .setProperty("codicePratica", simple("${body.codicePratica}"))// mi prendo il codice della pratica
                .log(LoggingLevel.INFO,"Entrato in rotta Elabora Pratica con body: ${body} ")
                .to(Constants.DIRECT_ELABORA_PRATICA)//qui posso chiamare una rotta di elab e inserire il servizio mockato. es: codice pratica pari ok/dispari ko
                .setHeader("codicePratica", exchangeProperty("codicePratica"))
                .enrich("{{camel.endpoint.sql.recuperaPratica}}")
                .convertBodyTo(Map.class)
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(201))
        .end();
    }
}

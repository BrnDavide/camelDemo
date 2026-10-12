package com.camel.demo.apis.routes.pratiche;

import com.camel.demo.aggregation.ClienteAggregation;
import com.camel.demo.aggregation.ServizioMockatoAggregation;
import com.camel.demo.constants.Constants;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Vengo indirizzato qui dalla pratica.
 * Poi recupero la pratica
 */
@Component
public class ElaboraPraticaRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from(Constants.DIRECT_ELABORA_PRATICA)
        .routeId(Constants.DIRECT_ELABORA_PRATICA)
                .log("Entrato nell'elaborazione pratica: ${body}")
                .setHeader("codicePratica", constant(exchangeProperty("codicePratica"))) //Property che si porta dietro
                .enrich(Constants.DIRECT_RECUPERA_CLIENTE, new ClienteAggregation()) //Mi prendo il cliente riutilizzando rotta e lo setto nella risposta.
                .enrich(Constants.DIRECT_SERVIZIO_MOCKATO, new ServizioMockatoAggregation()) //Chiamo il servizio mockato
                //Il servizio restituisce OK o KO, lo salvo
                .setHeader("esito", exchangeProperty("esito"))
                //MI serve che il risultato sia COMPLETATA / ERRORE
                //Uso una choice(if) che setta gli header da passare poi alle queries sia di aggiornamento pratica sia di inserimento esito
                .choice()
                .when(exchangeProperty("esito").isEqualTo("OK"))
                    .setHeader("statoPratica", constant("COMPLETATA"))
                .otherwise()
                    .setHeader("statoPratica", constant("ERRORE"))
                    .setHeader("messaggioErrore", constant("Cliente non abilitato all'inserimento della pratica"))
                .end()
                .to("{{camel.endpoint.sql.aggiornaStatoPratica}}")
                .to("{{camel.endpoint.sql.inserisciEsito}}")
        .end();


    }
}

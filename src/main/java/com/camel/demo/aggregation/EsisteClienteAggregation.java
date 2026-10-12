package com.camel.demo.aggregation;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;

/**
 * Interrogo se esista un cliente e setto header con codice cliente
 */
public class EsisteClienteAggregation implements AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        if (newExchange == null) {
            return oldExchange;
        }
        Integer numeroCountClienti = newExchange.getIn().getBody(Integer.class);
        // Nota: Qui setto nell'header il num count clienti, ché mi servirà per il check.
        oldExchange.getIn().setHeader("numeroCountClienti", numeroCountClienti);
        return oldExchange;
    }
}

package com.camel.demo.aggregation;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;

import java.util.Map;

public class ServizioMockatoAggregation implements AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        if (newExchange == null) {
            return oldExchange;
        }
        // La chiamata mockata risponde body:"OK/KO"
        String esito = newExchange.getIn().getBody(String.class);
        oldExchange.setProperty("esito", esito);
        return oldExchange;
    }
}

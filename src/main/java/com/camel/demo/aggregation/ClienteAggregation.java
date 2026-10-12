package com.camel.demo.aggregation;

import com.camel.demo.datasource.beans.CLIENTI;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;

public class ClienteAggregation implements AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        if(oldExchange == null){
            return newExchange;
        }
        CLIENTI cliente = newExchange.getIn().getBody(CLIENTI.class);

        oldExchange.setProperty("cliente", cliente);
        return oldExchange;
    }
}

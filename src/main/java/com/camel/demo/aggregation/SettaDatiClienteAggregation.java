package com.camel.demo.aggregation;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;

public class SettaDatiClienteAggregation implements AggregationStrategy {

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        if (newExchange == null) {
            return oldExchange;
        }
        return null;
    }
}

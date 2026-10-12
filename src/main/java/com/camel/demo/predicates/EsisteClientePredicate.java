package com.camel.demo.predicates;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;

public class EsisteClientePredicate implements Predicate {
    @Override
    public boolean matches(Exchange exchange) {
        Integer numeroCountClienti = exchange.getIn().getHeader("numeroCountClienti", Integer.class);
        return numeroCountClienti != null && numeroCountClienti> 0;
    }
}

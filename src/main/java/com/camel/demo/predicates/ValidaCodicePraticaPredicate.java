package com.camel.demo.predicates;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.apache.commons.lang3.math.NumberUtils;

public class ValidaCodicePraticaPredicate implements Predicate {
    @Override
    public boolean matches(Exchange exchange) {
        String codicePratica = exchange.getIn().getHeader("codicePratica", String.class);
        return NumberUtils.isParsable(codicePratica);
    }
}

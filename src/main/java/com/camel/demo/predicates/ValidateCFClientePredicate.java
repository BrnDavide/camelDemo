package com.camel.demo.predicates;

import com.camel.demo.beans.request.ClienteRequest;
import org.apache.camel.Exchange;
import org.apache.camel.Predicate;

public class ValidateCFClientePredicate implements Predicate {
    /**
     * Controllo iniziale su lunghezza cf
     * @param exchange
     * @return
     */
    @Override
    public boolean matches(Exchange exchange) {
        ClienteRequest req = exchange.getIn().getBody(ClienteRequest.class);
        if (req == null || req.getCf() == null) {
            return false;
        }
        return req.getCf().trim().length() == 16;
    }
}

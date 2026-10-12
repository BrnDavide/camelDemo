package com.camel.demo.predicates;


import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Verifico che il cod cliente inserito sia effettivamente un numero
 */
public class ValidateCodiceClientePredicate implements Predicate {
    @Override
    public boolean matches(Exchange exchange) {
        String codiceCliente = exchange.getIn().getHeader("codiceCliente", String.class);
        return NumberUtils.isParsable(codiceCliente);
    }
}

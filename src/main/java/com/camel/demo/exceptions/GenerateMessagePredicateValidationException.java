package com.camel.demo.exceptions;

import com.camel.demo.predicates.ValidateCFClientePredicate;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.support.processor.PredicateValidationException;

public class GenerateMessagePredicateValidationException implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        PredicateValidationException ex = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, PredicateValidationException.class);
        if(ex.getPredicate() instanceof ValidateCFClientePredicate){
            exchange.getIn().setBody("CF inserito non valido");
        } else {
            exchange.getIn().setBody("Dati non validi");
        }
    }
}

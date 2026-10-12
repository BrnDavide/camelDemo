package com.camel.demo.exceptions;

import com.camel.demo.beans.response.Errore;
import com.camel.demo.predicates.ValidaCodicePraticaPredicate;
import com.camel.demo.predicates.ValidateCFClientePredicate;
import com.camel.demo.predicates.ValidateCodiceClientePredicate;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.support.processor.PredicateValidationException;

public class GenerateMessagePredicateValidationException implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        PredicateValidationException ex = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, PredicateValidationException.class);
        if(ex.getPredicate() instanceof ValidateCFClientePredicate){
            exchange.getIn().setBody(new Errore("400","CF inserito non valido"));
        } else if(ex.getPredicate() instanceof ValidaCodicePraticaPredicate){
            exchange.getIn().setBody(new Errore("400","Codice pratica non valido"));
        } else if(ex.getPredicate() instanceof ValidateCodiceClientePredicate){
            exchange.getIn().setBody(new Errore("400","Codice cliente non valido"));
        } else {
            exchange.getIn().setBody(new Errore("400","Dati non validi"));
        }
    }
}

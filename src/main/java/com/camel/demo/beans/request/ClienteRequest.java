package com.camel.demo.beans.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.apache.camel.Exchange;
import org.apache.camel.Expression;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true, value={"codiceCliente"})
public class ClienteRequest implements Expression {

    private String nome;
    private String cognome;
    private String email;
    private Date dataDiNascita;
    private String cf;


    @Override
    public <T> T evaluate(Exchange exchange, Class<T> type) {
        ClienteRequest request = exchange.getIn().getBody(ClienteRequest.class);
        request.setCf(request.getCf());
        return type.cast(request);
    }
}

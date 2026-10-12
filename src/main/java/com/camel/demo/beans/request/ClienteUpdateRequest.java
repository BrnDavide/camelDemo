package com.camel.demo.beans.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.apache.camel.Exchange;
import org.apache.camel.Expression;

import java.util.Date;

/**
 * Qui portare codice cliente da header a body
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true, value={"codiceCliente"})
public class ClienteUpdateRequest implements Expression {

    private Integer codiceCliente;
    private String nome;
    private String cognome;
    private String email;
    private Date dataDiNascita;
    private String cf;

    @Override
    public <T> T evaluate(Exchange exchange, Class<T> type) {
        ClienteUpdateRequest request = exchange.getIn().getBody(ClienteUpdateRequest.class);
        String codiceCliente = exchange.getIn().getHeader("codiceCliente", String.class);
        request.setCodiceCliente(Integer.valueOf(codiceCliente));
        request.setCf(request.getCf());
        return type.cast(request);
    }
}

package com.camel.demo.beans.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.apache.camel.Exchange;
import org.apache.camel.Expression;

import java.math.BigDecimal;
import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PraticaRequest implements Expression {

    private BigDecimal importo;
    private String descrizione;
    private Integer codiceContraente;
    private Date dataSottoscrizione;
    private Date dataCancellazione;
    private String statoPratica;

    @Override
    public <T> T evaluate(Exchange exchange, Class<T> type) {
        PraticaRequest req = exchange.getIn().getBody(PraticaRequest.class);
        req.setStatoPratica("RICEVUTA");
        return type.cast(req);
    }
}

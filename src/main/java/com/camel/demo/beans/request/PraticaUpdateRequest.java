package com.camel.demo.beans.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.apache.camel.Exchange;
import org.apache.camel.Expression;

import java.math.BigDecimal;
import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true, value={"codicePratica"})
public class PraticaUpdateRequest implements Expression {
    private Integer codicePratica;
    private BigDecimal importo;
    private String descrizione;
    private Integer codiceContraente;
    private Date dataSottoscrizione;
    private Date dataCancellazione;
    private String statoPratica;

    @Override
    public <T> T evaluate(Exchange exchange, Class<T> type) {
        PraticaUpdateRequest request = exchange.getIn().getBody(PraticaUpdateRequest.class);
        String codicePratica = exchange.getIn().getHeader("codicePratica", String.class);
        request.setCodicePratica(Integer.valueOf(codicePratica));
        return type.cast(request);
    }
}

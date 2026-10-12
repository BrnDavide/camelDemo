package com.camel.demo.beans.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PraticaResponse {
    private Integer codicePratica;
    private BigDecimal importo;
    private String descrizione;
    private Integer codiceContraente;
    private Date dataSottoscrizione;
    private Date dataCancellazione;
    private String statoPratica;
}

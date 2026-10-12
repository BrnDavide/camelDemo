package com.camel.demo.datasource.beans;

import lombok.Data;

import java.util.Date;

@Data
public class PRATICHE {
    private Integer CODICE_PRATICA;
    private Integer IMPORTO;
    private String DESCRIZIONE;
    private Integer CODICE_CONTRAENTE;
    private Date DATA_SOTTOSCRIZIONE;
    private Date DATA_CANCELLAZIONE;
    private String STATO_PRATICA;
}

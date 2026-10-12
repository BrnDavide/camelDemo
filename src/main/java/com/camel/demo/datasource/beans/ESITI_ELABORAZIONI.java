package com.camel.demo.datasource.beans;

import lombok.Data;

import java.util.Date;

@Data
public class ESITI_ELABORAZIONI {
    private Integer CODICE_ESITO;
    private Integer CODICE_PRATICA;
    private String ESITO;
    private String MESSAGGIO_ERRORE;
    private String NOME_FILE;
    private Date DATA_ELABORAZIONE;
}

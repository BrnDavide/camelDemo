package com.camel.demo.datasource.beans;

import lombok.Data;

@Data
public class CLIENTI {
    private Integer CODICE_CLIENTE;
    private String NOME;
    private String COGNOME;
    private String DATA_DI_NASCITA;
    private String CF;
    private String EMAIL;
}

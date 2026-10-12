package com.camel.demo.constants;

import lombok.Data;

@Data
public class Constants {
    public static final String LOG_TAG = "camel.demo.apis";

    //Rotte
    public static final String DIRECT_INSERISCI_CLIENTE = "direct:inserisciCliente";
    public static final String DIRECT_RECUPERA_CLIENTE = "direct:recuperaCliente";
    public static final String DIRECT_ELIMINA_CLIENTE = "direct:eliminaCliente";
    public static final String DIRECT_AGGIORNA_CLIENTE = "direct:aggiornaCliente";
    public static final String DIRECT_ESISTE_CLIENTE = "direct:esisteCliente";
    public static final String DIRECT_INSERISCI_PRATICA= "direct:inserisciPratica";
    public static final String DIRECT_RECUPERA_PRATICA= "direct:recuperaPratica";
    public static final String DIRECT_ELIMINA_PRATICA= "direct:eliminaPratica";
    public static final String DIRECT_AGGIORNA_PRATICA = "direct:aggiornaPratica";
    public static final String DIRECT_ELABORA_PRATICA = "direct:elaboraPratica";
    public static final String DIRECT_SERVIZIO_MOCKATO = "direct:servizioMockato";
    public static final String DIRECT_SCRIVI_FILE= "direct:scriviFile";

}

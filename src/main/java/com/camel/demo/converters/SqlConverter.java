package com.camel.demo.converters;

import com.camel.demo.beans.request.ClienteRequest;
import com.camel.demo.beans.request.ClienteUpdateRequest;
import com.camel.demo.beans.request.PraticaRequest;
import com.camel.demo.beans.request.PraticaUpdateRequest;
import com.camel.demo.datasource.beans.CLIENTI;
import com.camel.demo.datasource.beans.PRATICHE;
import org.apache.camel.Converter;
import org.apache.camel.Exchange;

import java.util.HashMap;
import java.util.Map;

public class SqlConverter {

    @Converter
    public Map<String, Object> InserisciClienteReqConverter(ClienteRequest req, Exchange exchange) {
        Map<String,Object> map = new HashMap<>();
        map.put("nome",req.getNome());
        map.put("cognome",req.getCognome());
        map.put("dataDiNascita", req.getDataDiNascita());
        map.put("cf", req.getCf());
        map.put("email",req.getEmail());
        return map;
    }

    @Converter
    public Map<String, Object> RecuperaClienteRespConverter(CLIENTI dbTable, Exchange exchange) {
        Map<String,Object> map = new HashMap<>();
        map.put("codiceCliente",dbTable.getCODICE_CLIENTE());
        map.put("nome",dbTable.getNOME());
        map.put("cognome",dbTable.getCOGNOME());
        map.put("dataDiNascita", dbTable.getDATA_DI_NASCITA());
        map.put("cf", dbTable.getCF());
        map.put("email",dbTable.getEMAIL());
        return map;
    }

    @Converter
    public Map<String, Object> ClienteUpdateReqConverter(ClienteUpdateRequest req, Exchange exchange) {
        Map<String,Object> map = new HashMap<>();
        map.put("codiceCliente",req.getCodiceCliente());
        map.put("nome",req.getNome());
        map.put("cognome",req.getCognome());
        map.put("dataDiNascita", req.getDataDiNascita());
        map.put("cf", req.getCf());
        map.put("email",req.getEmail());
        return map;
    }

    @Converter
    public Map<String, Object> InserisciPraticaReqConverter(PraticaRequest req, Exchange exchange) {
        Map<String,Object> map = new HashMap<>();
        map.put("importo",req.getImporto());
        map.put("descrizione",req.getDescrizione());
        map.put("codiceContraente",req.getCodiceContraente());
        map.put("dataSottoscrizione", req.getDataSottoscrizione());
        map.put("data_cancellazione", req.getDataCancellazione());
        map.put("statoPratica", req.getStatoPratica());
        return map;
    }

    @Converter
    public Map<String, Object> RecuperaPraticaReqConverter(PraticaRequest req, Exchange exchange) {
        Map<String,Object> map = new HashMap<>();
        map.put("importo",req.getImporto());
        map.put("descrizione",req.getDescrizione());
        map.put("codiceContraente",req.getCodiceContraente());
        map.put("dataSottoscrizione", req.getDataSottoscrizione());
        map.put("data_cancellazione", req.getDataCancellazione());
        map.put("statoPratica", req.getStatoPratica());
        return map;
    }

    @Converter
    public Map<String, Object> RecuperaPraticaRespConverter(PRATICHE dbTable, Exchange exchange) {
        Map<String,Object> map = new HashMap<>();
        map.put("codicePratica",dbTable.getCODICE_PRATICA());
        map.put("importo",dbTable.getIMPORTO());
        map.put("descrizione",dbTable.getDESCRIZIONE());
        map.put("codiceContraente",dbTable.getCODICE_CONTRAENTE());
        map.put("dataSottoscrizione", dbTable.getDATA_SOTTOSCRIZIONE());
        map.put("dataCancellazione", dbTable.getDATA_CANCELLAZIONE());
        map.put("statoPratica", dbTable.getSTATO_PRATICA());
        return map;
    }

    @Converter
    public Map<String, Object> PraticaUpdateReqConverter(PraticaUpdateRequest req, Exchange exchange) {
        Map<String,Object> map = new HashMap<>();
        map.put("codicePratica",req.getCodicePratica());
        map.put("importo",req.getImporto());
        map.put("descrizione",req.getDescrizione());
        map.put("codiceContraente",req.getCodiceContraente());
        map.put("dataSottoscrizione", req.getDataSottoscrizione());
        map.put("data_cancellazione", req.getDataCancellazione());
        map.put("statoPratica", req.getStatoPratica());
        return map;
    }
}

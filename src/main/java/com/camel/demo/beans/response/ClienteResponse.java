package com.camel.demo.beans.response;

import lombok.Data;

import java.util.Date;

@Data
public class ClienteResponse{
    private Integer codiceCliente;
    private String nome;
    private String cognome;
    private String email;
    private Date dataDiNascita;
    private String cf;

}

package com.camel.demo.beans.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Errore {
    private String codice;
    private String messaggio;
}

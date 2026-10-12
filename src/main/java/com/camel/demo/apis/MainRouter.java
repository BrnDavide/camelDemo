package com.camel.demo.apis;

import com.camel.demo.converters.SqlConverter;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class MainRouter extends RouteBuilder {

    @Override
    public void configure(){
        //configurazione iniziale contract-first
        restConfiguration()
                .component("platform-http")
                .bindingMode(RestBindingMode.json)
                .bindingPackageScan("com.camel.demo.beans");

        rest().openApi()
                .specification("Apis.json")
                .missingOperation("ignore");

        //Converters
        getContext().getTypeConverterRegistry().addTypeConverters(new SqlConverter());

    }
}

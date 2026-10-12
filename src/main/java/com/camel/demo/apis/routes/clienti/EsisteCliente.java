package com.camel.demo.apis.routes.clienti;

import com.camel.demo.constants.Constants;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class EsisteCliente extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from(Constants.DIRECT_ESISTE_CLIENTE)
          .routeId(Constants.DIRECT_ESISTE_CLIENTE)
                .to("{{camel.endpoint.sql.esisteCliente}}")
        .end();
    }
}

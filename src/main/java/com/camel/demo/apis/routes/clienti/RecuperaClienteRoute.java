package com.camel.demo.apis.routes.clienti;

import com.camel.demo.constants.Constants;
import com.camel.demo.predicates.ValidateCodiceClientePredicate;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.sql.SqlConstants;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class RecuperaClienteRoute extends RouteBuilder {


    @Override
    public void configure() throws Exception {
        from(Constants.DIRECT_RECUPERA_CLIENTE)
        .routeId(Constants.DIRECT_RECUPERA_CLIENTE)
           .validate(new ValidateCodiceClientePredicate())
           .setHeader("codiceCliente", simple("${header.codiceCliente}", Integer.class))
           .to("{{camel.endpoint.sql.recuperaCliente}}")
           .convertBodyTo(Map.class)
           .choice()
           .when(header(SqlConstants.SQL_ROW_COUNT).isEqualTo(0))
                .log(LoggingLevel.WARN, "Cliente ${header.codiceCliente} non trovato")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(404))
           .otherwise()
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(204))
        .end();

    }
}

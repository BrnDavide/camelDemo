package com.camel.demo.apis.routes.clienti;

import com.camel.demo.beans.request.ClienteUpdateRequest;
import com.camel.demo.constants.Constants;
import com.camel.demo.predicates.ValidateCodiceClientePredicate;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.sql.SqlConstants;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AggiornaClienteRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from(Constants.DIRECT_AGGIORNA_CLIENTE)
          .routeId(Constants.DIRECT_AGGIORNA_CLIENTE)
                .log("Entrato in rotta Aggiorna Cliente")
                .validate(new ValidateCodiceClientePredicate())
                //.validate(new ValidateCFClientePredicate())
                .setBody(new ClienteUpdateRequest())
                .convertBodyTo(Map.class)
                .to("{{camel.endpoint.sql.aggiornaCliente}}")
                .choice()
                .when(header(SqlConstants.SQL_UPDATE_COUNT).isEqualTo(0))
                    .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(404))
                .otherwise()
                    .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200))
        .end();
    }
}

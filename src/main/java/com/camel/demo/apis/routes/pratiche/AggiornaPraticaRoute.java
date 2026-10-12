package com.camel.demo.apis.routes.pratiche;

import com.camel.demo.beans.request.PraticaUpdateRequest;
import com.camel.demo.constants.Constants;
import com.camel.demo.predicates.ValidaCodicePraticaPredicate;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.sql.SqlConstants;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AggiornaPraticaRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from(Constants.DIRECT_AGGIORNA_PRATICA)
        .routeId(Constants.DIRECT_AGGIORNA_PRATICA)
                .log(LoggingLevel.INFO, "Entro in aggiorna pratica: ${body}")
                .validate(new ValidaCodicePraticaPredicate())
                .setBody(new PraticaUpdateRequest())
                .convertBodyTo(Map.class)
                .to("{{camel.endpoint.sql.aggiornaPratica}}")
                .choice()
                    .when(header(SqlConstants.SQL_UPDATE_COUNT).isEqualTo(0))
                        .log(LoggingLevel.WARN, "Pratica ${header.codicePratica} non aggiornata")
                        .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(400))
                    .otherwise()
                        .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(204))
        .end();

    }
}

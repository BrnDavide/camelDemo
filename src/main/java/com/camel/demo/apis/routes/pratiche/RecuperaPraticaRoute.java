package com.camel.demo.apis.routes.pratiche;

import com.camel.demo.constants.Constants;
import com.camel.demo.predicates.ValidaCodicePraticaPredicate;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.sql.SqlConstants;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RecuperaPraticaRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from(Constants.DIRECT_RECUPERA_PRATICA)
                .routeId(Constants.DIRECT_RECUPERA_PRATICA)
                .validate(new ValidaCodicePraticaPredicate())
                .setHeader("codicePratica", simple("${header.codicePratica}", Integer.class))
                .to("{{camel.endpoint.sql.recuperaPratica}}")
                .convertBodyTo(Map.class)
                .choice()
                    .when(header(SqlConstants.SQL_ROW_COUNT).isEqualTo(0))
                        .log(LoggingLevel.WARN, "Pratica ${header.codicePratica} non trovata")
                        .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(404))
                    .otherwise()
                        .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(204))
        .end();
    }
}

package com.camel.demo.apis.routes.clienti;

import com.camel.demo.constants.Constants;
import com.camel.demo.predicates.ValidateCodiceClientePredicate;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.sql.SqlConstants;
import org.springframework.stereotype.Component;


@Component
public class EliminaClienteRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from(Constants.DIRECT_ELIMINA_CLIENTE)
            .routeId(Constants.DIRECT_ELIMINA_CLIENTE)
                .log(LoggingLevel.INFO,"Entro in rotta Elimina Cliente")
                .validate(new ValidateCodiceClientePredicate())
                .setHeader("codiceCliente", simple("${header.codiceCliente}", Integer.class))
                .to("{{camel.endpoint.sql.eliminaCliente}}")
                .choice()
                .when(header(SqlConstants.SQL_UPDATE_COUNT).isEqualTo(0))
                    .log(LoggingLevel.WARN, "Cliente ${header.codiceCliente} non trovato")
                    .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(404))
                .otherwise()
                    .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(204))
                .end();
    }

}

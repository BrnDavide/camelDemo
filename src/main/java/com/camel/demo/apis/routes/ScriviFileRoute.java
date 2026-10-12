package com.camel.demo.apis.routes;

import com.camel.demo.constants.Constants;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ScriviFileRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from(Constants.DIRECT_SCRIVI_FILE)
        .routeId(Constants.DIRECT_SCRIVI_FILE)
                .log("Entrato nella rotta Scrivi File")
                .setHeader("nomeFile", simple("Pratica_${header.codicePratica}_${date:now:yyyyMMddHHmmss}.txt"))
                .setHeader(Exchange.FILE_NAME, simple("${header.nomeFile}"))
                .to("{{camel.endpoint.file.output}}")
        .end();
    }
}

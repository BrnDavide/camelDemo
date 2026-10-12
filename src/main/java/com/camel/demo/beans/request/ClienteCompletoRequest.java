package com.camel.demo.beans.request;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.HashMap;
import java.util.Map;


public class ClienteCompletoRequest implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("cf", exchange.getIn().getBody(Map.class).get("cf"));
        exchange.getIn().setBody(map);
    }
}

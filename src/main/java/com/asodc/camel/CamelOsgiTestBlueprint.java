package com.asodc.camel;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

public class CamelOsgiTestBlueprint extends RouteBuilder {
    private static final String LOGGER = "com.asodc.camel.CamelOsgiTestBlueprint";

    private static final String VISITED_FIRST_ENDPOINT = "VisitedFirstEndpoint";
    private static final String VISITED_SECOND_ENDPOINT = "VisitedSecondEndpoint";
    private static final String VISITED_THIRD_ENDPOINT = "VisitedThirdEndpoint";

    private static final String SIMPLE_HEADERS_MESSAGE = "Headers: ${headers}";

    @SuppressWarnings("RedundantThrows")
    @Override
    public void configure() throws Exception {
        from("timer:timer?period=5000")
                .id("timer")
                .setHeader(VISITED_FIRST_ENDPOINT, constant(false))
                .setHeader(VISITED_SECOND_ENDPOINT, constant(false))
                .setHeader(VISITED_THIRD_ENDPOINT, constant(false))
                .log(LoggingLevel.INFO, LOGGER, SIMPLE_HEADERS_MESSAGE)
                .multicast()
                .parallelProcessing()
                .to("direct:firstEndpoint", "direct:secondEndpoint", "direct:thirdEndpoint");

        from("direct:firstEndpoint")
                .id("firstEndpoint")
                .setHeader(VISITED_FIRST_ENDPOINT, constant(true))
                .log(LoggingLevel.INFO, LOGGER, SIMPLE_HEADERS_MESSAGE);

        from("direct:secondEndpoint")
                .id("secondEndpoint")
                .setHeader(VISITED_SECOND_ENDPOINT, constant(true))
                .log(LoggingLevel.INFO, LOGGER, SIMPLE_HEADERS_MESSAGE);

        from("direct:thirdEndpoint")
                .id("thirdEndpoint")
                .setHeader(VISITED_THIRD_ENDPOINT, constant(true))
                .log(LoggingLevel.INFO, LOGGER, SIMPLE_HEADERS_MESSAGE);
    }
}

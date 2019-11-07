package osgi;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

public class CamelOsgiTestBlueprint extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("timer:start")
                .id("mainRoute")
                .log(LoggingLevel.INFO, "com.asodc.camel.osgi.CamelOsgiTestBlueprint", "Headers: ${headers}");
    }
}

package osgi;

import org.apache.camel.main.Main;

public class CamelOsgiTest {
    public static void main(String... args) throws Exception {
        Main main = new Main();
        // TODO: find out how to support MDC logging
//        CamelContext context = new DefaultCamelContext();
//        context.setUseMDCLogging(true);
//        main.getCamelContexts().add(context);
        main.addRouteBuilder(new CamelOsgiTestBlueprint());
        main.run();
    }
}

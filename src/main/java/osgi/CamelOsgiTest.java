package osgi;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.main.Main;

public class CamelOsgiTest {
    public static void main(String... args) throws Exception {
        // Run a route using only Main.
        // Not sure if MDC can be used here...
//        Main main = new Main();
//        main.addRouteBuilder(new CamelOsgiTestBlueprint());
//        main.run();

        // Run a route using only CamelContext.
        // Note that the JVM will terminate before the route gets a chance to do its thing.
        // This is because start() is non-blocking and if there are no statements following it,
        // the end of the program is reached and the JVM terminates.
        // This is why you see some code snippets using sleep methods.
//        CamelContext context = new DefaultCamelContext();
//        context.setUseMDCLogging(true);
//        context.addRoutes(new CamelOsgiTestBlueprint());
//        context.start();

        // Best of both
        CamelContext context = new DefaultCamelContext();
        context.setUseMDCLogging(true);
        context.addRoutes(new CamelOsgiTestBlueprint());
        context.start();

        Main main = new Main();
        main.getCamelContexts().add(context);
        main.run();
    }
}

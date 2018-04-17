package hu.pannon.api;

import hu.pannon.MyApplicationBinder;
import org.glassfish.jersey.server.ResourceConfig;

public class RestApplication extends ResourceConfig {

    public RestApplication() {
        packages("io.swagger.jaxrs.listing", "hu.pannon.api");

        // Register different Binders
        register(new MyApplicationBinder());
    }
}
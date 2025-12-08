package org.emp.gl.lookup;

import java.util.HashMap;
import java.util.Map;

public class Lookup {

    private static Lookup instance = new Lookup();

    private Map<Class, Object> services = new HashMap<>();

    private Lookup() { }

    public static Lookup getInstance() {
        return instance;
    }

    public <T> void subscribeService(Class<? super T> service, T instance) {
        services.put(service, instance);
    }

    public <T> T getService(Class<T> service) {
        return service.cast(services.get(service));
    }
}

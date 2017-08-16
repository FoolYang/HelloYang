package effective_1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Services {
	private Services() {}
	
	private static final Map<String, Provider> providers = 
			new ConcurrentHashMap<String, Provider>();
	
	public static final String DEFAULT_PROVIDER_NAME = "<def>";
	
	public static void registerProvider(String name, Provider p) {
		providers.put(name, p);
	}
	
	public static Service newInstance() {
		return newInstance(DEFAULT_PROVIDER_NAME);
	}
	
	public static Service newInstance(String name) {
		Provider p = providers.get(name);
		if (p == null) {
			throw new IllegalArgumentException("no provider registered with name :" + name);
		}
		return p.newService();
	}
}

interface Service {
	// ... method
}

interface Provider { 
	Service newService();
}

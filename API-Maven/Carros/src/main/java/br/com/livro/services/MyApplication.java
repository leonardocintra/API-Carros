package br.com.livro.services;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Application;

public class MyApplication extends Application {
	
	@Override
	public Map<String, Object> getProperties() {
		Map<String, Object> properties = new HashMap<>();
		// configura o pacote para fazer scan das classes con anotacao REST.
		properties.put("jersey.config.server.provider.packages", "br.com.livro");
		return properties;
	}
}
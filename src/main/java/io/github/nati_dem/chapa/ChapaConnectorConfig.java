package io.github.nati_dem.chapa;

import java.util.concurrent.TimeUnit;

import org.glassfish.jersey.client.oauth2.OAuth2ClientSupport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import io.github.nati_dem.chapa.connector.ChapaConnector;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;

/**
 * The <code>ChapaConnectorConfig</code> class is responsible for creating Spring beans 
 * and configuring webClient to call with Chapa APIs. 
 */
@Configuration
@ComponentScan(basePackages = { "io.github.nati_dem.chapa" })
@PropertySource("classpath:application.properties")
public class ChapaConnectorConfig {
	
	@Value("${chapa.base.url}")
	private String hostName;
	
	@Value("${chapa.connect.timeout:30}")
	private int connectTimeout;
	
	@Value("${chapa.connect.readTimeout:30}")
	private int readTimeout;
	
	@Value("${chapa.secret.key}")
    private String SECRETE_KEY;
	
	@Bean 
	public ChapaConnector chapaConnector() {
		return new ChapaConnector(chapaWebTarget());
	}

	@Bean("chapaClient")
	public Client chapaClient() {
		// todo - configure SSL context
		return ClientBuilder.newBuilder()
			       .connectTimeout(connectTimeout, TimeUnit.SECONDS)
			       .readTimeout(readTimeout, TimeUnit.SECONDS)
			       .register(OAuth2ClientSupport.feature(SECRETE_KEY))
			       .build();
	}
	
	@Bean("chapaWebTarget")
	public WebTarget chapaWebTarget() {
		return chapaClient().target(hostName);
	}
	
}

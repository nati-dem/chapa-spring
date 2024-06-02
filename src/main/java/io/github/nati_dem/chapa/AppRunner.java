package io.github.nati_dem.chapa;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import io.github.nati_dem.chapa.connector.ChapaConnector;

/**
 * The <code>AppRunner</code> class is created to run the application for testing purpose
 */
public class AppRunner {
	
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(ChapaConnectorConfig.class);

		ChapaConnector connector = ctx.getBean(ChapaConnector.class);
		try {
			connector.verify("testref");
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		((AnnotationConfigApplicationContext) ctx).close();
	}
	
}

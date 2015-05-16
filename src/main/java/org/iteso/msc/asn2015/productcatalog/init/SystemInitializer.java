package org.iteso.msc.asn2015.productcatalog.init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.iteso.msc.asn2015.productcatalog.logic.TestLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;


/**
 * Exmple listener (Autowire from Context Listener)
 * @author Daniel Rodriguez Millan drm@chocodev.com
 */
@Component
public class SystemInitializer implements ServletContextListener{

	@Autowired
	TestLogic testLogic;
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		
		// Forzamos el autowire de poiLogic (Autowire no funciona en el contextListener)
		WebApplicationContextUtils
         .getRequiredWebApplicationContext(event.getServletContext())
         .getAutowireCapableBeanFactory()
         .autowireBean(this);
	}

}

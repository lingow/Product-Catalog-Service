package org.iteso.msc.asn2015.productcatalog.app;

import org.iteso.msc.asn2015.productcatalog.logic.TestLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Vaadin Application entry point
 * @author Daniel Rodriguez Millan drm@chocodev.com
 *
 */
@Component
public class HerokuServerApplication extends ContextAwareApplication {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	TestLogic testLogic;
	
	
	@Override
	protected void initApplication() {
		super.initApplication();
	}

	public TestLogic getTestLogic() {
		return testLogic;
	}
}

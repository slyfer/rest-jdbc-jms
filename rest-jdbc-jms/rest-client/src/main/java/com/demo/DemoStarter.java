/**
 * 
 */
package com.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo.runner.ClientsRunner;

/**
 * Starts demo rest client application
 * 
 * @author Ciro Cardone
 * 
 */
public class DemoStarter {

	// spring client runner bean name
	private static final String CLIENT_RUNNER_BEAN_NAME = "clientsRunner";

	/**
	 * Logger
	 */
	final static Logger logger = LoggerFactory.getLogger(DemoStarter.class);

	/**
	 * @param args
	 */
	public static void main(final String[] args) {

		logger.info("Starting demo rest client application");

		new DemoStarter().start();
	}

	/**
	 * Init spring and runs rest clients
	 * 
	 */
	public void start() {
		ClassPathXmlApplicationContext classPathXmlApplicationContext = null;

		try {
			classPathXmlApplicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

			final ClientsRunner clientsRunner = (ClientsRunner) classPathXmlApplicationContext.getBean(CLIENT_RUNNER_BEAN_NAME);

			logger.info("Run clients");
			clientsRunner.runClients();

			System.exit(0);
		} catch (final Exception e) {
			logger.error("Error in demo rest client application", e);
			System.exit(-1);
		} finally {
			logger.info("Shutdown demo rest client application");

			if (null != classPathXmlApplicationContext) {
				classPathXmlApplicationContext.close();
			}
		}

	}
}

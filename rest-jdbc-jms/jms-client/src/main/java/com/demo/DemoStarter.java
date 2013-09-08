package com.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo.jms.DemoSubscriber;

/**
 * Starts demo jms client application
 * 
 * @author Ciro Cardone
 * 
 */
public class DemoStarter {

	// spring client runner bean name
	private static final String CLIENT_RUNNER_BEAN_NAME = "demoSubscriber";

	/**
	 * Logger
	 */
	final static Logger logger = LoggerFactory.getLogger(DemoStarter.class);

	/**
	 * @param args
	 */
	public static void main(final String[] args) {

		logger.info("Starting demo jms client application");

		new DemoStarter().start();
	}

	/**
	 * Initializes spring and runs jms client
	 * 
	 */
	public void start() {

		try {

			final ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

			final DemoSubscriber demoSubscriber = (DemoSubscriber) classPathXmlApplicationContext.getBean(CLIENT_RUNNER_BEAN_NAME);

			logger.info("Run jms client");
			demoSubscriber.start();

		} catch (final Exception e) {
			logger.error("Error in demo rest client application", e);
			System.exit(-1);
		}

	}
}

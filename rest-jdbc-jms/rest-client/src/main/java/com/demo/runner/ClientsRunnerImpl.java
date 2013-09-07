package com.demo.runner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.demo.rs.client.DemoService;
import com.demo.rs.model.Response;

/**
 * 
 * @author Ciro Cardone
 * 
 */
public class ClientsRunnerImpl implements ClientsRunner {

	/**
	 * Logger
	 */
	final Logger logger = LoggerFactory.getLogger(ClientsRunnerImpl.class);

	/**
	 * Logger
	 */
	final Logger errorLogger = LoggerFactory.getLogger("failed.request.logger");

	/**
	 * Rest client
	 */
	@Resource(name = "demoRestClient")
	private DemoService demoService;

	/**
	 * Properties
	 */
	@Autowired
	@Qualifier("clientProperties")
	private Properties clientProperties;

	@Override
	public void runClients() {

		final Integer threadPoolSize = Integer.parseInt(clientProperties.getProperty("rest.clients.thread.pool.size"));

		final int clients = Integer.parseInt(clientProperties.getProperty("rest.clients.number"));

		logger.debug("threadPool size " + threadPoolSize);
		logger.debug("clients to run " + clients);

		final ExecutorService threadPool = Executors.newFixedThreadPool(threadPoolSize);
		final Collection<RestClient> tasks = new ArrayList<RestClient>(clients);

		for (int i = 0; i < clients; i++) {
			tasks.add(new RestClient(demoService));
		}

		try {
			final List<Future<Response>> results = threadPool.invokeAll(tasks);
			threadPool.shutdown();

			for (int i = 0; i < results.size(); i++) {

				Response response;
				try {
					final Future<Response> result = results.get(i);
					response = result.get();
					logger.info("Response for client {} -> id: {} - time: {} ", i, response.getId(), response.getTimestamp());
				} catch (final ExecutionException e) {
					errorLogger.error("Error for client {}: {}", i, e.getMessage());
				}
			}

			threadPool.awaitTermination(100, TimeUnit.SECONDS);
		} catch (final InterruptedException e) {
			logger.error("Thread was interrrupetd", e);
		}
	}

	/**
	 * @return the demoService
	 */
	public DemoService getDemoService() {
		return demoService;
	}

	/**
	 * @param demoService
	 *            the demoService to set
	 */
	public void setDemoService(final DemoService demoService) {
		this.demoService = demoService;
	}

	/**
	 * @return the clientProperties
	 */
	public Properties getClientProperties() {
		return clientProperties;
	}

	/**
	 * @param clientProperties
	 *            the clientProperties to set
	 */
	public void setClientProperties(final Properties clientProperties) {
		this.clientProperties = clientProperties;
	}

}

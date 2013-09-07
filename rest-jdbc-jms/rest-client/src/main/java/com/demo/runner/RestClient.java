package com.demo.runner;

import java.util.UUID;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.rs.client.DemoService;
import com.demo.rs.model.Response;

/**
 * Callable rest client
 * 
 * @author Ciro Cardone
 * 
 */
public class RestClient implements Callable<Response> {

	/**
	 * Logger
	 */
	final Logger logger = LoggerFactory.getLogger(RestClient.class);

	/**
	 * Real rest client
	 */
	private final DemoService demoService;

	/**
	 * Create a new rest client
	 * 
	 * @param demoService
	 *            Real rest client
	 */
	public RestClient(final DemoService demoService) {
		this.demoService = demoService;
	}

	@Override
	public Response call() throws Exception {

		final String id = UUID.randomUUID().toString();
		try {
			final Response response = demoService.putId(id);
			return response;
		} catch (final Exception e) {
			throw new Exception("Failed to send " + id + " - " + e.getMessage());
		}

	}

}

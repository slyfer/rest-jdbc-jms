package com.demo.runner;

import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

	@Resource(name = "demoRestClient")
	private DemoService demoService;

	@Override
	public void runClients() {

		final Response response = demoService.putId(UUID.randomUUID().toString());

		logger.info("Response: id: {} - time: {} ", response.getId(), response.getTimestamp());

	}

}

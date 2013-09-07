package com.demo.jms;

import it.seat.core.db.model.ProcessedMessage;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.jms.MapMessage;
import javax.jms.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import com.demo.dao.ProcessedMessageDao;

/**
 * Fixed rate subscriber
 * 
 * @author Ciro Cardone
 * 
 */
public class DemoSubscriber extends Thread {

	private static final int RATE_PER_SECOND = 5;

	/**
	 * Logger
	 */
	final Logger logger = LoggerFactory.getLogger(DemoSubscriber.class);

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	private ProcessedMessageDao processedMessageDao;

	@Override
	public void run() {

		jmsTemplate.setDefaultDestinationName("test_queue");

		final ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();

		ses.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {

				final Message receive = jmsTemplate.receive();

				final MapMessage mapMessage = (MapMessage) receive;
				try {
					final String id = mapMessage.getString("id");
					logger.info("Received messege " + id);

					final ProcessedMessage processedMessage = new ProcessedMessage();
					processedMessage.setCreationActor(this.getClass().getName());
					processedMessage.setId(id);

					processedMessageDao.create(processedMessage);
				} catch (final Exception e) {
					logger.error("Error processing jms message", e);
				}

			}
		}, 0, ((1000 * 1000000) / (RATE_PER_SECOND)), TimeUnit.NANOSECONDS);

	}

	/**
	 * @return the processedMessageDao
	 */
	public ProcessedMessageDao getProcessedMessageDao() {
		return processedMessageDao;
	}

	/**
	 * @param processedMessageDao
	 *            the processedMessageDao to set
	 */
	public void setProcessedMessageDao(final ProcessedMessageDao processedMessageDao) {
		this.processedMessageDao = processedMessageDao;
	}
}

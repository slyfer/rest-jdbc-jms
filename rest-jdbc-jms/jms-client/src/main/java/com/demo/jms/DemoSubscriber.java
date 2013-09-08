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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;

import com.demo.dao.ProcessedMessageDao;

/**
 * Fixed rate jms subscriber
 * 
 * @author Ciro Cardone
 * 
 */
public class DemoSubscriber extends Thread {

	/**
	 * If we want max 5 dequeue for second, we have to set 4, because
	 * {@link ScheduledExecutorService} is imprecise
	 */
	private static final int RATE_PER_SECOND = 4;

	/**
	 * Logger
	 */
	final Logger logger = LoggerFactory.getLogger(DemoSubscriber.class);

	/**
	 * Jms template
	 */
	@Autowired
	private JmsTemplate jmsTemplate;

	/**
	 * Dao for {@link ProcessedMessage}
	 */
	@Autowired
	private ProcessedMessageDao processedMessageDao;

	/**
	 * Queue name
	 */
	@Value("#{clientProperties['jms.queue.name']}")
	private String queueName;

	@Override
	public void run() {

		jmsTemplate.setDefaultDestinationName(queueName);

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

	/**
	 * @return the jmsTemplate
	 */
	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	/**
	 * @param jmsTemplate
	 *            the jmsTemplate to set
	 */
	public void setJmsTemplate(final JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	/**
	 * @return the queueName
	 */
	public String getQueueName() {
		return queueName;
	}

	/**
	 * @param queueName
	 *            the queueName to set
	 */
	public void setQueueName(final String queueName) {
		this.queueName = queueName;
	}
}

package com.demo.jms;

import java.util.Date;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * {@link DemoPublisher} implementation
 * 
 * @author Ciro Cardone
 * 
 */
@Transactional
public class DemoPublisherImpl implements DemoPublisher {

	/**
	 * Jms template
	 */
	@Autowired
	private JmsTemplate jmsTemplate;

	/**
	 * Queue name
	 */
	@Value("#{serverProperties['jms.queue.name']}")
	private String queueName;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void publish(final String id, final Date date) {

		jmsTemplate.setDefaultDestinationName(queueName);

		jmsTemplate.send(new MessageCreator() {
			@Override
			public Message createMessage(final Session session) throws JMSException {
				final MapMessage mapMessage = session.createMapMessage();
				mapMessage.setString("id", id);
				mapMessage.setString("date", date.toString());
				return mapMessage;
			}
		});
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
}

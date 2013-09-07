package com.demo.jms;

import java.util.Date;

/**
 * Demo publisher interface
 * 
 * @author Ciro Cardone
 * 
 */
public interface DemoPublisher {

	/**
	 * Publish message on queue
	 * 
	 * @param id
	 *            Id to publish
	 * @param date
	 *            Publish date
	 */
	public void publish(String id, Date date);
}

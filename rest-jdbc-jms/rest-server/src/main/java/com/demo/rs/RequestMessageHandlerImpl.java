package com.demo.rs;

import it.seat.core.db.model.RequestMessage;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.RequestMessageDao;
import com.demo.jms.DemoPublisher;

public class RequestMessageHandlerImpl implements RequestMessageHanlder {

	/**
	 * Dao for {@link RequestMessage}
	 */
	@Autowired
	private RequestMessageDao requestMessageDao;

	/**
	 * Jms publisher
	 */
	@Autowired
	private DemoPublisher demoPublisher;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Date handle(final String id) {
		final Date processTime = new Date();

		final RequestMessage requestMessage = new RequestMessage();
		requestMessage.setId(id);
		requestMessage.setCreationDate(processTime);
		requestMessage.setCreationActor(this.getClass().getName());

		requestMessageDao.create(requestMessage);

		demoPublisher.publish(id, processTime);

		return processTime;
	}

	/**
	 * @return the requestMessageDao
	 */
	public RequestMessageDao getRequestMessageDao() {
		return requestMessageDao;
	}

	/**
	 * @param requestMessageDao
	 *            the requestMessageDao to set
	 */
	public void setRequestMessageDao(final RequestMessageDao requestMessageDao) {
		this.requestMessageDao = requestMessageDao;
	}

	/**
	 * @return the demoPublisher
	 */
	public DemoPublisher getDemoPublisher() {
		return demoPublisher;
	}

	/**
	 * @param demoPublisher
	 *            the demoPublisher to set
	 */
	public void setDemoPublisher(final DemoPublisher demoPublisher) {
		this.demoPublisher = demoPublisher;
	}

}

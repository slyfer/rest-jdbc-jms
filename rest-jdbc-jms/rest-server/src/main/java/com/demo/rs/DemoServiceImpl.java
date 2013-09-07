package com.demo.rs;

import it.seat.core.db.model.RequestMessage;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.demo.dao.RequestMessageDao;
import com.demo.rs.model.Response;

/**
 * Implementation of {@link DemoService}
 * 
 * @author Ciro Cardone
 * 
 */
public class DemoServiceImpl implements DemoService {

	/**
	 * Logger
	 */
	final Logger logger = LoggerFactory.getLogger(DemoServiceImpl.class);

	/**
	 * Dao for {@link RequestMessage}
	 */
	@Autowired
	private RequestMessageDao requestMessageDao;

	@Override
	public Response putId(final String id) throws WebApplicationException {

		logger.info("Processing " + id);

		try {
			final Date processTime = new Date();

			final RequestMessage requestMessage = new RequestMessage();
			requestMessage.setId(id);
			requestMessage.setCreationDate(processTime);
			requestMessage.setCreationActor(this.getClass().getName());

			requestMessageDao.create(requestMessage);

			logger.debug("Process time for {} is {}", id, processTime);

			return createResponse(id, processTime);
		} catch (final Exception e) {
			logger.error("Unexpoected exception during id processing: ", id, e);

			// in real world we must provide some info to client
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Utility method to create a {@link Response}
	 * 
	 * @param id
	 *            Response id
	 * @param date
	 *            Response date
	 * @return A {@link Response} containing given parameters
	 * 
	 * @throws DatatypeConfigurationException
	 *             If date conversion non works
	 */
	private Response createResponse(final String id, final Date date) throws DatatypeConfigurationException {
		final Response response = new Response();

		response.setId(id);

		// converts java.util.Date to XMLGregorianCalendar
		final GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.setTime(date);
		final XMLGregorianCalendar xmlTimestamp = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);

		response.setTimestamp(xmlTimestamp);

		return response;
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

}

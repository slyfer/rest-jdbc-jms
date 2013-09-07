package com.demo.dao;

import it.seat.core.db.model.RequestMessage;

/**
 * Dao implementation for {@link RequestMessage}
 * 
 * @author Ciro Cardone
 * 
 */
public class RequestMessageDaoImpl extends GenericDaoImpl<RequestMessage, String> implements RequestMessageDao {

	/**
	 * Create a new dao
	 */
	public RequestMessageDaoImpl() {
		super(RequestMessage.class);
	}

}

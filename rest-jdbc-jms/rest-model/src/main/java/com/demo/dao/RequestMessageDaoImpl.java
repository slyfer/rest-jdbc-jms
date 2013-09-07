package com.demo.dao;

import it.seat.core.db.model.RequestMessage;

import org.springframework.transaction.annotation.Transactional;

/**
 * Dao implementation for {@link RequestMessage}
 * 
 * @author Ciro Cardone
 * 
 */
@Transactional
public class RequestMessageDaoImpl extends GenericDaoImpl<RequestMessage, String> implements RequestMessageDao {

	/**
	 * Creates a new dao
	 */
	public RequestMessageDaoImpl() {
		super(RequestMessage.class);
	}

}

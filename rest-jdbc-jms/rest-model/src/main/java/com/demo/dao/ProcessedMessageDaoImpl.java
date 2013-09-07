package com.demo.dao;

import it.seat.core.db.model.ProcessedMessage;

import org.springframework.transaction.annotation.Transactional;

/**
 * Dao implementation for {@link ProcessedMessage}
 * 
 * @author Ciro Cardone
 * 
 */
@Transactional
public class ProcessedMessageDaoImpl extends GenericDaoImpl<ProcessedMessage, String> implements ProcessedMessageDao {

	/**
	 * Creates a new dao
	 */
	public ProcessedMessageDaoImpl() {
		super(ProcessedMessage.class);
	}

}

package com.demo.dao;

import java.io.Serializable;

/**
 * Base interface for DAO classes
 * 
 * @param <T>
 *            entity class
 * @param <PK>
 *            entity primary key
 * 
 * @author Ciro Cardone
 */
public interface GenericDao<T, PK extends Serializable> {

	/**
	 * Loads an entity
	 * 
	 * @param id
	 *            Entity id
	 * @return The entity with given id
	 */
	public T load(PK id);

	/**
	 * Persists new entity
	 * 
	 * @param newInstance
	 *            Entity to persist
	 */
	public void create(T newInstance);

	/**
	 * Updates an entity
	 * 
	 * @param transientObject
	 *            Object containing the new values
	 * 
	 * @return Updated entity
	 */
	public T update(T transientObject);

	/**
	 * Delete an entity
	 */

	/**
	 * Delete an entity
	 * 
	 * @param persistentObject
	 *            Entity to delete
	 */
	public void delete(T persistentObject);

	/**
	 * Delete an entity
	 * 
	 * @param id
	 *            Entity to delete id
	 */
	public void deleteById(PK id);

	/**
	 * Flush entity manager
	 */
	public void flush();
}

package com.demo.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * {@link GenericDao} implementation.
 * 
 * @param <T>
 *            entity class
 * @param <PK>
 *            primary key dell'entity
 * 
 * @author Ciro Cardone
 */
@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public abstract class GenericDaoImpl<T, PK extends Serializable> implements GenericDao<T, PK> {

	/**
	 * Logger
	 */
	final Logger logger = LoggerFactory.getLogger(GenericDaoImpl.class);

	/**
	 * Entity to manage
	 */
	private final Class<T> persistentClass;

	/**
	 * EntityManager JPA
	 */
	protected EntityManager em;

	/**
	 * Create new generic dao to manage <code>persistentClass<code>
	 * 
	 * @param persistentClass
	 *            Entity to manage
	 */
	public GenericDaoImpl(final Class<T> persistentClass) {
		super();
		this.persistentClass = persistentClass;
	}

	/**
	 * Set the entity manager
	 * 
	 * @param em
	 *            Entity manager to set
	 */
	@PersistenceContext(unitName = "demo-pu")
	public void setEm(final EntityManager em) {
		this.em = em;
	}

	@Override
	@Transactional(readOnly = false)
	public void create(final T o) {
		em.persist(o);
	}

	@Override
	@Transactional(readOnly = true)
	public T load(final PK id) {
		return em.find(persistentClass, id);
	}

	@Override
	@Transactional(readOnly = false)
	public T update(final T o) {
		final T merge = em.merge(o);
		return merge;
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(final T o) {
		em.remove(o);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteById(final PK id) {
		final T entity = load(id);
		em.remove(entity);
	}

	@Override
	@Transactional(readOnly = false)
	public void flush() {
		em.flush();
	}
}

package it.seat.core.db.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PersistenceContext;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Base entity with basic entity fields
 * 
 * @author Ciro Cardone
 * 
 */
@MappedSuperclass
@PersistenceContext(unitName = "demo-pu")
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "CREATION_ACTOR", nullable = false)
	protected String creationActor;

	@Column(name = "MODIFY_ACTOR")
	protected String modifyActor;

	@Column(name = "CREATION_DATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	protected Date creationDate;

	@Column(name = "MODIFY_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	protected Date modifyDate;

	/**
	 * Set creation date if not specified
	 */
	@PrePersist
	protected void prePersist() {
		if (null == creationDate) {
			this.creationDate = new Date();
		}
	}

	/**
	 * Set modify date if not specified
	 */
	@PreUpdate
	protected void preUpdate() {
		this.modifyDate = new Date();
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate
	 *            the creationDate to set
	 */
	public void setCreationDate(final Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the modifyDate
	 */
	public Date getModifyDate() {
		return modifyDate;
	}

	/**
	 * @param modifyDate
	 *            the modifyDate to set
	 */
	public void setModifyDate(final Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	/**
	 * @return the creationActor
	 */
	public String getCreationActor() {
		return creationActor;
	}

	/**
	 * @param creationActor
	 *            the creationActor to set
	 */
	public void setCreationActor(final String creationActor) {
		this.creationActor = creationActor;
	}

	/**
	 * @return the modifyActor
	 */
	public String getModifyActor() {
		return modifyActor;
	}

	/**
	 * @param modifyActor
	 *            the modifyActor to set
	 */
	public void setModifyActor(final String modifyActor) {
		this.modifyActor = modifyActor;
	}

	/**
	 * Force all sub classes to define toString()
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public abstract String toString();

}
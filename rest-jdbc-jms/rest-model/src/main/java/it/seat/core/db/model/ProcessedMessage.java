/**
 * 
 */
package it.seat.core.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Maps table to store processed messages received by jms queue
 * 
 * @author Ciro Cardone
 * 
 */
@Entity
@Table(name = "PROCESSED_MESSAGE")
public class ProcessedMessage extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Table PK
	 */
	@Id
	@Column(name = "ID")
	private String id;

	@Override
	public String toString() {
		return "ProcessedMessage [id=" + id + ", creationActor=" + creationActor + ", modifyActor=" + modifyActor + ", creationDate=" + creationDate + ", modifyDate=" + modifyDate
				+ "]";
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final String id) {
		this.id = id;
	}

}

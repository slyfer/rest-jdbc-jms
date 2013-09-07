/**
 * 
 */
package it.seat.core.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Maps table to store request messages received by rest server
 * 
 * @author Ciro Cardone
 * 
 */
@Entity
@Table(name = "REQUEST_MESSAGE")
public class RequestMessage extends BaseEntity {

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
		return "RequestMessage [id=" + id + ", creationActor=" + creationActor + ", modifyActor=" + modifyActor + ", creationDate=" + creationDate + ", modifyDate=" + modifyDate
				+ "]";
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

}

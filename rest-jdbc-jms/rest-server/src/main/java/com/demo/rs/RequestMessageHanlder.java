/**
 * 
 */
package com.demo.rs;

import java.util.Date;

/**
 * @author Ciro Cardone
 * 
 */
public interface RequestMessageHanlder {

	/**
	 * Handle a message
	 * 
	 * @param id
	 * @return Processing date
	 */
	public Date handle(String id);

}

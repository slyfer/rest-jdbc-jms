package com.demo.rs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

import org.apache.cxf.jaxrs.model.wadl.Description;

import com.demo.rs.model.Response;

/**
 * Demo rest service
 * 
 * @author Ciro Cardone
 * 
 */
@Path("/demoService")
@Produces("application/xml")
@Description("")
public interface DemoService {

	// @POST
	@GET
	@Path("putId/{id}")
	public Response putId(@PathParam("id") String id) throws WebApplicationException;

}

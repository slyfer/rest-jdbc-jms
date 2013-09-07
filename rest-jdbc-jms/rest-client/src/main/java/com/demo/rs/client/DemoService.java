package com.demo.rs.client;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;

import com.demo.rs.model.Response;

/**
 * Demo rest service client
 * 
 * @author Ciro Cardone
 * 
 */
@Path("/demoService")
public interface DemoService {

	@POST
	@Path("putId/{id}")
	public Response putId(@PathParam("id") String id) throws WebApplicationException;

}
/**
 *
 */
package com.restful.status;

import javax.ws.rs.GET;
/**
 * @author Divya
 * @date   02-Feb-2017
 */
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/status")
public class Status {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle() {
		return "<p>Web Services </p>";
	}

}

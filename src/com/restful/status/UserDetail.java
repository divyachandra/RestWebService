/**
 *
 */
package com.restful.status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;

import com.restful.utils.DatabaseUtil;
import com.restful.utils.ToJson;

/**
 * @author Divya
 * @date 06-Feb-2017
 */
@Path("/userdetails")
public class UserDetail {
	public static final String FIND_BY_NAME_SQL = "Select USERID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME from USER where USERNAME=?";

	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response returnUserDetail(@QueryParam("user") String user) throws SQLException, JSONException {

		PreparedStatement prepStatement = null;
		String result = null;
		Response response = null;

		try {

			if (user == null)
				return Response.status(404).entity("Error: No user entered").build();

			Connection connection = DatabaseUtil.getConnection();
			prepStatement = connection.prepareStatement(FIND_BY_NAME_SQL);
			prepStatement.setString(1, user);
			ResultSet resultSet = prepStatement.executeQuery();

			ToJson jsonConverter = new ToJson();
			JSONArray jsonArray = new JSONArray();

			jsonArray = jsonConverter.convertToJsonArray(resultSet);
			result = jsonArray.toString();
			response = Response.ok(result).build();
			prepStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return response;

	}

	@Path("/{user}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response getUserByPath(@PathParam("user") String user) throws SQLException, JSONException {

		PreparedStatement prepStatement = null;
		String result = null;
		Response response = null;

		try {
			Connection connection = DatabaseUtil.getConnection();
			prepStatement = connection.prepareStatement(FIND_BY_NAME_SQL);
			prepStatement.setString(1, user);
			ResultSet resultSet = prepStatement.executeQuery();

			ToJson jsonConverter = new ToJson();
			JSONArray jsonArray = new JSONArray();

			jsonArray = jsonConverter.convertToJsonArray(resultSet);
			result = jsonArray.toString();
			response = Response.ok(result).build();
			prepStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return response;

	}

}

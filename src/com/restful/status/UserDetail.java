/**
 *
 */
package com.restful.status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;

import com.restful.bean.User;
import com.restful.utils.DatabaseUtil;
import com.restful.utils.ToJson;

/**
 * @author Divya
 * @date 06-Feb-2017
 */
@Path("/user")
public class UserDetail {
	public static final String FIND_BY_NAME_SQL = "Select USERID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME from USER where USERNAME=?";
	public static final String INSERT_USER_SQL = "Insert into User(USERNAME, PASSWORD, FIRSTNAME, LASTNAME) values(?, ?, ?, ?)";

	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response returnUserDetail(@QueryParam("user") String user) throws Exception {

		String result = null;
		Response response = null;
		try {
			if (user == null)
				return Response.status(404).entity("Error: No user entered").build();
			result = jsonToString(findUser(user));
			response = Response.ok(result).build();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return response;
	}

	@Path("/{user}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response getUserByPath(@PathParam("user") String user) throws Exception {

		String result = null;
		Response response = null;

		try {
			result = jsonToString(findUser(user));
			response = Response.ok(result).build();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return response;

	}

	@POST
	@Consumes({ MediaType.TEXT_HTML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces(MediaType.TEXT_HTML)
	public Response insertUser(String urlUserString) throws Exception {

		Response response = null;

		try {
			ObjectMapper mapper = new ObjectMapper();
			User user = mapper.readValue(urlUserString, User.class);
			int httpCode = insert(user);

			if (httpCode == 200) {
				response = Response.status(200).entity("user inserted").build();
			} else {
				response = Response.status(500).entity("Server was not able to process request").build();
			}

		} catch (Exception e) {
			e.printStackTrace();
			Response.status(500).entity("Server was not able to process request").build();
		}
		return response;
	}

	protected ResultSet findUser(String user) {
		PreparedStatement prepStatement = null;
		ResultSet resultSet = null;

		try {
			Connection connection = DatabaseUtil.getConnection();
			prepStatement = connection.prepareStatement(FIND_BY_NAME_SQL);
			prepStatement.setString(1, user);
			resultSet = prepStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}

	protected int insert(User user) {
		PreparedStatement prepStatement = null;
		try {
			Connection connection = DatabaseUtil.getConnection();
			prepStatement = connection.prepareStatement(INSERT_USER_SQL);
			prepStatement.setString(1, user.getUserName());
			prepStatement.setString(2, user.getPassword());
			prepStatement.setString(3, user.getFirstName());
			prepStatement.setString(4, user.getLastName());
			prepStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 500;
		}
		return 200;
	}

	protected String jsonToString(ResultSet resultSet) throws Exception {
		ToJson jsonConverter = new ToJson();
		JSONArray jsonArray = new JSONArray();

		try {
			jsonArray = jsonConverter.convertToJsonArray(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jsonArray.toString();
	}
}

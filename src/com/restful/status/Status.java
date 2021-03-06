/**
 *
 */
package com.restful.status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.GET;
/**
 * @author Divya
 * @date   02-Feb-2017
 */
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;

import com.restful.utils.DatabaseUtil;
import com.restful.utils.ToJson;

@Path("/status")
public class Status {

	public static final String FIND_BY_NAME_SQL = "Select USERID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME from USER where USERNAME=?";
	public static final String SELECT_ALL_USERS_SQL = "Select * from USER";

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle() {
		return "<p>Web Services </p>";
	}

	@Path("/userlist")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnDatabaseStatus() throws SQLException, JSONException {

		PreparedStatement prepStatement = null;
		String result = null;
		Response response = null;

		try {
			Connection connection = DatabaseUtil.getConnection();
			prepStatement = connection.prepareStatement(SELECT_ALL_USERS_SQL);
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
		return result;

	}
}

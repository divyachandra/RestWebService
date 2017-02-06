/**
 *
 */
package com.restful.utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * @author Divya
 * @date 04-Feb-2017
 */
public class ToJson {

	public JSONArray convertToJsonArray(ResultSet rs) throws SQLException, JSONException {

		JSONArray jsonArray = new JSONArray();

		try {
			ResultSetMetaData rsmeta = rs.getMetaData();
			while (rs.next()) {
				int numOfCols = rsmeta.getColumnCount();
				JSONObject jsonObject = new JSONObject();
				for (int i = 1; i <= numOfCols; i++) {
					String columnName = rsmeta.getColumnName(i);
					if (rsmeta.getColumnType(i) == Types.ARRAY)
						jsonObject.put(columnName, rs.getArray(columnName));
					else if (rsmeta.getColumnType(i) == Types.BIGINT)
						jsonObject.put(columnName, rs.getInt(columnName));
					else if (rsmeta.getColumnType(i) == Types.BOOLEAN)
						jsonObject.put(columnName, rs.getBoolean(columnName));
					else if (rsmeta.getColumnType(i) == Types.BLOB)
						jsonObject.put(columnName, rs.getBlob(columnName));
					else if (rsmeta.getColumnType(i) == Types.DOUBLE)
						jsonObject.put(columnName, rs.getDouble(columnName));
					else if (rsmeta.getColumnType(i) == Types.FLOAT)
						jsonObject.put(columnName, rs.getFloat(columnName));
					else if (rsmeta.getColumnType(i) == Types.INTEGER)
						jsonObject.put(columnName, rs.getInt(columnName));
					else if (rsmeta.getColumnType(i) == Types.NVARCHAR)
						jsonObject.put(columnName, rs.getString(columnName));
					else if (rsmeta.getColumnType(i) == Types.VARCHAR)
						jsonObject.put(columnName, rs.getString(columnName));
					else if (rsmeta.getColumnType(i) == Types.DATE)
						jsonObject.put(columnName, rs.getDate(columnName));
					else if (rsmeta.getColumnType(i) == Types.TIMESTAMP)
						jsonObject.put(columnName, rs.getTimestamp(columnName));
					else if (rsmeta.getColumnType(i) == Types.NUMERIC)
						jsonObject.put(columnName, rs.getBigDecimal(columnName));
					else
						jsonObject.put(columnName, rs.getObject(columnName));
				}
				jsonArray.put(jsonObject);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jsonArray;
	}

}

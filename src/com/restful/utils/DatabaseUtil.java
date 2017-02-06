/**
 *
 */
package com.restful.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * @author Divya
 * @date 02-Feb-2017
 */
public class DatabaseUtil {

	private static DatabaseUtil INSTANCE;
	private Connection connection;

	public static Connection getConnection() {
		initialize();
		return INSTANCE.connection;
	}

	private void setConnection() throws SQLException {
		DataSource dataSource = setDataSource();
		INSTANCE.connection = dataSource.getConnection();
	}

	protected static synchronized void initialize() {
		if (INSTANCE == null) {
			INSTANCE = new DatabaseUtil();
			try {
				INSTANCE.setConnection();
				if (INSTANCE.connection == null)
					throw new NullPointerException("Database connection is NULL.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected DataSource setDataSource() {
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser(getUser());
		dataSource.setPassword(getPassword());
		dataSource.setDatabaseName(getDatabaseName());
		dataSource.setPortNumber(getPort());
		dataSource.setServerName(getHost());

		return dataSource;
	}

	public String getUser() {
		return Globals.getProperties().getProperty("db.user");
	}

	public String getHost() {
		return Globals.getProperties().getProperty("db.host");
	}

	public String getPassword() {
		return Globals.getProperties().getProperty("db.password");
	}

	public String getDriver() {
		return Globals.getProperties().getProperty("db.driver");
	}

	public int getPort() {
		return Integer.parseInt(Globals.getProperties().getProperty("db.port"));
	}

	public String getDatabaseName() {
		return Globals.getProperties().getProperty("db.name");
	}

	public String getAutoReconnect() {
		return Globals.getProperties().getProperty("db.autoReconnect");
	}

	public String getUseSsl() {
		return Globals.getProperties().getProperty("db.UseSSL");
	}

}

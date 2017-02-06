/**
 *
 */
package com.restful.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.tomcat.util.http.fileupload.IOUtils;

/**
 * @author Divya
 * @date 02-Feb-2017
 */
public class Globals {

	private static final String DB_PROPERTIES_FILE = "/resources/db.properties";

	private static final Globals INSTANCE = new Globals();
	private Properties properties;

	public static Properties getProperties() {
		return INSTANCE.properties;
	}

	public Globals() {
		properties = new Properties();
		loadDbProperties(DB_PROPERTIES_FILE);
	}

	private void loadDbProperties(String propertyFileName) {
		InputStream in = Globals.class.getResourceAsStream(propertyFileName);
		if (in != null) {
			try {
				properties.load(in);
				IOUtils.closeQuietly(in);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

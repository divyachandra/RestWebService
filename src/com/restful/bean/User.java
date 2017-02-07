/**
 *
 */
package com.restful.bean;

/**
 * @author Divya
 * @date 06-Feb-2017
 */
public class User {

	private String userName;
	private String password;
	private String firstName;
	private String lastName;

	public User(String userName, String password, String firstName, String lastName) {
		super();
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public User() {
		// No-Op
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		String fullName = "N/A";

		if (this.firstName != null)
			fullName = this.firstName;
		if (this.lastName != null) {
			fullName = fullName + " " + this.lastName;
		}

		return fullName;
	}

}

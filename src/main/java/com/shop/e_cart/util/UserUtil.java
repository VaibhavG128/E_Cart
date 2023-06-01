package com.shop.e_cart.util;

public class UserUtil {
	private int id;
	private String name;
	private String email;
	private String userType; 

	public UserUtil() {
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public UserUtil(int id, String name, String email, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserUtil [id=" + id + ", name=" + name + ", email=" + email + ", userType=" + userType + "]";
	}


}

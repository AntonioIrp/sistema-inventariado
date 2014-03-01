package com.sendproperties.ws;

import java.io.Serializable;

public class Usuarios implements Serializable{
	private String user,pass;
	private int id;
	
	public Usuarios(String user, String pass )//int id
	{
		this.user=user;
		this.pass=pass;
		this.id=id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}

package com.sendproperties.ws;

import java.io.Serializable;
import java.sql.ResultSet;

public class Comercio implements Serializable{
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	private int idUser,idCom;
	private String comercio, descripcion; //nombre comercio
	 
	public Comercio(int idUser, int idCom, String comercio)
	{
		this.idUser=idUser;
		this.idCom=idCom;
		this.comercio=comercio;
		this.descripcion=descripcion;
	}

	public Comercio(ResultSet rs) {
		try
		{
		this.idUser=rs.getInt("iduser");
		this.idCom=rs.getInt("idcom");	
		this.comercio=rs.getString("comercio");
		this.descripcion=rs.getString("descripcion");
		}catch(Exception e){}
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getIdCom() {
		return idCom;
	}

	public void setIdCom(int idCom) {
		this.idCom = idCom;
	}

	public String getComercio() {
		return comercio;
	}

	public void setComercio(String comercio) {
		this.comercio = comercio;
	}
	
	
}

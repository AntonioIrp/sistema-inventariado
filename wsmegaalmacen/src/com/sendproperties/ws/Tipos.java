package com.sendproperties.ws;

import java.io.Serializable;
import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class Tipos implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String nombre_producto;
	int id_producto;
	String url;
	int cantidad;
	
	public void addNombre(String nombre)
	{
		nombre_producto=nombre;
	}
	public void addidproducto(int idproducto)
	{
		id_producto=idproducto;
	}
	public void addcantidad(int uds)
	{
		cantidad=uds;
	}
	public String getnombre()
	{
		return nombre_producto;
	}
	
}

package com.sendproperties.ws;

import java.io.Serializable;
import java.sql.ResultSet;

public class Producto implements Serializable{
	
	//Atributos de producto
	//la imagen se adjuntara como URL
	private String nombre,descripcion,ubicacion,tipo,imagen;
	private int unidades,idProducto, idComercio;
	
	

	public Producto(String nombre, String descripcion, String ubicacion,String tipo, String imagen, int unidades, int idProducto, int idComercio)
	{
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.ubicacion=ubicacion;
		this.idProducto=idProducto;
		this.tipo=tipo;
		this.imagen=imagen;
		this.unidades=unidades;
		this.idComercio=idComercio;
	}

	public Producto (ResultSet rs)
	{
		try
		{
		this.nombre=rs.getString("nombre");
		this.tipo=rs.getString("tipo");		
		this.ubicacion=rs.getString("ubicacion");
		this.imagen=rs.getString("imagen");
		this.descripcion=rs.getString("descripcion");
		this.unidades=rs.getInt("unidades");
		this.idComercio=rs.getInt("idcom");
		this.idProducto=rs.getInt("idproducto");
		}catch(Exception e){}
	}
	
	public Producto(int idProducto, String nombre, String tipo) {
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.tipo = tipo;
		this.imagen = "";
	}
	
	public Producto()
	{
		nombre="";
		descripcion="";
		ubicacion="";
		idProducto=-1;
		tipo="";
		imagen="";
		unidades=-1;
		idComercio=-1;
	}
	
	public Producto(int idProducto, String nombre, String tipo, String rutaImagen) {
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.tipo = tipo;
		this.imagen = rutaImagen;
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getTipo() {
		return tipo;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getIdComercio() {
		return idComercio;
	}

	public void setIdComercio(int idComercio) {
		this.idComercio = idComercio;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}


	
	
	
}

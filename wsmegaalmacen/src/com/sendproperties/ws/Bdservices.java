package com.sendproperties.ws;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.kobjects.base64.Base64;
import org.w3c.dom.Element;

import antlr.debug.TraceEvent;
import antlr.debug.Tracer;

//import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import java.sql.*;

import com.mysql.jdbc.Driver;




public class Bdservices{
	
	Connection conn;
	Statement stm;
	String sql;
	ResultSet rs;
	boolean operacion_correcta;		
	
	public String altaProducto(String param) {
		String nom = "q";
	
		byte[] parametro=Base64.decode(param);
		ByteArrayInputStream bai=new ByteArrayInputStream(parametro);               
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(bai);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Producto nuevoProducto=(Producto) ois.readObject();
			Class.forName("com.mysql.jdbc.Driver");
			try {
				conn=DriverManager.getConnection("jdbc:mysql://localhost/megaalmacen","root","root");
			 
			stm=conn.createStatement();
			sql="insert into megaalmacen.productos values ( '"+nuevoProducto.getIdComercio()+"', NULL, '"+nuevoProducto.getNombre()+"', '"+nuevoProducto.getTipo()+"', '"+nuevoProducto.getUbicacion()+"', '"+nuevoProducto.getDescripcion()+"', '"+nuevoProducto.getUnidades()+"','"+nuevoProducto.getImagen()+"');";

			stm.executeUpdate(sql);		//"+nuevoProducto.getIdProducto()+"
			
			
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return "nombre producto:"+nom+"Last Name : ";
	}
	
	
	public String modificarProducto(String param) {
		String nom = "q";
		int cantidad = 0, identificador;
		byte[] parametro=Base64.decode(param);
		ByteArrayInputStream bai=new ByteArrayInputStream(parametro);               
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(bai);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Producto nuevoProducto=(Producto) ois.readObject();
			
			
			Class.forName("com.mysql.jdbc.Driver");
			try {
				conn=DriverManager.getConnection("jdbc:mysql://localhost/megaalmacen","root","root");
			 
			stm=conn.createStatement();
			sql="update megaalmacen.productos set  nombre='"+nuevoProducto.getNombre()+"', tipo='"+nuevoProducto.getTipo()+"', ubicacion='"+nuevoProducto.getUbicacion()+"', descripcion='"+nuevoProducto.getDescripcion()+"', unidades="+nuevoProducto.getUnidades()+", imagen='"+nuevoProducto.getImagen()+"' where productos.idproducto="+nuevoProducto.getIdProducto()+";";
			stm.executeUpdate(sql);		//idcom="+nuevoProducto.getIdComercio()+",
			
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

	    
		return "nombre producto:"+nom+"Last Name : ";
	}
	
	
	public int LoginUsuario(String param) 
	{
		Usuarios usuario = null;
		int iduser = 0;
		boolean user_exist;
		byte[] parametro=Base64.decode(param);
		ByteArrayInputStream bai=new ByteArrayInputStream(parametro);               
		ObjectInputStream ois = null;

		
		try {
			ois = new ObjectInputStream(bai);
		} catch (IOException e) {
			e.printStackTrace();
		}		
		try {
			usuario = (Usuarios) ois.readObject();
			sql = "select * from megaalmacen.usuarios where user='"+usuario.getUser()+"' && pass='"+usuario.getPass()+"';";

			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				conn=DriverManager.getConnection("jdbc:mysql://localhost/recetas","root","root");
				stm=conn.createStatement();
				rs = stm.executeQuery(sql);
				user_exist=rs.next();
				if(user_exist==true)
				{
					iduser=rs.getInt("iduser");
				}
				else
				{
					iduser=-1;
				}
				
			}catch(Exception e)
			{
				System.out.println("Error: "+e);
				e.printStackTrace();
				return -2;
			}
			
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		return iduser;
	}
	
	public int EliminarProducto(int param) 
	{
		int borrado = 0;
		
		String sql;
		
		
		sql = "delete from megaalmacen.productos where productos.idproducto="+param+";";
		
					try
					{
						Class.forName("com.mysql.jdbc.Driver");
						conn=DriverManager.getConnection("jdbc:mysql://localhost/recetas","root","root");
						stm=conn.createStatement();
						stm.execute(sql);//    executeQuery(sql_login);
				
						
						
					}catch(Exception e)
					{
						System.out.println("Error: "+e); 
						return -1;
					}
		
		return 1;
	}
	

	
public String enviarArrayList(int idcom) throws IOException {
		
		ArrayList<Producto> param=null;
		param = formarArray(idcom);//llama al metodo que se encarga de realizar la consulta de BDD
		
		ByteArrayOutputStream ba = new ByteArrayOutputStream();
	    ObjectOutputStream oos = new ObjectOutputStream(ba);
	    oos.writeObject(param);
	    byte[] arrayBytesitos =ba.toByteArray();
	    String encodedstring = Base64.encode(arrayBytesitos);
		 
		return encodedstring;
	}
	
public ArrayList<Producto> formarArray(int idcom){
		
	
	sql = "SELECT * FROM megaalmacen.productos where idcom='"+idcom+"'";
	ArrayList<Producto> items = new ArrayList<Producto>();
	try
	{
		Class.forName("com.mysql.jdbc.Driver");
		conn=DriverManager.getConnection("jdbc:mysql://localhost/recetas","root","root");
		stm=conn.createStatement();
		rs = stm.executeQuery(sql);		
		while(rs.next())
		{
			Producto p1 = new Producto(rs);
			items.add(p1);
		}
		
	}catch(Exception e)
	{
		System.out.println("Error: "+e); 
	}
	return items;
	}



public String enviarArrayListComercios(int param) throws IOException {
	
	ArrayList<Comercio> comercio=null;
	comercio = formarArrayComercios(param);//llama al metodo que se encarga de realizar la consulta de BDD
	
	ByteArrayOutputStream ba = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(ba);
    oos.writeObject(comercio);
    byte[] arrayBytesitos =ba.toByteArray();
    String encodedstring = Base64.encode(arrayBytesitos);
	 
	return encodedstring;
}

public ArrayList<Comercio> formarArrayComercios(int param){
	

sql = "SELECT * FROM megaalmacen.comercios where iduser='"+param+"';";
ArrayList<Comercio> items = new ArrayList<Comercio>();
try
{
	Class.forName("com.mysql.jdbc.Driver");
	conn=DriverManager.getConnection("jdbc:mysql://localhost/recetas","root","root");
	stm=conn.createStatement();
	rs = stm.executeQuery(sql);		
	while(rs.next())
	{
		Comercio p1 = new Comercio(rs);
		items.add(p1);
	}
	
}catch(Exception e)
{
	System.out.println("Error: "+e); 
}
return items;
}


	
	
}

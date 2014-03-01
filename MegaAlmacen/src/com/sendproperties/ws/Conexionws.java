package com.sendproperties.ws;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.kobjects.base64.Base64;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;


	class Conexionws {
		static String ip;//="192.168.1.48"; //"192.168.14.205"; //"192.168.14.106";
		static String port;
		String namespace="http://ws.sendproperties.com";
		String url="http://"+ip+":"+port+"/wsmegaalmacen/services/Bdservices?wsdl";
		

	public String solicitarProductos(int idcom ) throws IOException {
		final String NAMESPACE = this.namespace; 
	    final String URL = url;
	    final String METHOD_NAME = "enviarArrayList";
		final String SOAP_ACTION = namespace+"/"+METHOD_NAME+"/"; //"http://ws.sendproperties.com/enviarArrayList/";
	    
		String respuesta="";
	    SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
	    
//	    Pass value for fname variable in the web service
        PropertyInfo lnameProp =new PropertyInfo();
        lnameProp.setName("param");
        lnameProp.setValue(idcom);
        lnameProp.setType(Integer.class);
        request.addProperty(lnameProp); 
	    
	    
	    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	    envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        
        try {
           androidHttpTransport.call(SOAP_ACTION, envelope);
           SoapPrimitive response = (SoapPrimitive)envelope.getResponse();
           respuesta=response.toString();
        	
  
        } catch (Exception e) {
            System.out.println("Error de aqui en la conexion http"+e);
        }
		return respuesta;
		
	}
	
	
	public String solicitarComercios(int iduser) throws IOException {
		final String NAMESPACE = this.namespace; 
	    final String URL = url;
	    final String METHOD_NAME = "enviarArrayListComercios";
		final String SOAP_ACTION = namespace+"/"+METHOD_NAME+"/"; //"http://ws.sendproperties.com/enviarArrayList/";
	    
		String respuesta="";
	    SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
	    
//      Pass value for fname variable in the web service
        PropertyInfo lnameProp =new PropertyInfo();
        lnameProp.setName("param");
        lnameProp.setValue(iduser);
        lnameProp.setType(Integer.class);
        request.addProperty(lnameProp); 
	    
	    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	    envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        
        try {
           androidHttpTransport.call(SOAP_ACTION, envelope);
           SoapPrimitive response = (SoapPrimitive)envelope.getResponse();
           respuesta=response.toString();
        	
  
        } catch (Exception e) {
            System.out.println("Error de aqui en la conexion http"+e);
            e.printStackTrace();
            
        }
		return respuesta;
		
	}
		
		
		
		

	public String enviarProducto(Producto objeto) throws IOException {
		String respuesta = "nada";
		
		final String NAMESPACE = this.namespace; 
	    final String URL = url;
	    final String METHOD_NAME = "altaProducto";
		final String SOAP_ACTION = namespace+"/"+METHOD_NAME+"/"; 
		   
	    ByteArrayOutputStream ba = new ByteArrayOutputStream();
	    ObjectOutputStream oos = new ObjectOutputStream(ba);
	    oos.writeObject(objeto);
	    byte[] arrayBytesitos =ba.toByteArray();

	    String encodedstring = Base64.encode(arrayBytesitos);

	    SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
//      Pass value for fname variable in the web service
        PropertyInfo lnameProp =new PropertyInfo();
        lnameProp.setName("param");
        lnameProp.setValue(encodedstring);
        lnameProp.setType(String.class);
        request.addProperty(lnameProp);      
  
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	    envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        
        try {
           androidHttpTransport.call(SOAP_ACTION, envelope);
           SoapPrimitive response = (SoapPrimitive)envelope.getResponse();
           respuesta=response.toString();
        	
  
        } catch (Exception e) {
            System.out.println("Error de aqui en la conexion http"+e);
        }			
        return respuesta;
	}
	
	
	
	public String modificarProducto(Producto objeto) throws IOException {
		String respuesta = "nada";
		
		final String NAMESPACE = this.namespace; 
	    final String URL = url;
	    final String METHOD_NAME = "modificarProducto";
		final String SOAP_ACTION = namespace+"/"+METHOD_NAME+"/"; 	    
	   
	    ByteArrayOutputStream ba = new ByteArrayOutputStream();
	    ObjectOutputStream oos = new ObjectOutputStream(ba);
	    oos.writeObject(objeto);
	    byte[] arrayBytesitos =ba.toByteArray();

	    
	    String encodedstring = Base64.encode(arrayBytesitos);	    
	    SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
	    
//      Pass value for fname variable in the web service
        PropertyInfo lnameProp =new PropertyInfo();
        lnameProp.setName("param");
        lnameProp.setValue(encodedstring);
        lnameProp.setType(String.class);
        request.addProperty(lnameProp);      
  
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	    envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        
        try {
           androidHttpTransport.call(SOAP_ACTION, envelope);
           SoapPrimitive response = (SoapPrimitive)envelope.getResponse();
           respuesta=response.toString();
        	
  
        } catch (Exception e) {
            System.out.println("Error de aqui en la conexion http"+e);
        }			
        return respuesta;
	}
	
	
	public int LoginUsuario(Usuarios objeto) throws IOException {
		
		int respuesta = 0;
		
		final String NAMESPACE = this.namespace; 
	    final String URL = url;
	    final String METHOD_NAME = "LoginUsuario";
		final String SOAP_ACTION = namespace+"/"+METHOD_NAME+"/"; 
	   
	    ByteArrayOutputStream ba = new ByteArrayOutputStream();
	    ObjectOutputStream oos = new ObjectOutputStream(ba);
	    oos.writeObject(objeto);
	    byte[] arrayBytesitos =ba.toByteArray();
	    String encodedstring = Base64.encode(arrayBytesitos);

	    SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
//      Pass value for fname variable in the web service
        PropertyInfo lnameProp =new PropertyInfo();
        lnameProp.setName("param");
        lnameProp.setValue(encodedstring);
        lnameProp.setType(String.class);
        request.addProperty(lnameProp);      
  
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	    envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        
        try {
           androidHttpTransport.call(SOAP_ACTION, envelope);
           SoapPrimitive response = (SoapPrimitive)envelope.getResponse();
           respuesta=Integer.valueOf(response.toString());
        	
  
        } catch (Exception e) {
            System.out.println("Error de aqui en la conexion http"+e);
            return -2;
        }			
		
        return respuesta;
		
	}
	
	public int Eliminar(int idproducto)throws IOException{
		final String NAMESPACE = this.namespace; 
	    final String URL = url;
	    final String METHOD_NAME = "EliminarProducto";
		final String SOAP_ACTION = namespace+"/"+METHOD_NAME+"/"; //"http://ws.sendproperties.com/enviarArrayList/";
	    
		int borrado = 0;
	    SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
	    
//      Pass value for fname variable in the web service
        PropertyInfo lnameProp =new PropertyInfo();
        lnameProp.setName("param");
        lnameProp.setValue(idproducto);
        lnameProp.setType(Integer.class);
        request.addProperty(lnameProp); 
	    
	    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	    envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        
        try {
           androidHttpTransport.call(SOAP_ACTION, envelope);
           SoapPrimitive response = (SoapPrimitive)envelope.getResponse();
           borrado=Integer.valueOf(response.toString());
        	
  
        } catch (Exception e) {
            System.out.println("Error de aqui en la conexion http"+e);
        }
		
		return borrado;
	}
	
	
	
}
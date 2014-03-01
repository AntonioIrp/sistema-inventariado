package com.sendproperties.ws;


//import com.example.notasdelbuscador.String;

import java.io.IOException;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertarProducto extends Activity {

	//Precisa de implementación para la lectura del idComercio y la asignacion del idProducto
	private int idcom;
	private int idProducto = 680;
	public boolean backButton = false;
	 public boolean homeButton = false;
	 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_producto);
    	
        Bundle bundle=getIntent().getExtras();
        this.idcom=(Integer) bundle.get("idcom");
        
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	    if (keyCode == KeyEvent.KEYCODE_BACK) 
    	    {
    	    	backButton = true;
    	    	Intent i = new Intent(getApplicationContext(), Productos.class);
    	    	i.putExtra("idcom", idcom);
    	    	startActivity(i);
    	     	this.finish();
    	     return true;
    	    }else if(keyCode == KeyEvent.KEYCODE_HOME)
    	    {
    	    	homeButton = true;
    	    }
    	    return super.onKeyDown(keyCode, event);
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_insertar_producto, menu);
        return true;
    }
    
    public void onClickAltaProducto(View v)
    {
    	EditText nombre,tipo,descripcion,ubicacion,unidades,urlImagen;
    	
    	nombre=(EditText)findViewById(R.id.editText1);
    	tipo=(EditText)findViewById(R.id.editText2);
    	descripcion=(EditText)findViewById(R.id.editText3);
    	ubicacion=(EditText)findViewById(R.id.editText4);
    	unidades=(EditText)findViewById(R.id.editText5);
    	urlImagen=(EditText)findViewById(R.id.editText6);
    	
    	Producto nuevoProducto = new Producto(
    			nombre.getEditableText().toString(),
    			descripcion.getEditableText().toString(),
    			ubicacion.getEditableText().toString(),
    			tipo.getEditableText().toString(),
    			urlImagen.getEditableText().toString(),
    			Integer.valueOf(unidades.getEditableText().toString()),
    			idProducto,
    			idcom);
    	
        Conexionws conn=new Conexionws();
        try {
			conn.enviarProducto(nuevoProducto);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Toast.makeText(this,"El producto NO ha sido dado de alta", Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}
        Toast.makeText(this,"El producto ha sido dado de alta satisfactoriamente", Toast.LENGTH_SHORT).show();
       
        Intent i = new Intent(getApplicationContext(), Productos.class);
        i.putExtra("idcom", idcom);
    	
        this.finish();  
        startActivity(i);

    	
    }

	public int getIdcom() {
		return idcom;
	}

	public void setIdcom(int idcom) {
		this.idcom = idcom;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
}

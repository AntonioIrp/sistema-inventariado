package com.sendproperties.ws;


//import com.example.notasdelbuscador.String;

import java.io.IOException;



import android.os.Bundle;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

//por si queremos refrescar un activity

//finish();
//startActivity(getIntent());

public class ModificarProducto extends Activity {
	EditText nombre,tipo,descripcion,ubicacion,unidades,urlImagen;
	
	private int idcom;
	private int idProducto;
	private Producto p;
    public boolean backButton = false;
    public boolean homeButton = false;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_producto);
        
        Bundle bundle=getIntent().getExtras(); 
//        this.idcom=(Integer)bundle.get("idcom");
        
        p=(Producto)bundle.get("producto");
        
        idcom=p.getIdComercio();
        idProducto = p.getIdProducto();
        
        nombre=(EditText)findViewById(R.id.editText1);
    	tipo=(EditText)findViewById(R.id.editText2);
    	descripcion=(EditText)findViewById(R.id.editText3);
    	ubicacion=(EditText)findViewById(R.id.editText4);
    	unidades=(EditText)findViewById(R.id.editText5);
    	urlImagen=(EditText)findViewById(R.id.editText6);
    	
    	nombre.setText(p.getNombre());
    	tipo.setText(p.getTipo());
    	descripcion.setText(p.getDescripcion());
    	ubicacion.setText(p.getUbicacion());
    	unidades.setText(String.valueOf(p.getUnidades()));
    	urlImagen.setText(p.getImagen());
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
    
    public void onClickModificarProducto(View v)
    {
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
        try 
        {
        	conn.modificarProducto(nuevoProducto);
		} 
        catch (IOException e) 
        {
			Toast.makeText(this,"ERROR: No se ha podido modificar el producto", Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}
        
        Toast.makeText(this,"Se han modificado los atributos correctamente", Toast.LENGTH_SHORT).show();
       
        Intent i = new Intent(getApplicationContext(), Productos.class);
    	i.putExtra("idcom", idcom);
       
    	this.finish();
    	startActivity(i);
    	

    	
    }

    public void OnClicVolver(View v)
    {
    	Intent i = new Intent(getApplicationContext(), Productos.class);
    	i.putExtra("idcom", idcom);
    	this.finish();
    	startActivity(i);
    }
    
    
	public int Idcom() {
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
	

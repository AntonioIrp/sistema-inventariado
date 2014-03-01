package com.sendproperties.ws;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OptionalDataException;
import java.util.ArrayList;




import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

public class Comercios extends Activity {

	 private ArrayList<Comercio> comercios;
	 int iduser;
	 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comercios);
        
        Bundle bundle=getIntent().getExtras();
        iduser=(Integer) bundle.get("iduser");
        
        ListView lv = (ListView)findViewById(R.id.listViewComercios);
//        TextView texto=(TextView) findViewById(R.id.textView1);
        
        comercios=obtenerComercios();
              
        ComerciosAdapter adapter = new ComerciosAdapter(this, comercios);
        lv.setAdapter(adapter);
        
     
     
//     lv.setOnItemLongClickListener (new OnItemLongClickListener() {
//    	  public boolean onItemLongClick(android.widget.AdapterView<?> parent, View view,int position, long id) {
// 
//    		  Log.v("long clicked","position"+" "+position);
//    		  return true;
//    	  }
//    	});
//     
	lv.setOnItemClickListener(new ListView.OnItemClickListener() {
		public void onItemClick(android.widget.AdapterView<?> parent, View view,int position, long id) {
			Intent newActivity = new Intent(getApplicationContext(), Productos.class);	
			Comercio p = (Comercio)comercios.get(position);
			int idcom=p.getIdCom();
			newActivity.putExtra("idcom", idcom);	
			startActivity(newActivity);	
		}
	});
	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_productos, menu);
        return true;
    }
    
    
   
    
    public void onClickNuevoProducto(View v)
    {
    	Intent i = new Intent(this,InsertarProducto.class);
    	startActivity(i);
    	
    }
    
    private ArrayList<Comercio> obtenerComercios() 
    {
    	Conexionws conn=new Conexionws();
    	
    	ArrayList<Comercio> comercios = null;
    	String respuesta=null;
		
    	try {
			respuesta = conn.solicitarComercios(iduser);
			comercios=decodificacion(respuesta);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	
		return comercios;
    
    }
    
    @SuppressWarnings("unchecked")
	public ArrayList<Comercio> decodificacion(String respuesta)
    {
    	ArrayList<Comercio> items = null;
    	try 
    	{
			byte[] parametro=org.kobjects.base64.Base64.decode(respuesta);
			ByteArrayInputStream bai=new ByteArrayInputStream(parametro);               
		    ObjectInputStream ois = null;
			try 
			{
				ois = new ObjectInputStream(bai);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		
				items=(ArrayList<Comercio>) ois.readObject();
				ois.close();
    	} catch (OptionalDataException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	return items;
    }
    
}

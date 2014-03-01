package com.sendproperties.ws;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OptionalDataException;
import java.util.ArrayList;




import android.os.Bundle;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

public class Productos extends Activity {
	 private ArrayList<Producto> itemsCompra;
	 int idcom;
	 Producto p;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        
        Bundle bundle=getIntent().getExtras();
//        idcom=(Integer) bundle.get("idcom");
        this.idcom=bundle.getInt("idcom");
        
        ListView lv = (ListView)findViewById(R.id.listView);       
        itemsCompra = obtenerItems();
        final ItemCompraAdapter adapter = new ItemCompraAdapter(this, itemsCompra);
        lv.setAdapter(adapter);
        
     
     lv.setOnItemLongClickListener (new OnItemLongClickListener() {
    	  public boolean onItemLongClick(android.widget.AdapterView<?> parent, View view,final int position, long id) {
 
    		  Log.v("long clicked","position"+" "+position);
    		  p = (Producto)itemsCompra.get(position);
    		 
    		  final AlertDialog alertDialog = new AlertDialog.Builder(view.getContext()).create();
    		  alertDialog.setTitle("¿Quieres eliminar: "+p.getNombre()+" ?");
    		  alertDialog.setMessage("Al pulsar SI se eliminará el elemento");
    		 
			  alertDialog.setButton("SI",new DialogInterface.OnClickListener() {
				  public void onClick(DialogInterface dialog, int which) 
				  {
					  	int borrado;
		                int eliminar=p.getIdProducto(); 
		                Conexionws conn= new Conexionws();
		                try {
							borrado=conn.Eliminar(eliminar);
							//lv. refreshDrawableState();//   refreshDrawableState()
							//adapter.notifyDataSetChanged();
//							lv.invalidate();
//							itemsCompra = obtenerItems();
//							lv.setAdapter(adapter);
//							
//							adapter.notifyDataSetChanged();
//							
//							lv.refreshDrawableState();
							itemsCompra.remove(position);
							adapter.notifyDataSetChanged();
							if(borrado==1)
							{
								Log.v("borrado", "borrado correctamente");
							}
							else
							{
								Log.v("noborrado", "no borrado correctamente");
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
		                                                
		          }
		       });
			  
			  alertDialog.setButton2("NO",new DialogInterface.OnClickListener() {
				  public void onClick(DialogInterface dialog, int which) 
				  {
		                 
		                                                
		          }
		       });
    		
    		  alertDialog.setIcon(R.drawable.warning);
    		  alertDialog.show();
    		  
    		  return true;
    	  }
    	});

     
    
	lv.setOnItemClickListener(new ListView.OnItemClickListener() 
	{		
		public void onItemClick(android.widget.AdapterView<?> parent, View view,int position, long id)
		{
			Intent newActivity = new Intent(getApplicationContext(), ModificarProducto.class);	
			Producto p = (Producto)itemsCompra.get(position);
			newActivity.putExtra("producto", p);
			newActivity.putExtra("idcomercio", idcom);
			
			startActivity(newActivity);	
			finish();
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
    	i.putExtra("idcom", idcom);
    	startActivity(i);
    	finish();
    }
    
    private ArrayList<Producto> obtenerItems() 
    {
    	Conexionws conn=new Conexionws();
    	ArrayList<Producto> items = null;
    	String respuesta=null;
		try {
			respuesta = conn.solicitarProductos(idcom);
			items=decodificacion(respuesta);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	
		return items;
    }
    
    @SuppressWarnings("unchecked")
	public ArrayList<Producto> decodificacion(String respuesta)
    {
    	ArrayList<Producto> items = null;
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
		
				items=(ArrayList<Producto>) ois.readObject();
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

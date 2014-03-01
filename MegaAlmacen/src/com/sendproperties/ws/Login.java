package com.sendproperties.ws;

import java.io.IOException;
import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {

    int iduser;
    public boolean backButton = false;
    public boolean homeButton = false;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        
        
        //Producto p1 = new Producto("Producto", "descripcion", "ubicacion","tipo", "imagen", 3, 1);
              
    }
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
    
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.crear:
			final Dialog dialog = new Dialog(this); 
	        dialog.setContentView(R.layout.dialog_configuracion);
	        dialog.setTitle("Configuración TCP/IP ");                          
	        dialog.show();
	        Button b = (Button)dialog.findViewById(R.id.buttonIP);
	        
	        b.setOnClickListener(new OnClickListener() {
	           

				public void onClick(View v) {
					
					
					EditText ipEdit=(EditText)dialog.findViewById(R.id.ip);
			    	EditText portEdit=(EditText)dialog.findViewById(R.id.port);
			    	
			    	Conexionws.ip =	ipEdit.getEditableText().toString();
			    	Conexionws.port = portEdit.getEditableText().toString();
			    	
					dialog.cancel();					
				}});
	        
			
			return true;
		}
		return false;
    }
    
    
    
    
    
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	    if (keyCode == KeyEvent.KEYCODE_BACK) 
    	    {
    	    	backButton = true;
    	    	
    	    	final Builder alertDialog = new AlertDialog.Builder(this);
      		  alertDialog.setTitle("¿Quieres salir de la aplicacion?");
      		 
      		 
  			  alertDialog.setPositiveButton("SI",new DialogInterface.OnClickListener() {
  				  public void onClick(DialogInterface dialog, int which) 
  				  {
  					finish();  	
  				  }
  				 } );
  			  
  			  
  			  alertDialog.setNegativeButton("NO",new DialogInterface.OnClickListener() {
  				  public void onClick(DialogInterface dialog, int which) 
  				  {
  		                 
  		                                          
  		          }
  		       });
      		
      		  alertDialog.setIcon(R.drawable.warning);
      		  alertDialog.show();
    
    	    	
    	     	
    	     return true;
    	    }else if(keyCode == KeyEvent.KEYCODE_HOME)
    	    {
    	    	homeButton = true;
    	    }
    	    return super.onKeyDown(keyCode, event);
    }
    
    
    public void OnLoginUsuario(View v)
    {
    	EditText usuarioP=(EditText)findViewById(R.id.user);
        EditText contraseñaP=(EditText)findViewById(R.id.pass);
        
    	String usuario = usuarioP.getText().toString();
        String contraseña = contraseñaP.getText().toString();
        
        Usuarios user=new Usuarios(usuario, contraseña);
        user.setUser(usuario);
        user.setPass(contraseña);
        
    
        
        try {
            Conexionws conn=new Conexionws();
			iduser=conn.LoginUsuario(user);
			if(iduser>=0)
			{
				Toast.makeText(this,usuario+" has iniciado sesión", Toast.LENGTH_SHORT).show();
				Intent newActivity = new Intent(getApplicationContext(), Comercios.class);	
				newActivity.putExtra("iduser", iduser);	
				startActivity(newActivity);
			}
			if(iduser==-1)
			{
				Toast.makeText(this,"El identificador o contraseña no son correctos", Toast.LENGTH_SHORT).show();
			}
			if(iduser==-2)
			{
				Toast.makeText(this,"El servidor no esta activo", Toast.LENGTH_SHORT).show();
			}
		
		} catch (Exception e) {
			
			Toast.makeText(this,"El servidor no esta activo", Toast.LENGTH_SHORT).show();
		}
    }
    
    public void OnDialogAyuda(View v)
    {
    	Dialog dialog = new Dialog(this); 
        dialog.setContentView(R.layout.dialog_ayuda);
        dialog.setTitle("¿Por donde comienzo? ");                          
        dialog.show();
    }
 
    public void OnDialogAcercaDe(View v)
    {
    	final Dialog dialog = new Dialog(Login.this);
    	dialog.setContentView(R.layout.dialog_acerca_de);
    	final MediaPlayer sound = MediaPlayer.create(this, R.raw.huevo_de_pascua);
        TextView huevotext=(TextView)dialog.findViewById(R.id.textView1);
        dialog.setTitle("Y hablando de...");                          
        dialog.show();
        
        huevotext.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	if (sound.isPlaying()) 
                {
                	sound.stop();
                } 
                else 
                {
                	try {
                		sound.start();
                	} catch (IllegalStateException e) 
                		{
                		e.printStackTrace();
                		}
                }
            }
        }); 
        
        
        
        
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {         
            public void onCancel(DialogInterface dialog) {
            	if (sound.isPlaying()) 
                {
                	sound.stop();
                } 
            	dialog.cancel();
            }
        });
        
    }  
    
   
    
 }
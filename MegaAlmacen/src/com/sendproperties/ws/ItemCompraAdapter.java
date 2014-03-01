package com.sendproperties.ws;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class ItemCompraAdapter extends BaseAdapter {
	private Activity activityc;
	private ArrayList<Producto> itemsc;
	InputStream inputStream = null;    
	LayoutInflater inflater;
	public ImageLoader imageLoader;
	
	public ItemCompraAdapter(Activity activity, ArrayList<Producto> items) {
		activityc = activity;
		itemsc = items;
		inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		imageLoader=new ImageLoader(activity.getApplicationContext());
	}

	public int getCount() {
		return itemsc.size();
	}

	public Object getItem(int position) {
		return itemsc.get(position);
	}

public long getItemId(int position) {
		return itemsc.get(position).getIdProducto();
	}

public View getView(int position, View convertView, ViewGroup parent) {
		View vi=convertView;
		
        if(convertView == null) 
        {
        	vi = inflater.inflate(R.layout.list_item_layout, null);
        }
          
        ImageView image = (ImageView) vi.findViewById(R.id.imagen); 
        TextView nombre = (TextView) vi.findViewById(R.id.nombre);
        TextView tipo = (TextView) vi.findViewById(R.id.tipo);
        
        Producto item = itemsc.get(position);
        
        nombre.setText(item.getNombre());        
        tipo.setText(item.getTipo());
        imageLoader.DisplayImage(item.getImagen(), image);
         
        return vi;
	}
}

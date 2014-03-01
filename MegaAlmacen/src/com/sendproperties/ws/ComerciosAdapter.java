package com.sendproperties.ws;

import java.io.InputStream;
import java.util.ArrayList;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class ComerciosAdapter extends BaseAdapter {
	private Activity activityc;
	private ArrayList<Comercio> itemsc;
	InputStream inputStream = null;    
	LayoutInflater inflater;
	public ImageLoader imageLoader;
	
	public ComerciosAdapter(Activity activity, ArrayList<Comercio> items) {
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
		return itemsc.get(position).getIdCom();
	}

public View getView(int position, View convertView, ViewGroup parent) {
		View vi=convertView;
		
        if(convertView == null) 
        {
        	vi = inflater.inflate(R.layout.list_comercios_layout, null);
        }
         
        TextView nombre = (TextView) vi.findViewById(R.id.nombreComercio);
        TextView descripcion = (TextView) vi.findViewById(R.id.descripComercio);
        Comercio item = itemsc.get(position);
        
        nombre.setText(item.getComercio().toString());        
        descripcion.setText(item.getDescripcion().toString());
        return vi;
	}
}

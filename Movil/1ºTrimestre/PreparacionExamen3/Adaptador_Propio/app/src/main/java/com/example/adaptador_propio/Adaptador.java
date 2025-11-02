package com.example.adaptador_propio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
    private ArrayList<Datos> datos;
    private Context contexto;

    public Adaptador(Context contexto, ArrayList<Datos>datos){
        this.contexto = contexto;
        this.datos = datos;
    }

    @Override
    public int getCount() {
        return datos.size();
    }

    @Override
    public Object getItem(int position) {
        return datos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater mostrado = LayoutInflater.from(contexto);
        View elemento = mostrado.inflate(R.layout.elemento, parent, false);

        TextView txt1 = (TextView) elemento.findViewById(R.id.texto1);
        txt1.setText(datos.get(position).getTxt1());

        TextView txt2 = (TextView) elemento.findViewById(R.id.texto2);
        txt2.setText(datos.get(position).getTxt2());

        return elemento;
    }
}
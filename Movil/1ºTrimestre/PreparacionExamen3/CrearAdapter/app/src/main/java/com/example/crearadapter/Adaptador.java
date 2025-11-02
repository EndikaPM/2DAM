package com.example.crearadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Adaptador extends ArrayAdapter<Datos> {
    private Datos[]datos;
    public Adaptador(Context context, Datos[] datos){
        super(context, R.layout.elemnto, datos);
        this.datos = datos;
    }

    public View getView(int posicion, View convertirView, ViewGroup parent){
        LayoutInflater mostrado = LayoutInflater.from(getContext());
        View elemento = mostrado.inflate(R.layout.elemnto, parent, false);
        TextView texto1 = (TextView) elemento.findViewById(R.id.texto1);
        texto1.setText(datos[posicion].getText1());
        TextView texto2 = (TextView) elemento.findViewById(R.id.texto2);
        texto2.setText(datos[posicion].getText2());
        return elemento;
    }
}

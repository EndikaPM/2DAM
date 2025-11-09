package com.example.proyectolibro.Controller;

import android.content.Context;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyectolibro.Model.Libro;
import com.example.proyectolibro.R;

import java.util.ArrayList;

 public class LibroAdaptador extends Adaptador {
    public LibroAdaptador(Context contexto, int R_layout_idView, ArrayList<Libro> entrada){
        super(contexto, R_layout_idView, entrada);
    }

    @Override
    public void onEntrada(Object entrada, View view) {
        if(entrada != null){
            Libro libro = (Libro) entrada;
           ImageView imagen = view.findViewById(R.id.imagen_libro);
            TextView titulo = view.findViewById(R.id.titulo_libro);
            TextView texto = view.findViewById(R.id.texto_libro);
            TextView fecha = view.findViewById(R.id.fecha_libro);

            imagen.setImageResource(libro.getImagen());
            titulo.setText(libro.getTitulo());
            texto.setText(libro.getTexto());
            fecha.setText(libro.getFecha());
        }
    }
}

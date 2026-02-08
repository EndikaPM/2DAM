package com.example.proyectolibro.Controller;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.proyectolibro.R;

public class PantallaDetalles extends AppCompatActivity {
    TextView titulo;
    ImageView favorito;
    ImageView imgenLibro;
    TextView descrip;
    TextView fecha;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_detalles);

        imgenLibro = findViewById(R.id.imagen_libro);
        titulo = findViewById(R.id.titulo_libro);
        descrip = findViewById(R.id.texto_libro);
        fecha = findViewById(R.id.fecha_libro);
        favorito = findViewById(R.id.icono_favorito);
        back = findViewById(R.id.backDetalle);

        Intent recibir = getIntent();
        Bundle datosRecibidos = recibir.getExtras();

        if (datosRecibidos != null){
            // Verificar si se recibió un Bitmap o un recurso drawable
            Bitmap imagenBitmap = datosRecibidos.getParcelable("imagenBitmap");
            if (imagenBitmap != null) {
                imgenLibro.setImageBitmap(imagenBitmap);
            } else {
                imgenLibro.setImageResource(datosRecibidos.getInt("imagen"));
            }

            titulo.setText(datosRecibidos.getString("titulo"));
            descrip.setText(datosRecibidos.getString("contenido"));
            fecha.setText(datosRecibidos.getString("fecha"));
            boolean esFavorito = datosRecibidos.getBoolean("favorito");
            if (esFavorito){
                favorito.setImageResource(R.drawable.ic_heart_filled);
            }else{
                favorito.setImageResource(R.drawable.ic_heart_empty);
            }
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent volver = new Intent(PantallaDetalles.this, Activity2.class);
                startActivity(volver);
            }
        });
    }
}
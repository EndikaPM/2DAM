package com.example.tarea1tarea2.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tarea1tarea2.R;

public class Activity3 extends AppCompatActivity {

    EditText titulo;
    EditText contenido;
    Button guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        titulo = findViewById(R.id.titulo);
        contenido = findViewById(R.id.contenido);
        guardar = findViewById(R.id.guardar);


        String titu = titulo.getText().toString();
        String conte = contenido.getText().toString();


        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent datos = new Intent();
                datos.putExtra("tituEdi",titu);
                datos.putExtra("conteEdi", conte);

                setResult(RESULT_OK,datos);

                finish();
            }
        });
    }

}
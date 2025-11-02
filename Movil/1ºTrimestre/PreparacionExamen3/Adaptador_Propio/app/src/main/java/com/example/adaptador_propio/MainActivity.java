package com.example.adaptador_propio;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Datos> datos = new ArrayList<Datos>();
        datos.add(new Datos("Línea Superior 1", "Línea Inferior 1"));
        datos.add(new Datos("Línea Superior 2", "Línea Inferior 2"));
        datos.add(new Datos("Línea Superior 3", "Línea Inferior 3"));
        datos.add(new Datos("Línea Superior 4", "Línea Inferior 4"));

        Adaptador adaptador = new Adaptador(this, datos);
        lista = (ListView) findViewById(R.id.lista);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Hay que parsear al tipo Dato para lo coja
                String pulsado = ((Datos)parent.getItemAtPosition(position)).getTxt1();
                String pulsado2 = ((Datos)parent.getItemAtPosition(position)).getTxt2();

                Toast.makeText(MainActivity.this, "Has pulsado: " + pulsado + "\n" + pulsado2,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
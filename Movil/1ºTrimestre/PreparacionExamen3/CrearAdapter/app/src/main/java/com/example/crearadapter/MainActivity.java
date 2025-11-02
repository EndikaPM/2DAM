package com.example.crearadapter;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Datos[] datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datos = new Datos[]{
                new Datos("Linea superior 1", "Linea inferior 1"),
                new Datos("Linea superior 2", "Linea inferior 2"),
                new Datos("Linea superior 3", "Linea inferior 3"),
                new Datos("Linea superior 4", "Linea inferior ")
        };

        ListView listado = (ListView) findViewById(R.id.lista);
        Adaptador miAdaptador = new Adaptador(this, datos);
        listado.setAdapter(miAdaptador);

        View miCabecera = getLayoutInflater().inflate(R.layout.cabecera, null);
        listado.addHeaderView(miCabecera);

        listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selecionado = ((Datos) parent.getItemAtPosition(position)).getText1();
                Toast.makeText(MainActivity.this, selecionado, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
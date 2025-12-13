package com.example.actividad10_1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button boton1, boton2;
    EditText textoEditar1, textoEditar2;
    TextView texto1, texto2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton1 = findViewById(R.id.boton1);
        boton2 = findViewById(R.id.boton2);
        textoEditar1 = findViewById(R.id.EditarTexto1);
        textoEditar2 = findViewById(R.id.EditarTexto2);
        texto1 = findViewById(R.id.text1);
        texto2 = findViewById(R.id.text2);

        boton1.setText("Guardar");
        boton2.setText("Recuperar");

        SharedPreferences prefText1 = getSharedPreferences("EditText1", MODE_PRIVATE);

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor1 = prefText1.edit();

                String texto1Guardar = textoEditar1.getText().toString();
                String texto2Guardar = textoEditar2.getText().toString();

                editor1.putString("texto1", texto1Guardar);
                editor1.putString("texto2", texto2Guardar);

                editor1.commit();// me recomienda el editor apply() en vez de commit()

                textoEditar1.setText("");
                textoEditar2.setText("");
            }
        });

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String texto1Recuperado = prefText1.getString("texto1", "No hay texto guardado");
                String texto2Recuperado = prefText1.getString("texto2", "No hay texto guardado");

                texto1.setText(texto1Recuperado);
                texto2.setText(texto2Recuperado);
            }
        });
    }
}
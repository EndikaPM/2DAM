package com.example.actividad_7_3;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditText text = findViewById(R.id.texto);
        Button boton = findViewById(R.id.boton);

        boton.setOnClickListener(v -> {
            String tex = text.getText().toString();
            if (!tex.isEmpty()) {
                Toast.makeText(this, tex,Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"Introduce un texto ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
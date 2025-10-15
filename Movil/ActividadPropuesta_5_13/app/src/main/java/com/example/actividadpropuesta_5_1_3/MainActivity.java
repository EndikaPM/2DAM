package com.example.actividadpropuesta_5_1_3;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

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
        TextView text = (TextView) findViewById(R.id.textInp);
        text.setTextSize(20);
        text.setGravity(Gravity.CENTER);
        TextView texto = (TextView) findViewById(R.id.textInp2);
        texto.setText("\nTexto añadido con Append desde Java");
        texto.setTextSize(20);
        texto.setTextColor(Color.BLUE);
        text.append(texto.getText());
    }
}
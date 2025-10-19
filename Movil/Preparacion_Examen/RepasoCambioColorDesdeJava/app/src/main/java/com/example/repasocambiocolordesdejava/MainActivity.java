package com.example.repasocambiocolordesdejava;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
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

        TextView texto = (TextView) findViewById(R.id.textoCambiar);

        Button boton1 = (Button)findViewById(R.id.boton1);
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                texto.setTextColor(Color.BLUE);
                texto.append("\nBoton 1 pulsado");
            }
        });

        Button boton2 = (Button) findViewById(R.id.boton2);
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SpannableString addTex = new SpannableString("\ntexto añadido desde java");
                addTex.setSpan(new ForegroundColorSpan(Color.MAGENTA),0,addTex.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                texto.append(addTex);
                SpannableString addTex2 = new SpannableString("\nBoton 2 Pulsado");
                addTex2.setSpan(new ForegroundColorSpan(Color.GREEN),0,addTex2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                texto.append(addTex2);
            }
        });



    }
}
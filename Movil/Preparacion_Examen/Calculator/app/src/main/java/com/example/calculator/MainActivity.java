package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private StringBuilder textoPantalla = new StringBuilder();
    float resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView pantall = findViewById(R.id.pantalla);
    //numeros
        Button uno = (Button) findViewById(R.id.uno);
        Button dos = (Button) findViewById(R.id.dos);
        Button tres = (Button) findViewById(R.id.tres);
        Button cuatro = (Button) findViewById(R.id.cuatro);
        Button cinco = (Button) findViewById(R.id.cinco);
        Button seis = (Button) findViewById(R.id.seis);
        Button siete = (Button) findViewById(R.id.siete);
        Button ocho = (Button) findViewById(R.id.ocho);
        Button nueve = (Button) findViewById(R.id.nueve);
        Button cero = (Button) findViewById(R.id.cero);
        //boton funciones
        Button sum = (Button) findViewById(R.id.suma);
        Button rest = (Button) findViewById(R.id.rest);
        Button div = (Button) findViewById(R.id.div);
        Button mult = (Button) findViewById(R.id.muti);
        Button igual = (Button) findViewById(R.id.igual);
        //resto

        Button Cc = (Button) findViewById(R.id.limp);
        Button coma = (Button)findViewById(R.id.coma);
        /* forma que v en internet para repasar y reutilizar codigo
        View.OnClickListener botGeneral = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button botonG = (Button) v;
                textoPantalla.append(botonG.getText());
                pantall.setText(textoPantalla.toString());
            }
        };
        */
        uno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textoPantalla.append(uno.getText());
                pantall.setText(textoPantalla.toString());
            }
        });
        dos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textoPantalla.append(dos.getText());
                pantall.setText(textoPantalla.toString());
            }
        });
        tres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textoPantalla.append(tres.getText().toString());// Comprobar si funciona
                pantall.setText(textoPantalla);
            }
        });
        cuatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textoPantalla.append(cuatro.getText().toString());
                pantall.setText(textoPantalla);
            }
        });
        cinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pantall.setText(textoPantalla.append(cinco.getText().toString()));
            }
        });
        seis.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                pantall.setText(textoPantalla.append(seis.getText().toString()));
            }
        });
        siete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pantall.setText(textoPantalla.append(siete.getText().toString()));
            }
        });
        ocho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pantall.setText(textoPantalla.append(ocho.getText().toString()));
            }
        });
        nueve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textoPantalla.append(nueve.getText().toString());
                pantall.setText(textoPantalla);
            }
        });
        cero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textoPantalla.append(cero.getText().toString());
                pantall.setText(textoPantalla);
            }
        });
        sum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pantall.setText(textoPantalla.append(sum.getText().toString()));
            }
        });
        rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pantall.setText(textoPantalla.append(rest.getText().toString()));
            }
        });
        mult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pantall.setText(textoPantalla.append(mult.getText().toString()));
            }
        });
        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pantall.setText(textoPantalla.append(div.getText().toString()));
            }
        });

        coma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pantall.setText(textoPantalla.append("."));
            }
        });


        igual.setOnClickListener(new View.OnClickListener() {
            String [] partes;
            @Override
            public void onClick(View v) {
                if(textoPantalla.toString().contains("+")){
                    partes = textoPantalla.toString().split("\\+");
                    resultado = sumar(Float.parseFloat(partes[0]),Float.parseFloat(partes[1]));
                    pantall.setText(String.valueOf(resultado));
                } else if (textoPantalla.toString().contains("-")) {
                    partes = textoPantalla.toString().split("-");
                    resultado = restar(Float.parseFloat(partes[0]), Float.parseFloat(partes[1]));
                    pantall.setText(String.valueOf(resultado));
                } else if (textoPantalla.toString().contains("*")) {
                    partes = textoPantalla.toString().split("\\*");
                    resultado = multi(Float.parseFloat(partes[0]), Float.parseFloat(partes[1]));
                    pantall.setText(String.valueOf(resultado));
                }else{
                    partes = textoPantalla.toString().split("/");
                    resultado = divi(Float.parseFloat(partes[0]),Float.parseFloat(partes[1]));
                    pantall.setText(String.valueOf(resultado));
                }
                textoPantalla.delete(0,textoPantalla.length());
                textoPantalla.append(resultado);
            }
        });

        Cc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pantall.setText("0");
                textoPantalla.delete(0,textoPantalla.length());
            }
        });

    }
    private float sumar(float a, float b){
        return a + b;
    }
    private float restar(float a, float b){
        return a - b;
    }
    private float multi(float a, float b){
        return a * b;
    }
    private float divi(float a, float b){
        return a / b;
    }
}
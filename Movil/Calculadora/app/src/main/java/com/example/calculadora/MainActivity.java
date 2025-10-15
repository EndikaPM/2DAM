package com.example.calculadora;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private StringBuilder texPantalla = new StringBuilder();
    float resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // capturamos el id de la pantalla de xml y la almacenamos en esl Text view
        TextView pantalla = findViewById(R.id.pantallaCal);

        //almacenamos los botones igual que antes
        // acuerdate endica de que ara el id es necesario llamar desde la R.
        Button uno = (Button) findViewById(R.id.uno);
        Button dos = (Button) findViewById(R.id.dos);
        Button tres = (Button) findViewById(R.id.tres);
        Button cuatro = (Button) findViewById(R.id.cuatro);
        Button cinco = (Button) findViewById(R.id.cinco);
        Button sies = (Button) findViewById(R.id.seis);
        Button siete = (Button) findViewById(R.id.siete);
        Button ocho = (Button) findViewById(R.id.ocho);
        Button nuece = (Button) findViewById(R.id.nueve);
        Button cero = (Button) findViewById(R.id.cero);

        //almacenamos los botones de funcion
        Button suma = (Button) findViewById(R.id.suma);
        Button reta = (Button) findViewById(R.id.menos);
        Button multipli = (Button) findViewById(R.id.multipli);
        Button divi = (Button) findViewById(R.id.divi);
        Button igual = (Button) findViewById(R.id.equal);

        Button coma = (Button) findViewById(R.id.coma);
        Button Cc = (Button) findViewById(R.id.reinicio);

        View.OnClickListener bot = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button boto = (Button) v;
                texPantalla.append(boto.getText());
                pantalla.setText(texPantalla.toString());
            }
        };

        //asignamos los listener a cada uno de los botones
        uno.setOnClickListener(bot);
        dos.setOnClickListener(bot);
        tres.setOnClickListener(bot);
        cuatro.setOnClickListener(bot);
        cinco.setOnClickListener(bot);
        sies.setOnClickListener(bot);
        siete.setOnClickListener(bot);
        ocho.setOnClickListener(bot);
        nuece.setOnClickListener(bot);
        cero.setOnClickListener(bot);
        //tambien a los botones de funcion ver como hacer que no entren en el metodo
        suma.setOnClickListener(bot);
        reta.setOnClickListener(bot);
        multipli.setOnClickListener(bot);
        divi.setOnClickListener(bot);

        //---coma--- no se aun como gestionarla
        coma.setOnClickListener(bot);
        Cc.setOnClickListener(bot);


        // el igual mirar a ver otro modo paraque no de problemas
        igual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //no permite el split directamente porque no lo se...
                String signoOperacion = texPantalla.toString();
                String [] partes;

                if (signoOperacion.contains("+")){
                    //da error si no pones la barras de escape porque no lo coge como texto sino suma o concatena
                    // eso tienes que tenerlo en cuenta tonto
                    partes = signoOperacion.split("\\+");
                    resultado = suma(Float.parseFloat(partes[0]),Float.parseFloat(partes[1]));
                    pantalla.setText(String.valueOf(resultado));
                } else if (signoOperacion.contains("-")) {
                    partes = signoOperacion.split("-");
                    resultado = resta(Float.parseFloat(partes[0]),Float.parseFloat(partes[1]));
                    pantalla.setText(String.valueOf(resultado));
                }else if (signoOperacion.contains("*")) {
                    partes = signoOperacion.split("\\*");
                    resultado = multi(Float.parseFloat(partes[0]),Float.parseFloat(partes[1]));
                    pantalla.setText(String.valueOf(resultado));
                }else if (signoOperacion.contains("/")) {
                    partes = signoOperacion.split("/");
                    resultado = divi(Float.parseFloat(partes[0]),Float.parseFloat(partes[1]));
                    pantalla.setText(String.valueOf(resultado));
                }else {
                    pantalla.setText("Error");
                }
            }
        });

        Cc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              pantalla.setText("0");
              texPantalla.delete(0, texPantalla.length());
              resultado = 0;
            }
        });
    }

    private float suma(float a, float b){
        return a + b;
    }
    private float resta(float a, float b){
        return a - b;
    }
    private float multi(float a, float b){
        return a * b;
    }
    private float divi(float a, float b){
        return a / b;
    }
}
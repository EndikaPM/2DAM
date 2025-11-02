package com.example.practicaintentyimeoption;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 10;
    private TextView textMostrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textMostrar = (TextView) findViewById(R.id.textoPantalla);
        EditText capturar1 = (EditText) findViewById(R.id.username);
        EditText capturar2 = (EditText) findViewById(R.id.message);
        Button enviar = (Button) findViewById(R.id.send);


        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = capturar1.getText().toString();
                String mensaje = capturar2.getText().toString();

                capturar1.setImeOptions(EditorInfo.IME_ACTION_NEXT);
                capturar2.setImeOptions(EditorInfo.IME_ACTION_GO);

                Intent mensajero = new Intent(MainActivity.this, MainActivity2.class);
                mensajero.putExtra("nameUser", usuario);
                mensajero.putExtra("nameMenssage", mensaje);
                startActivityForResult(mensajero,REQUEST_CODE);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent mensajero) {
        super.onActivityResult(requestCode, resultCode, mensajero);

        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE){
            if (mensajero.hasExtra("DatosOk")){
                String mensajeAdd = mensajero.getStringExtra("DatosOk");
                textMostrar.append("\n\n"+mensajeAdd);
                textMostrar.setTextColor(Color.GREEN);
            }
        }
    }
}
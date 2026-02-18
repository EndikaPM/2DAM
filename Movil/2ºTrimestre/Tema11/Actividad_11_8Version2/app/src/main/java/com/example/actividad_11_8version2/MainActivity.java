package com.example.actividad_11_8version2;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
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

        View viewNegro = findViewById(R.id.negro);
        View viewVerde = findViewById(R.id.verde);
        View viewRosa = findViewById(R.id.rosa);
        View viewNaranja = findViewById(R.id.naranja);

        OnLayoutTouchListener listener = new OnLayoutTouchListener() {
            @Override
            public void onLayoutPressed(String colorName) {
                Toast.makeText(MainActivity.this,
                        "Pulsado: " + colorName,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLayoutReleased(String colorName) {
                Toast.makeText(MainActivity.this,
                        "Dejado de pulsar: " + colorName,
                        Toast.LENGTH_SHORT).show();
            }
        };
        asignarListener(viewRosa, "Rosa", listener);
        asignarListener(viewNaranja, "Naranja", listener);
        asignarListener(viewNegro, "Negro",listener);
        asignarListener(viewVerde, "Verde", listener);

    }

    private void asignarListener(View view, String color, OnLayoutTouchListener lissener){
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        lissener.onLayoutPressed(color);
                        break;
                    case MotionEvent.ACTION_UP:
                        lissener.onLayoutReleased(color);
                        break;
                }
                return true;
            }
        });
    }
}
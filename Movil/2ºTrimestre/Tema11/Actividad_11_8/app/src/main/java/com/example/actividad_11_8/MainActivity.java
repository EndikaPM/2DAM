package com.example.actividad_11_8;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity  {
    View negro;
    View verde;
    View rosa;
    View naranja;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        negro = findViewById(R.id.negro);
        verde = findViewById(R.id.verde);
        rosa = findViewById(R.id.rosa);
        naranja = findViewById(R.id.naranja);

        setTouchListener(negro, "Negro");
        setTouchListener(verde, "Verde");
        setTouchListener(rosa, "Rosa");
        setTouchListener(naranja, "Naranja");
    }


    private void setTouchListener(View view, String color) {
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Toast.makeText(MainActivity.this,
                                "Pulsado " + color,
                                Toast.LENGTH_SHORT).show();
                        return true;

                    case MotionEvent.ACTION_UP:
                        Toast.makeText(MainActivity.this,
                                "Soltado " + color,
                                Toast.LENGTH_SHORT).show();
                        return true;
                }
                return false;
            }
        });
    }
}
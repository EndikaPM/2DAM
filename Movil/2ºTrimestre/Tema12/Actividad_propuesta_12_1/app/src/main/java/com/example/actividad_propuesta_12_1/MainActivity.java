package com.example.actividad_propuesta_12_1;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imagen = findViewById(R.id.imagen);

        imagen.setOnClickListener(v -> {

            ObjectAnimator movimiento = ObjectAnimator.ofFloat(imagen, "translationX", 200f);

            movimiento.setDuration(1000);
            movimiento.start();
        });
    }
}
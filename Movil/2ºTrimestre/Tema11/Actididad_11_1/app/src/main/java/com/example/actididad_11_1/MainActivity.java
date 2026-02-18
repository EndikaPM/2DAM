package com.example.actididad_11_1;

import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView top;
    private TextView bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Display pantalla = getWindowManager().getDefaultDisplay();
        Point media = new Point();
        pantalla.getSize(media);
        int ancho = media.x;
        int alto = media.y;

        top = findViewById(R.id.topForm1);
        top.setText("Con la forma de Display \nel Ancho es: " + ancho + "px y el Alto es: " + alto+"px");

        DisplayMetrics metrica = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrica);
        int anchoMetrica = metrica.widthPixels;
        int altoMetrica = metrica.heightPixels;

        bottom = findViewById(R.id.bottomForm1);
        bottom.setText("Con la forma de DisplayMetrics \nel Ancho es: " + anchoMetrica + "px y el Alto es: " + altoMetrica+"px");
    }
}
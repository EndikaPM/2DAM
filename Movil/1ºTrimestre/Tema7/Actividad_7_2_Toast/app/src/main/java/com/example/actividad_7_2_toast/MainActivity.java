package com.example.actividad_7_2_toast;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
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

        /*Toast toast = Toast.makeText(this,"Hola Jesus",Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER| Gravity.LEFT, 0,0);
        toast.show();*/

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.custom_toast_id));

        ImageView image = (ImageView) layout.findViewById(R.id.imagen_toast);
        image.setImageResource(R.drawable.outline_brightness_auto_24);
        TextView text = (TextView) layout.findViewById(R.id.texto_toast);
        text.setText("¡HOLAAA JESUSUS!");

        Toast toast2 = new Toast(this);
        toast2.setDuration(Toast.LENGTH_SHORT);
        toast2.setGravity(Gravity.CENTER, 0,0);
        toast2.setView(layout);
        toast2.show();
    }
}
package com.example.actividad_11_10;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager misSensores = (SensorManager) getSystemService(SENSOR_SERVICE);

        List<Sensor> listaSensores = misSensores.getSensorList(Sensor.TYPE_ALL);

        for (Sensor sensor : listaSensores){
            Log.d("Sensores ", sensor.getName());
        }
    }
}
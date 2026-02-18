package com.example.activity_11_11;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometro;
    private int contador = 0;
    private double x = 0, y = 0, z = 0, a = 0, amax = 0;
    private final double SHAKE_THRESHOLD = 12.0;

    private TextView tvax, tvay, tvaz, tva, tvaMax;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar vistas
        tvax = findViewById(R.id.textViewAX);
        tvay = findViewById(R.id.textViewAY);
        tvaz = findViewById(R.id.textViewAZ);
        tva = findViewById(R.id.textViewA);
        tvaMax = findViewById(R.id.textViewAmax);

        // Inicializar SensorManager
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // Definir el Runnable (Bucle de actualización de UI)
        runnable = new Runnable() {
            @Override
            public void run() {
                contador++;
                tvax.setText("X: " + String.format("%.2f", x));
                tvay.setText("Y: " + String.format("%.2f", y));
                tvaz.setText("Z: " + String.format("%.2f", z));
                tva.setText("A: " + String.format("%.2f", a));
                tvaMax.setText("Max: " + String.format("%.2f", amax));

                handler.postDelayed(this, 100);
            }
        };
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        x = event.values[0];
        y = event.values[1];
        z = event.values[2];
        a = Math.sqrt(x * x + y * y + z * z);
        if (a > amax) amax = a;

        // --- DETECCIÓN DE INCLINACIÓN (Logcat) ---
        if (x < -2) Log.d("SENSOR_INCLINA", "Derecha");
        else if (x > 2) Log.d("SENSOR_INCLINA", "Izquierda");

        if (y < -2) Log.d("SENSOR_INCLINA", "Arriba");
        else if (y > 2) Log.d("SENSOR_INCLINA", "Abajo");

        // --- DETECCIÓN DE SACUDIDA ---
        if (a > SHAKE_THRESHOLD) {
            Log.i("SENSOR_EVENTO", "¡SACUDIDA DETECTADA!");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

    @Override
    protected void onResume() {
        super.onResume();
        // REGISTRAMOS al volver a la app
        if (accelerometro != null) {
            sensorManager.registerListener(this, accelerometro, SensorManager.SENSOR_DELAY_UI);
        }
        handler.post(runnable);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // DESREGISTRAMOS para ahorrar batería y detenemos el handler
        sensorManager.unregisterListener(this);
        handler.removeCallbacks(runnable);
        Log.w("SISTEMA", "Sensor y Handler detenidos (Ahorro de batería)");
    }
}
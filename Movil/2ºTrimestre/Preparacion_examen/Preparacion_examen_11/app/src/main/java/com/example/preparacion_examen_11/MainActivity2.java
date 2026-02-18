package com.example.preparacion_examen_11;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

public class MainActivity2 extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_AUDIO = 2;
    private MediaRecorder recorder;
    private MediaPlayer player;
    private String output;

    // Controla si estamos grabando en este momento
    private boolean grabando = false;
    Button btn1Grabadora, btn2Grabadora, btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        // Ruta donde se guardará el archivo de audio
        output = getExternalFilesDir(null).getAbsolutePath() + "/miSonido.3gp";

        btn1Grabadora = findViewById(R.id.btn1Grabadora);
        btn2Grabadora = findViewById(R.id.btn2Grabadora);
        btnVolver = findViewById(R.id.btnVolver);

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Usamos finish() en lugar de crear un nuevo Intent,
                // así volvemos a la Activity anterior sin crear una instancia nueva
                finish();
            }
        });

        btn1Grabadora = findViewById(R.id.btn1Grabadora);
        btn1Grabadora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarGrabacion();
            }

        });

         btn2Grabadora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // CORRECCIÓN CLAVE: hay que detener la grabación ANTES de reproducir.
                // Si el MediaRecorder sigue activo y escribiendo en el archivo,
                // el MediaPlayer no puede leerlo y la app se cierra.
                detenerYReproducir();
            }
        });

    }
    private void iniciarGrabacion() {
        // ✅ Comprobar permiso antes de usar el micrófono
        if (checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO}, PERMISSION_REQUEST_AUDIO);
            return;
        }
        // Evitamos iniciar una segunda grabación si ya hay una en curso
        if (grabando) {
            Toast.makeText(this, "Ya hay una grabación en curso", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            recorder = new MediaRecorder();
            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            recorder.setOutputFile(output);
            recorder.prepare();
            recorder.start();

            grabando = true;
            Toast.makeText(this, "Grabando...", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al iniciar la grabación", Toast.LENGTH_SHORT).show();
        }
    }

    private void detenerYReproducir() {
        // Si no se ha iniciado ninguna grabación, avisamos y salimos
        if (!grabando) {
            Toast.makeText(this, "Primero debes grabar algo", Toast.LENGTH_SHORT).show();
            return;
        }

        // Paso 1: Detener y liberar el MediaRecorder
        // Esto es imprescindible para que el archivo quede cerrado y listo para leer
        recorder.stop();
        recorder.release();
        recorder = null;
        grabando = false;

        // Paso 2: Reproducir el archivo ya cerrado con MediaPlayer
        try {
            // Si había una reproducción anterior en curso, la liberamos antes
            if (player != null) {
                player.release();
                player = null;
            }

            player = new MediaPlayer();
            player.setDataSource(output);
            player.prepare();
            player.start();

            Toast.makeText(this, "Reproduciendo...", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al reproducir el audio", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Liberamos recursos al cerrar la Activity para evitar fugas de memoria
        if (recorder != null) {
            recorder.release();
            recorder = null;
        }
        if (player != null) {
            player.release();
            player = null;
        }
    }
}
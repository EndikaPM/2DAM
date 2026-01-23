package com.example.actividad_11_6;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer reproductor;
    private MediaRecorder grabador;
    private ImageButton mic;
    private ImageButton play;
    private ImageButton pause;
    Toast toast;
    String rutaArchivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mic = findViewById(R.id.mic);
        play = findViewById(R.id.play);
        pause= findViewById(R.id.pause);

        toast = new Toast(this);

        rutaArchivo = getExternalFilesDir(null).getAbsolutePath() + "/mi_grabacion.3gp";


        mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Verificar si el permiso de micrófono está concedido
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                    // Si no está concedido, pedirlo
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.RECORD_AUDIO}, 100);
                } else {
                    iniciarGrabacion();
                }
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (grabador != null) {
                    grabador.stop();
                    grabador.release();
                    grabador = null;

                    toast.setText("Deteniendo la Grabación ...");
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                java.io.File archivo = new java.io.File(rutaArchivo);
                if (!archivo.exists()) {
                    Toast.makeText(MainActivity.this, "No hay grabación para reproducir", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {

                    if(reproductor != null){
                        reproductor.release();
                    }

                    reproductor = new MediaPlayer();
                    reproductor.setDataSource(rutaArchivo);
                    reproductor.prepare();
                    reproductor.start();

                    toast.setText("Reproduciendo ...");
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.show();
                }catch (IOException e){
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void iniciarGrabacion(){
        try {
            grabador = new MediaRecorder();
            grabador.setAudioSource(MediaRecorder.AudioSource.MIC);
            grabador.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            grabador.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            grabador.setOutputFile(rutaArchivo);
            grabador.prepare();
            grabador.start();

            toast.setText("Iniciando la Grabación ...");
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
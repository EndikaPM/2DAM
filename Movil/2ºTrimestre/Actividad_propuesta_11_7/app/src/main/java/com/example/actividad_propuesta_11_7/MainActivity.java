package com.example.actividad_propuesta_11_7;

import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    private Button botonGrabar, botonParar, botonReproducir;
    private SurfaceView video;
    private SurfaceHolder holder;
    private MediaRecorder grabador;
    private MediaPlayer reproductor;
    private String ruta;
    private boolean isGrabando = false;

    private static final int PERMISSION_REQUEST_CODE = 1000;
    private String[] permisos = {
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.RECORD_AUDIO
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        verificarPermisos();

        video = findViewById(R.id.video);
        botonGrabar = findViewById(R.id.grabar);
        botonParar = findViewById(R.id.parar);
        botonReproducir = findViewById(R.id.repro);

        video.getHolder().addCallback(this);

        video.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        botonGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    grabador = new MediaRecorder();
                    grabador.setAudioSource(MediaRecorder.AudioSource.MIC);
                    grabador.setVideoSource(MediaRecorder.VideoSource.CAMERA);
                    grabador.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
                    grabador.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
                    grabador.setVideoEncoder(MediaRecorder.VideoEncoder.H264);

                    ruta = getExternalFilesDir(null).getAbsolutePath() + "/mi_video.mp4";
                    grabador.setOutputFile(ruta);
                    grabador.setPreviewDisplay(holder.getSurface());

                    grabador.prepare();
                    grabador.start();

                    isGrabando = true;
                    botonGrabar.setEnabled(false);
                    botonParar.setEnabled(true);
                    botonReproducir.setEnabled(false);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Error al iniciar grabación", Toast.LENGTH_SHORT).show();
                }
            }
        });

        botonParar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isGrabando) {
                    try {
                        grabador.stop();
                    } catch (RuntimeException stopException) {
                        // Error si la grabación es demasiado corta
                        stopException.printStackTrace();
                    } finally {
                        grabador.release();
                        grabador = null;
                        isGrabando = false;
                    }
                    botonGrabar.setEnabled(true);
                    botonParar.setEnabled(false);
                    botonReproducir.setEnabled(true);
                } else if (reproductor != null && reproductor.isPlaying()) {
                    reproductor.stop();
                    botonReproducir.setText("Reproducir");
                }
            }
        });

        botonReproducir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (reproductor == null) {
                        reproductor = new MediaPlayer();
                        reproductor.setDisplay(holder);
                    }

                    if (reproductor.isPlaying()) {
                        reproductor.pause();
                        botonReproducir.setText("Continuar");
                    } else {
                        reproductor.reset();
                        reproductor.setDataSource(ruta);
                        reproductor.setDisplay(holder);
                        reproductor.prepare();
                        reproductor.start();
                        botonReproducir.setText("Pausar");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "No hay video grabado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        this.holder = holder;
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        this.holder = holder;
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        if (grabador != null) {
            grabador.release();
            grabador = null;
        }
        if (reproductor != null) {
            reproductor.release();
            reproductor = null;
        }
    }

    // --- Gestión de Permisos ---
    private void verificarPermisos() {
        if (checkSelfPermission(android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                checkSelfPermission(android.Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(permisos, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (!(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                Toast.makeText(this, "Se necesitan permisos para funcionar", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }
}
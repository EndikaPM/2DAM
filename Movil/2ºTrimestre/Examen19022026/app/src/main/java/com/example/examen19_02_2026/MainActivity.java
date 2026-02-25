package com.example.examen19_02_2026;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometro;
    private int contador = 0;
    private double x = 0, y = 0, z = 0, a = 0, amax = 0;
    private final double SHAKE_THRESHOLD = 12.0;
    private TextView tvax, tvay, tvaz, tva, tvaMax;
    private ImageView imagen;
    private FrameLayout frameLayout;

    private float imagenCenterX = 0;
    private float imagenCenterY = 0;
    private float imagenX = 0;
    private float imagenY = 0;

    private final Handler handler = new Handler(Looper.getMainLooper());
    private Runnable runnable;
    private Vibrator vibrator;
    private MediaPlayer mediaPlayer;
    private boolean sacudidaDetectada = false;
    private static final long SHAKE_COOLDOWN = 500;
    private long ultimaSacudida = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvax = findViewById(R.id.textViewAX);
        tvay = findViewById(R.id.textViewAY);
        tvaz = findViewById(R.id.textViewAZ);
        tva = findViewById(R.id.textViewA);
        tvaMax = findViewById(R.id.textViewAmax);
        imagen = findViewById(R.id.imagen);
        frameLayout = findViewById(R.id.main);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


        frameLayout.post(() -> {

            imagenCenterX = frameLayout.getWidth() / 2f;
            imagenCenterY = frameLayout.getHeight() / 2f;

            imagenX = imagenCenterX;
            imagenY = imagenCenterY;
            actualizarPosicionImagen();

        });

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

        if (a > amax) {
            amax = a;
        }

        float sensibilidad = 150f;

        imagenX = imagenCenterX - (float)(x * sensibilidad);
        imagenY = imagenCenterY - (float)(y * sensibilidad);

        actualizarPosicionImagen();

        if (a > SHAKE_THRESHOLD) {
            long tiempoActual = System.currentTimeMillis();
            if (tiempoActual - ultimaSacudida > SHAKE_COOLDOWN) {
                ultimaSacudida = tiempoActual;
                ejecutarEfectoSacudida();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {    }

    private void actualizarPosicionImagen() {
        int imagenWidth = imagen.getWidth();
        int imagenHeight = imagen.getHeight();

        if (imagenWidth == 0) imagenWidth = 100;
        if (imagenHeight == 0) imagenHeight = 100;

        float posX = imagenX - (imagenWidth / 2f);
        float posY = imagenY - (imagenHeight / 2f);

        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) imagen.getLayoutParams();
        params.leftMargin = (int) posX;
        params.topMargin = (int) posY;
        params.gravity = 0;
        imagen.setLayoutParams(params);

        verificarSalidaPantalla(posX, posY, imagenWidth, imagenHeight);
    }

    private void verificarSalidaPantalla(float posX, float posY, int width, int height) {

        int pantallaDerecha = frameLayout.getWidth();
        int pantallaAbajo = frameLayout.getHeight();


        boolean estaFueraDeIzquierda = posX + width < 0;
        boolean estaFueraDeDerecha = posX > pantallaDerecha;
        boolean estaFueraDeArriba = posY + height < 0;
        boolean estaFueraDeAbajo = posY > pantallaAbajo;

        if (estaFueraDeIzquierda || estaFueraDeDerecha || estaFueraDeArriba || estaFueraDeAbajo) {
            reproducirSonido();
        }
    }

    private void reproducirSonido() {

        try {
            if (mediaPlayer != null) {
                mediaPlayer.release();
            }
            //mediaPlayer =
            if (mediaPlayer != null) {
                mediaPlayer.setVolume(1.0f, 1.0f);
                mediaPlayer.start();
            }
        } catch (Exception e) {

        }
    }

    private void ejecutarEfectoSacudida() {
        //Mira como generar sacudida
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setDuration(500);

        RotateAnimation rotateAnim = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animationSet.addAnimation(rotateAnim);

        ScaleAnimation scaleAnim = new ScaleAnimation(1.0f, 1.5f, 1.0f, 1.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnim.setRepeatCount(1);
        scaleAnim.setRepeatMode(Animation.REVERSE);
        animationSet.addAnimation(scaleAnim);

        imagen.startAnimation(animationSet);

        handler.postDelayed(() -> {
            imagenX = imagenCenterX;imagenY = imagenCenterY;actualizarPosicionImagen();
        }, 500);
    }


    private void generarVibracion() {
        if (vibrator != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                VibrationEffect effect = VibrationEffect.createOneShot(300, VibrationEffect.DEFAULT_AMPLITUDE);
                vibrator.vibrate(effect);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (accelerometro != null) {
            sensorManager.registerListener(this, accelerometro, SensorManager.SENSOR_DELAY_UI);
        }
        handler.post(runnable);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
        handler.removeCallbacks(runnable);

        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        handler.removeCallbacks(runnable);
    }
}
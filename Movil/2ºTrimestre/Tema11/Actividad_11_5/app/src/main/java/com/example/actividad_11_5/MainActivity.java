package com.example.actividad_11_5;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button boton1;
    Button boton2;
    SeekBar seekbar1;
    SeekBar seekbar2;
    SoundPool sndPool;
    int sonido1, sonido2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton1 = findViewById(R.id.boton1);
        boton2 = findViewById(R.id.boton2);
        seekbar1 = findViewById(R.id.seekbar1);
        seekbar2 = findViewById(R.id.seekbar2);

        boton1.setText("Cancion1");
        boton2.setText("Cancion2");

        sndPool = new SoundPool(2, AudioManager.STREAM_MUSIC,100);
        sonido1 = sndPool.load(this, R.raw.relaxing_guitar,1);
        sonido2 = sndPool.load(this, R.raw.cinematic_designed, 1);

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sndPool.play(sonido1,((float) seekbar1.getProgress() /200),
                        ((float) seekbar1.getProgress() /200), 1,0, ((float) seekbar2.getProgress() /200));
            }
        });

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sndPool.play(sonido2,((float) seekbar1.getProgress() / 200),
                        ((float) seekbar1.getProgress() /200), 1,0, ((float) seekbar2.getProgress() /200));
            }
        });

    }
}
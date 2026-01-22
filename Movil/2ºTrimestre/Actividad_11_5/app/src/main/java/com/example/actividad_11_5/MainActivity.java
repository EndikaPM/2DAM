package com.example.actividad_11_5;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton1 = findViewById(R.id.boton1);
        boton2 = findViewById(R.id.boton2);
        seekbar1 = findViewById(R.id.seekbar1);
        seekbar2 = findViewById(R.id.seekbar2);

        sndPool = new SoundPool(1, AudioManager.STREAM_MUSIC,100);
        sndPool.load(this, R.raw.relaxing_guitar,1);

        sndPool.play(R.raw.relaxing_guitar,seekbar1.getProgress(),
                seekbar1.getProgress(), 1,0, seekbar2.getProgress());
    }
}
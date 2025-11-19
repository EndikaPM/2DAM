package com.example.aplicicacion7_3_2;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
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

        SeekBar seekBar = findViewById(R.id.seekbar);

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.custom_toast_id));

        ImageView image = (ImageView) layout.findViewById(R.id.imagen_toast);
        image.setImageResource(R.drawable.outline_shelf_position_24);

        Toast toast = new Toast(this);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


                if (progress < 40) {
                    TextView text = (TextView) layout.findViewById(R.id.texto_toast);
                    text.setText("Arriba");
                    toast.setGravity(Gravity.TOP,0, 0);

                } else if (progress > 40 && progress < 80) {
                    TextView text = (TextView) layout.findViewById(R.id.texto_toast);
                    text.setText("Centrado");
                    toast.setGravity(Gravity.CENTER,0, 0);

                } else if (progress > 80) {
                    TextView text = (TextView) layout.findViewById(R.id.texto_toast);
                    text.setText("Abajo");
                    toast.setGravity(Gravity.BOTTOM,0, 0);
                }
                toast.show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }
}
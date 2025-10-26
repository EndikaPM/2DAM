package com.example.aswitch;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Switch pulsado = (Switch) findViewById(R.id.miswich);
        pulsado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean pulsado) {
                if (pulsado){
                    Toast.makeText(MainActivity.this,"Boton Pulsado",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"Boton NO Pulsado",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
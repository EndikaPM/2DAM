package com.example.radiobutton;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

        /*RadioGroup miGrupo = (RadioGroup) findViewById(R.id.radio);
        miGrupo.clearCheck();
        miGrupo.check(R.id.radioButon1);
        int idMarco = miGrupo.getCheckedRadioButtonId();*/

        RadioGroup miGrupo2 = (RadioGroup) findViewById(R.id.radio);
        miGrupo2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull RadioGroup group, int checkedId) {
                RadioButton selecion = findViewById(checkedId);
                String texto = selecion.getText().toString();
                Toast.makeText(MainActivity.this, "Se cliclea el " + texto,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
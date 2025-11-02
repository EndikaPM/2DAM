package com.example.checkbox;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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

        CheckBox check = (CheckBox) findViewById(R.id.checkbox);

        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean pulsado) {
                if (pulsado){
                    Toast.makeText(MainActivity.this,"CheckBox_Pulsado",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"CheckBox_No_Pulsado",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
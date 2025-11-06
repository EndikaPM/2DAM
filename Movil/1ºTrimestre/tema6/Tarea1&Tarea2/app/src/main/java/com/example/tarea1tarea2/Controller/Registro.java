package com.example.tarea1tarea2.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tarea1tarea2.R;

public class Registro extends AppCompatActivity {

    private EditText user;
    private EditText passwd;
    private EditText passwdCon;
    private Button guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        user = findViewById(R.id.user);
        passwd = findViewById(R.id.passwd);
        guardar = findViewById(R.id.guardar);
        passwdCon = findViewById(R.id.passwdConfirm);


            guardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (passwd.equals(passwdCon)){
                        finish();
                    }else{
                        Toast.makeText(Registro.this, "La contraseña no coinciden", Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }

    @Override
    public void finish(){
        Intent enviarDatos = new Intent();
        enviarDatos.putExtra("usuario", user.getText().toString());
        enviarDatos.putExtra("passwd", passwd.getText().toString());
        setResult(RESULT_OK, enviarDatos);

        super.finish();
    }

}
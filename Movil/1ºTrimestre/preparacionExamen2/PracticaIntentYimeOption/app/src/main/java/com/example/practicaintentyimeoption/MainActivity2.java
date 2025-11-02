package com.example.practicaintentyimeoption;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {
    private String recibirUser, recibirMenssage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recibirUser = getIntent().getStringExtra("nameUser");
        recibirMenssage = getIntent().getStringExtra("nameMenssage");

        Intent datos = new Intent();
        datos.putExtra("DatosOk","Para: "+ recibirUser+"\nMensaje: "+recibirMenssage);

        Toast.makeText(this,"Para: "+ recibirUser+"\nMensaje: "+recibirMenssage,Toast.LENGTH_LONG).show();

        setResult(RESULT_OK,datos);

        finish();
    }

}
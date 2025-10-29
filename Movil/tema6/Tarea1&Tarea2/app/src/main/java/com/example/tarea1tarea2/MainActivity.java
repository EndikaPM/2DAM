package com.example.tarea1tarea2;

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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Usuario [] usuario = {new Usuario("Endika", "Endika"),new Usuario("Eva", "Eva"),
                new Usuario("Usuario", "Usuario"),new Usuario("Root", "root")};

        Button ok = (Button) findViewById(R.id.ok);
        Button singIn = (Button) findViewById(R.id.sign_In);
        EditText textUser = (EditText) findViewById(R.id.textUsuario);
        EditText textPassword = (EditText) findViewById(R.id.textPassword);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tam = usuario.length;
                String user = textUser.getText().toString();
                String passwo = textPassword.getText().toString();
                boolean registrado = false;

                for (int i = 0; i < tam && !registrado; i++) {
                    if (usuario[i].getUser().equals(user) && usuario[i].getPassword().equals(passwo)){
                        registrado = true;
                        Intent pasarActivity2 = new Intent(MainActivity.this, Activity2.class);
                        startActivity(pasarActivity2);
                    }
                }
                if (!registrado) {
                    Toast.makeText(MainActivity.this, "Usuario no registrado\n registrate para entrar", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
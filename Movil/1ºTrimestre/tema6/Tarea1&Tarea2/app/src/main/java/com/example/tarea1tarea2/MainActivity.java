package com.example.tarea1tarea2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tarea1tarea2.Controller.Activity2;
import com.example.tarea1tarea2.Controller.Registro;
import com.example.tarea1tarea2.Model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final static int REQUEST_CODE = 10;
    private List<Usuario> usuario = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario.add(new Usuario("Endika", "Endika"));
        usuario.add(new Usuario("Eva", "Eva"));
        usuario.add(new Usuario("Usuario", "Usuario"));
        usuario.add(new Usuario("Root", "root"));
        usuario.add(new Usuario("", ""));

        Button ok = (Button) findViewById(R.id.ok);
        Button singIn = (Button) findViewById(R.id.sign_In);
        EditText textUser = (EditText) findViewById(R.id.textUsuario);
        EditText textPassword = (EditText) findViewById(R.id.textPassword);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tam = usuario.size();
                String user = textUser.getText().toString();
                String passwo = textPassword.getText().toString();
                boolean registrado = false;

                for (Usuario u : usuario) {
                    if (u.comprovarAcces(user,passwo)){
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

        singIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registro = new Intent(MainActivity.this, Registro.class);
                startActivityForResult(registro,REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent recibir){
        super.onActivityResult(requestCode, resultCode, recibir);
        if (RESULT_OK == resultCode && REQUEST_CODE == requestCode){
            Bundle b = recibir.getExtras();
            if (b != null) {
                usuario.add(new Usuario(b.getString("usuario"), b.getString("passwd")));
            }
        }
    }

}
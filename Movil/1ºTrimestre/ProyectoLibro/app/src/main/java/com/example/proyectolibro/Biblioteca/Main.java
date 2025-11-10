package com.example.proyectolibro.Biblioteca;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectolibro.Controller.Activity2;
import com.example.proyectolibro.Controller.Registro;
import com.example.proyectolibro.Model.Usuario;
import com.example.proyectolibro.R;

import java.util.ArrayList;
import java.util.List;

public class Main extends AppCompatActivity {
    private final static int REQUEST_CODE = 10;
    private ImageView ojo;
    private boolean ojoPulsado;
    private List<Usuario> usuario = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario.add(new Usuario("Endika", "Endika"));
        usuario.add(new Usuario("Eva", "Eva"));
        usuario.add(new Usuario("Usuario", "Usuario"));
        usuario.add(new Usuario("Root", "root"));


        EditText textUser = (EditText) findViewById(R.id.textUsuario);
        EditText textPassword = (EditText) findViewById(R.id.textPassword);

        ojo = findViewById(R.id.ojo);
        ojo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ojoPulsado){
                    ojoPulsado = false;
                    textPassword.setInputType(InputType.TYPE_CLASS_TEXT);
                }else{
                    ojoPulsado = true;
                    textPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

        Button ok = (Button) findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tam = usuario.size();
                String user = textUser.getText().toString();
                String passwo = textPassword.getText().toString();
                boolean registrado = false;
                if (user.isEmpty() || passwo.isEmpty()) {
                    Toast.makeText(Main.this, "Rellena todos los campos",Toast.LENGTH_LONG).show();
                }

                for (Usuario u : usuario) {
                    if (u.comprovarAcces(user,passwo)){
                        registrado = true;
                        Intent pasarActivity2 = new Intent(Main.this, Activity2.class);
                        startActivity(pasarActivity2);
                    }
                }
                if (!registrado && !(user.isEmpty() || passwo.isEmpty())) {
                    Toast.makeText(Main.this, "Usuario no registrado\n registrate para entrar", Toast.LENGTH_LONG).show();
                }
            }
        });

        Button singIn = (Button) findViewById(R.id.sign_In);
        singIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registro = new Intent(Main.this, Registro.class);
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
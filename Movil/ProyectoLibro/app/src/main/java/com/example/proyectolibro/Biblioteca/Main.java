package com.example.proyectolibro.Biblioteca;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main extends AppCompatActivity {
    private final static int REQUEST_CODE = 10;
    private ImageView ojo;
    private boolean ojoPulsado;
    //private List<Usuario> usuario = new ArrayList<>(); TODO tengo que quitarla
    private MediaPlayer mediaPlayer;
    private final char alfabeto[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };
    private final char alfabetoEncriptado[] = {'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P', 'A', 'S', 'D', 'F', 'G',
            'H', 'J', 'K', 'L', 'Z', 'X', 'C', 'V', 'B', 'N', 'M',
            'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd', 'f', 'g',
            'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm',
            '9', '8', '7', '6', '5', '4', '3', '2', '1', '0'
    };
    String endika;
    String root;
    private Map<String, String> usuarios;
    public SharedPreferences usuariosDb;
    EditText textUser;
    EditText textPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this, R.raw.background_music);

        endika = codificar(alfabeto, alfabetoEncriptado, "Endika");
        root = codificar(alfabeto, alfabetoEncriptado, "root");
        usuarios = new HashMap<>();

        /*usuario.add(new Usuario("Endika", "Endika"));
        usuario.add(new Usuario("Eva", "Eva"));
        usuario.add(new Usuario("Usuario", "Usuario"));
        usuario.add(new Usuario("Root", "root"));
        TODO nuevos insertasos en sharePerferent Maria , Maria*/
        usuariosDb = getSharedPreferences("Usuarios", MODE_PRIVATE);
        cargarUsuarios();


        textUser = (EditText) findViewById(R.id.textUsuario);
        textPassword = (EditText) findViewById(R.id.textPassword);

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
                int tam = usuarios.size();
                String user = textUser.getText().toString();
                String passwo = textPassword.getText().toString();

                boolean registrado = false;

                if (user.isEmpty() || passwo.isEmpty()) {
                    Toast.makeText(Main.this, "Rellena todos los campos ",Toast.LENGTH_LONG).show();
                }

                String userEncriptado = codificar(alfabeto, alfabetoEncriptado, user);
                String passEncriptada = codificar(alfabeto, alfabetoEncriptado, passwo);

                for (Map.Entry<String,String> u : usuarios.entrySet()) {
                    if (u.getKey().equals(userEncriptado) && u.getValue().equals(passEncriptada)) {
                        registrado = true;
                        Intent pasarActivity2 = new Intent(Main.this, Activity2.class);
                        startActivity(pasarActivity2);
                    }
                }
                if (!registrado) {
                    Toast.makeText(Main.this, "Usuario no registrado\n registrate para entrar ", Toast.LENGTH_LONG).show();
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

        mediaPlayer.setLooping(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
           // mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent recibir){
        super.onActivityResult(requestCode, resultCode, recibir);
        if (RESULT_OK == resultCode && REQUEST_CODE == requestCode){
            Bundle b = recibir.getExtras();
            if (b != null) {
                String usuario = b.getString("usuario");
                String passwd = b.getString("passwd");

                String usuarioEncriptado = codificar(alfabeto, alfabetoEncriptado, usuario);
                String passwdEncriptada = codificar(alfabeto, alfabetoEncriptado, passwd);

                SharedPreferences.Editor editor = usuariosDb.edit();
                editor.putString(usuarioEncriptado, passwdEncriptada);// guardamos el usuario y la contraseña encriptada
                editor.apply();

                cargarUsuarios();

                Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private static String codificar(char[] alfabeto, char[] alfabetoEncriptado, String palabra) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < palabra.length(); i++) {
            for (int j = 0; j < alfabeto.length; j++) {
                if (palabra.charAt(i) == alfabeto[j]) {
                    result.append(alfabetoEncriptado[j]);
                    break;
                }
            }
        }
        return result.toString();
    }

    private static String decodificar(char[] alfabeto, char[] alfabetoEncriptado, String palabra) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < palabra.length(); i++) {
            for (int j = 0; j < alfabetoEncriptado.length; j++) {
                if (palabra.charAt(i) == alfabetoEncriptado[j]) {
                    result.append(alfabeto[j]);
                    break;
                }
            }
        }
        return result.toString();
    }

    private void cargarUsuarios() {
        //usuarios.clear();
        usuarios.put(endika, endika);
        usuarios.put(root, root);

        Map<String, ?> prefs = usuariosDb.getAll();
        for (Map.Entry<String, ?> entry : prefs.entrySet()) {
            usuarios.put(entry.getKey(), entry.getValue().toString());
        }
    }

}
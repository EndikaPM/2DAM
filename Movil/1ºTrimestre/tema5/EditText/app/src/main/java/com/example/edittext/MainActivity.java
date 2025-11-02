package com.example.edittext;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
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
        EditText textoLeido = (EditText) findViewById(R.id.mitexto);
        String cadenaTexto = textoLeido.getText().toString();
        textoLeido.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                String texto_actual = s.toString();
                if (texto_actual.contains("<")|| texto_actual.contains(">")){
                    Toast.makeText(MainActivity.this, "Caracter intrducido no es valida", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Toast.makeText(MainActivity.this, "Tencudado con el caracter >", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });

        textoLeido.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
               if (actionId == EditorInfo.IME_ACTION_SEARCH){
                   // Cuando presionas el botón de búsqueda del teclado
                   String texto = v.getText().toString();
                   Toast.makeText(MainActivity.this, "Buscando: " + texto, Toast.LENGTH_SHORT).show();
                    return true;
                }
               return false;
            }
        });
    }
}
package com.example.actividad10_1;

import static android.content.Context.MODE_PRIVATE; // Constante para abrir SharedPreferences en modo privado

import android.content.SharedPreferences; // API para guardar pares clave-valor persistentes
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    // Referencias a widgets de la interfaz (se enlazarán con findViewById en onCreate)
    Button boton1, boton2;
    EditText textoEditar1, textoEditar2;
    TextView texto1, texto2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Asocia el layout XML (res/layout/activity_main.xml) con esta Activity
        setContentView(R.layout.activity_main);

        // Vinculación de variables Java con vistas del layout mediante sus IDs
        boton1 = findViewById(R.id.boton1); // botón para guardar los textos
        boton2 = findViewById(R.id.boton2); // botón para recuperar los textos
        textoEditar1 = findViewById(R.id.EditarTexto1); // primer EditText editable
        textoEditar2 = findViewById(R.id.EditarTexto2); // segundo EditText editable
        texto1 = findViewById(R.id.text1); // TextView para mostrar el texto recuperado 1
        texto2 = findViewById(R.id.text2); // TextView para mostrar el texto recuperado 2

        // Opcional: establecer el texto visible de los botones (si no está en el XML)
        boton1.setText("Guardar");
        boton2.setText("Recuperar");

        // --- SharedPreferences ---
        // Crea o abre un archivo de preferencias llamado "EditText1" en modo privado.
        // MODE_PRIVATE indica que sólo esta aplicación puede leer/escribir estas preferencias.
        SharedPreferences prefText1 = getSharedPreferences("EditText1", MODE_PRIVATE);

        // Listener para el botón "Guardar". Se ejecuta cuando el usuario pulsa el botón.
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtenemos un editor para modificar las preferencias.
                SharedPreferences.Editor editor1 = prefText1.edit();

                // Leemos el contenido actual de los EditText (lo que ha escrito el usuario)
                String texto1Guardar = textoEditar1.getText().toString();
                String texto2Guardar = textoEditar2.getText().toString();

                // Guardamos dos pares clave-valor en las preferencias.
                // Las claves son "texto1" y "texto2"; los valores son las cadenas leídas.
                editor1.putString("texto1", texto1Guardar);
                editor1.putString("texto2", texto2Guardar);

                // Persistimos los cambios.
                // - commit(): escribe de forma síncrona y devuelve true/false según éxito.
                // - apply(): escribe de forma asíncrona (no bloquea el hilo UI) y es el recomendado.
                // Aquí se usa commit() (como en el código original), pero se recomienda apply().
                editor1.commit(); // podría cambiarse por editor1.apply(); para no bloquear la UI

                // Limpiamos los EditText como feedback visual de que se ha guardado
                textoEditar1.setText("");
                textoEditar2.setText("");
            }
        });

        // Listener para el botón "Recuperar". Lee las preferencias y muestra los valores guardados.
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Recupera los valores guardados. Si no existe la clave, devuelve el valor por defecto.
                String texto1Recuperado = prefText1.getString("texto1", "No hay texto guardado");
                String texto2Recuperado = prefText1.getString("texto2", "No hay texto guardado");

                // Muestra los textos recuperados en los TextView correspondientes
                texto1.setText(texto1Recuperado);
                texto2.setText(texto2Recuperado);
            }
        });
    }
}
package com.example.preparacion_examen_11;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

// Activity principal de la aplicación
// Aquí se manejan acciones para abrir la cámara, abrir la galería y mostrar la imagen seleccionada
public class MainActivity extends AppCompatActivity implements android.view.SurfaceHolder.Callback {
    // Lista de permisos que la app solicitará al usuario
    // Se inicializa en onCreate según la versión de Android para soportar READ_MEDIA_IMAGES en Android 13+
    private String[] permisos; // se asigna en onCreate

    // Códigos de petición usados con requestPermissions
     private static final int PERMISSION_REQUEST_CODE = 1000; // petición genérica (no usada ahora)
    private static final int PERMISSION_REQUEST_CAMERA = 1001; // petición para permiso de cámara
    private static final int PERMISSION_REQUEST_STORAGE = 1002; // petición para permiso de almacenamiento
     private static final int REQUEST_IMAGE_CAPTURE = 1; // petición para capturar imagen con la cámara
     private static final int REQUEST_GALLERY = 2; // petición para seleccionar imagen desde la galería

    // Vistas definidas en el layout (activity_main.xml)
    private TextView texto; // ejemplo de TextView (puede mostrar información o estado)
    private ImageView imagen; // ImageView donde mostraremos la imagen tomada/seleccionada
    private Button btn1, btn2, btn3; // botones para cámara, galería y otra actividad

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        verificarPermisos();
        // Inicializamos el array de permisos según la versión de Android
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            permisos = new String[]{
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_MEDIA_IMAGES
            };
        } else {
            permisos = new String[]{
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE
            };
        }

        // Inicializamos las referencias a las vistas (vinculación con XML)
        texto = findViewById(R.id.texto);
        imagen = findViewById(R.id.imagen);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);


        // No solicitamos permisos automáticamente en onCreate; los pedimos bajo demanda
        // (cuando el usuario pulse Cámara o Galería). Esto evita que la app se cierre
        // si el usuario concede permisos parciales como "Mientras use la app"

        // Listener para el botón 1: abrir la cámara
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirCamara(); // abre la app de cámara del sistema
            }
        });

        // Listener para el botón 2: abrir la galería para elegir una imagen
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirGaleria();
            }
        });

        // Listener para el botón 3: lanzar otra actividad (MainActivity2)
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

    }

    // Método para verificar permisos necesarios en tiempo de ejecución
    // Nota: antes había una comprobación equivocada (RECORD_AUDIO). La corregimos a READ_EXTERNAL_STORAGE
    private void verificarPermisos() {
        // Método dejado para compatibilidad si se desea usar comprobación previa.
        // Actualmente solicitamos permisos bajo demanda en los métodos abrirCamara/abrirGaleria.
    }

    // Callbacks del SurfaceHolder (no se usan activamente aquí, pero la Activity implementa la interfaz)
    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    // Método que crea un Intent para abrir la aplicación de cámara del dispositivo
    // Se usa ACTION_IMAGE_CAPTURE y startActivityForResult para recibir la miniatura de la imagen
    private void abrirCamara() {
        // Comprobar permiso de cámara en tiempo de ejecución
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // Solicitamos sólo el permiso de cámara; al concederlo volveremos a intentar abrir la cámara
            requestPermissions(new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CAMERA);
            return;
        }
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Lanza la cámara y espera el resultado en onActivityResult
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } else {
            // Si no existe app de cámara, avisamos al usuario
            Toast.makeText(this, "No hay aplicación de cámara disponible", Toast.LENGTH_SHORT).show();
        }
    }

    // Método que abre la galería para seleccionar una imagen
    private void abrirGaleria() {
        // Comprobar permiso de lectura de almacenamiento en tiempo de ejecución
        String storagePerm = (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) ?
                Manifest.permission.READ_MEDIA_IMAGES : Manifest.permission.READ_EXTERNAL_STORAGE;
        if (checkSelfPermission(storagePerm) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{storagePerm}, PERMISSION_REQUEST_STORAGE);
            return;
        }
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Lanza la galería; el resultado se recibirá en onActivityResult
        startActivityForResult(intent, REQUEST_GALLERY);
    }

    // Aquí manejamos los resultados de la cámara y de la galería
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Manejo del resultado proveniente de la cámara
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // La cámara devuelve una miniatura de la imagen en el extra "data"
            Bundle extras = data.getExtras();
            if (extras != null) {
                // Convertimos el extra a Bitmap y lo mostramos en el ImageView
                imagen.setImageBitmap((android.graphics.Bitmap) extras.get("data"));
            }
        // Manejo del resultado proveniente de la galería
        } else if (requestCode == REQUEST_GALLERY && resultCode == RESULT_OK && data != null) {
            // data.getData() contiene la URI de la imagen seleccionada
            // setImageURI gestiona la carga y muestra la imagen en el ImageView
            imagen.setImageURI(data.getData());
        }
    }

    // Resultado de la petición de permisos al usuario
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST_CODE) {
            // Verificamos que todos los permisos solicitados hayan sido concedidos
            // Si no hay resultados, consideramos que no se concedieron
            if (grantResults.length == 0) {
                Toast.makeText(this, "No se concedieron permisos. Algunas funciones no funcionarán.", Toast.LENGTH_LONG).show();
                // No forzamos cierre; dejamos que el usuario interactúe y pueda concederlos desde ajustes
                return;
            }

            // Comprueba si al menos uno de los permisos solicitados fue concedido
            boolean alMenosUno = false;
            for (int grant : grantResults) {
                if (grant == PackageManager.PERMISSION_GRANTED) {
                    alMenosUno = true;
                    break;
                }
            }

            if (!alMenosUno) {
                // Ningún permiso concedido: informamos y no continuamos con funciones que requieren permisos
                Toast.makeText(this, "Se necesitan permisos para funcionar. Concede permisos desde Ajustes.", Toast.LENGTH_LONG).show();
                // Opcional: podríamos cerrar, pero mejor dejar al usuario abrir la app y conceder desde Ajustes
                // finish();
            } else {
                // Permisos parciales concedidos: informamos que algunas funciones pueden estar limitadas
                Toast.makeText(this, "Permisos concedidos parcialmente: algunas funciones pueden estar limitadas.", Toast.LENGTH_LONG).show();
            }
        }
        else if (requestCode == PERMISSION_REQUEST_CAMERA) {
            // Si el permiso de cámara fue concedido, intentamos abrir la cámara de nuevo
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                abrirCamara();
            } else {
                Toast.makeText(this, "Permiso de cámara denegado. No se puede abrir la cámara.", Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == PERMISSION_REQUEST_STORAGE) {
            // Si el permiso de almacenamiento fue concedido, intentamos abrir la galería de nuevo
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                abrirGaleria();
            } else {
                Toast.makeText(this, "Permiso de almacenamiento denegado. No se puede abrir la galería.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
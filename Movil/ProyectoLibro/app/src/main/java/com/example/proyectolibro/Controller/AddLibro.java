package com.example.proyectolibro.Controller;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


import androidx.appcompat.app.AppCompatActivity;
import com.example.proyectolibro.R;
import java.util.Calendar;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import org.json.JSONArray;
import org.json.JSONObject;
import android.widget.ProgressBar;
import android.widget.Toast;

public class AddLibro extends AppCompatActivity {
    private ImageView img;
    private EditText title;
    private EditText des;
    private EditText dataPubli;
    private Button save;
    private Button back;
    private Button btnEscanear;
    private ProgressBar progressBar;
    private static final int REQUEST_GALLERY = 1;
    private static final int REQUEST_BARCODE = 2;
    private static final int REQUEST_PERMISSION = 3;
    private Bitmap imagenSeleccionada;
    private RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_libro);

        // Inicializar RequestQueue para llamadas API
        requestQueue = Volley.newRequestQueue(this);

        imagenSeleccionada = null;
        img = findViewById(R.id.img);
        title = findViewById(R.id.title);
        des = findViewById(R.id.textDescrip);
        dataPubli = findViewById(R.id.datePublis);
        btnEscanear = findViewById(R.id.btnEscanearISBN);
        progressBar = findViewById(R.id.progressBar);

        btnEscanear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solicitarPermisoCamara();
            }
        });


        // Click listener en el ImageView
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirGaleria();
            }
        });

        // Click listener en el contenedor de imagen para que sea más fácil seleccionar
        View imageContainer = findViewById(R.id.imageContainer);
        if (imageContainer != null) {
            imageContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    abrirGaleria();
                }
            });
        }

        dataPubli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePicker = new DatePickerDialog(
                        AddLibro.this,
                        (view, selectedYear, selectedMonth, selectedDay) -> {
                            String dateWithFormat = String.format("%02d/%02d/%04d", selectedDay, selectedMonth + 1, selectedYear);

                            dataPubli.setText(dateWithFormat);
                        },
                        year, month, day
                );

                datePicker.show();
            }
        });
        save = findViewById(R.id.save);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(AddLibro.this, Activity2.class);
                startActivity(back);
            }
        });

        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void abrirGaleria() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_GALLERY && resultCode == RESULT_OK && data != null) {
            Uri imagenUri = data.getData();
            try {
                Bitmap bitmapOriginal = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imagenUri);
                // Redimensionar la imagen para evitar problemas de memoria
                imagenSeleccionada = redimensionarBitmap(bitmapOriginal, 500, 500);
                img.setImageBitmap(imagenSeleccionada);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // Manejar los codigos de barras escaneados
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Escaneo cancelado", Toast.LENGTH_SHORT).show();
            } else {
                String isbn = result.getContents();
                Toast.makeText(this, "ISBN escaneado: " + isbn, Toast.LENGTH_SHORT).show();
                buscarLibroPorISBN(isbn);
            }
        }
    }

    /**
     * Redimensiona un Bitmap manteniendo la proporción
     */
    private Bitmap redimensionarBitmap(Bitmap original, int maxAncho, int maxAlto) {
        int ancho = original.getWidth();
        int alto = original.getHeight();

        float ratio = Math.min((float) maxAncho / ancho, (float) maxAlto / alto);

        int nuevoAncho = Math.round(ancho * ratio);
        int nuevoAlto = Math.round(alto * ratio);

        return Bitmap.createScaledBitmap(original, nuevoAncho, nuevoAlto, true);
    }

    @Override
    public void finish(){
        Intent addOrUpdateDatos = new Intent();
        // Si el usuario seleccionó una imagen, la enviamos como Bitmap redimensionado
        if (imagenSeleccionada != null) {
            // Comprimir aún más si es necesario para el Intent
            Bitmap bitmapParaEnviar = redimensionarBitmap(imagenSeleccionada, 300, 300);
            addOrUpdateDatos.putExtra("imagen", bitmapParaEnviar);
        } else {
            // Si no seleccionó, enviamos -1 para usar imagen por defecto
            addOrUpdateDatos.putExtra("imagen", -1);
        }
        addOrUpdateDatos.putExtra("Title", title.getText().toString());
        addOrUpdateDatos.putExtra("Descri", des.getText().toString());
        addOrUpdateDatos.putExtra("datePublis", dataPubli.getText().toString());
        setResult(RESULT_OK, addOrUpdateDatos);

        super.finish();
    }
    private void iniciarEscanerCodigo() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.EAN_13); // ISBN-13
        integrator.setPrompt("Escanea el código de barras del libro");
        integrator.setCameraId(0);  // Cámara trasera
        integrator.setBeepEnabled(true);
        integrator.setBarcodeImageEnabled(true);
        integrator.setOrientationLocked(false);
        integrator.initiateScan();
    }

    private void buscarLibroPorISBN(String isbn) {
        progressBar.setVisibility(View.VISIBLE);

        // TODO: Para evitar el error 429 (Too Many Requests), necesitas una API Key.
        // 1. Ve a https://console.cloud.google.com/
        // 2. Crea un proyecto y habilita "Books API"
        // 3. Crea una credencial (API Key) y pégala abajo.
        String apiKey = "AIzaSyBUv7oTtaFW6HE6lEsn3DXa1Lfw4_zMlQ4"; // <--- PEGA TU API KEY AQUI

        String url = "https://www.googleapis.com/books/v1/volumes?q=isbn:" + isbn;
        if (!apiKey.isEmpty()) {
            url += "&key=" + apiKey;
        }

        JsonObjectRequest jsonRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray items = response.getJSONArray("items");
                            if (items.length() > 0) {
                                JSONObject book = items.getJSONObject(0).getJSONObject("volumeInfo");

                                // Extraer información del libro
                                String titulo = book.optString("title", "Sin título");

                                // Lógica mejorada para la descripción
                                String descripcion = book.optString("description", "");
                                if (descripcion.isEmpty()) {
                                    // Si no hay descripción en volumeInfo, intentamos buscar un fragmento de texto
                                    if (items.getJSONObject(0).has("searchInfo")) {
                                        descripcion = items.getJSONObject(0).getJSONObject("searchInfo").optString("textSnippet", "");
                                    }
                                }
                                if (descripcion.isEmpty()) {
                                    descripcion = "Descripción no disponible para este libro.";
                                }

                                String fechaPublicacion = book.optString("publishedDate", "Fecha desconocida");

                                // Obtener URL de la imagen con gestión de errores mejorada
                                String imageUrl = "";
                                if (book.has("imageLinks")) {
                                    JSONObject imageLinks = book.getJSONObject("imageLinks");
                                    // Intentamos obtener la imagen más grande disponible
                                    if (imageLinks.has("thumbnail")) {
                                        imageUrl = imageLinks.getString("thumbnail");
                                    } else if (imageLinks.has("smallThumbnail")) {
                                        imageUrl = imageLinks.getString("smallThumbnail");
                                    }

                                    if (!imageUrl.isEmpty()) {
                                        // Reemplazar http por https para evitar bloqueos de seguridad
                                        imageUrl = imageUrl.replace("http://", "https://");
                                        cargarImagenDesdeURL(imageUrl);
                                    }
                                } else {
                                    Toast.makeText(AddLibro.this, "Este libro no tiene portada", Toast.LENGTH_SHORT).show();
                                }

                                // Autocompletar campos
                                title.setText(titulo);
                                des.setText(descripcion);
                                dataPubli.setText(fechaPublicacion);

                                Toast.makeText(AddLibro.this, "Libro encontrado: " + titulo, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(AddLibro.this, "No se encontró información del libro", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(AddLibro.this, "Error al procesar datos", Toast.LENGTH_SHORT).show();
                        }
                        progressBar.setVisibility(View.GONE);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.GONE);
                        String mensajeError = "Error de conexión";
                        if (error.networkResponse != null) {
                            mensajeError += " Código: " + error.networkResponse.statusCode;
                        }
                        if (error.getMessage() != null) {
                            mensajeError += " " + error.getMessage();
                        }
                        Toast.makeText(AddLibro.this, mensajeError, Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }
        );

        requestQueue.add(jsonRequest);
    }

    private void cargarImagenDesdeURL(String imageUrl) {
        // Usar Glide para cargar la imagen directamente en el ImageView y capturar el Bitmap
        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        Toast.makeText(AddLibro.this, "Error al cargar la imagen", Toast.LENGTH_SHORT).show();
                        if (e != null) e.printStackTrace();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                        // Guardar la imagen descargada en la variable global
                        imagenSeleccionada = redimensionarBitmap(resource, 500, 500);
                        return false; // Retornar false permite que Glide actualice el ImageView img automáticamente
                    }
                })
                .into(img);
    }

    private void solicitarPermisoCamara() {
        if (checkSelfPermission(android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.CAMERA}, REQUEST_PERMISSION);
        } else {
            iniciarEscanerCodigo();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                iniciarEscanerCodigo();
            } else {
                Toast.makeText(this, "Permiso de cámara denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
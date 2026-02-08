package com.example.proyectolibro.Controller;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectolibro.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class AddLibro extends AppCompatActivity {
    private ImageView img;
    private EditText title;
    private EditText des;
    private EditText dataPubli;
    private Button save;
    private Button back;
    private static final int REQUEST_GALLERY = 1;
    private static final int REQUEST_PERMISSION = 2;
    private Bitmap imagenSeleccionada;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_libro);

        imagenSeleccionada = null;
        img = findViewById(R.id.img);

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

        title = findViewById(R.id.title);
        des = findViewById(R.id.textDescrip);
        dataPubli = findViewById(R.id.datePublis);
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
}
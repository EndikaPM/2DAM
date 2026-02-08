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

import androidx.appcompat.app.AppCompatActivity;


import com.example.proyectolibro.R;

import java.util.Calendar;

public class Update extends AppCompatActivity {
    private ImageView img;
    private EditText tituloEdit;
    private EditText descripEdit;
    private EditText fechaEdit;
    private Button back;
    private Button save;
    private int posicionEditar = -1;
    private int imagenId;
    private Bitmap imagenSeleccionada;
    private static final int REQUEST_GALLERY = 1;
    private static final int REQUEST_PERMISSION = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        img = findViewById(R.id.imgEdit);
        tituloEdit = findViewById(R.id.titleEdit);
        descripEdit = findViewById(R.id.textDescripEdit);
        fechaEdit = findViewById(R.id.datePublisEdit);
        Intent datosRecibidos = getIntent();// asi se recibe el inten que envias de la Actividad2
        if (datosRecibidos != null){
            posicionEditar = datosRecibidos.getIntExtra("indexEdit", -1);

            imagenId = datosRecibidos.getIntExtra("img", R.drawable.ic_launcher_foreground);
            img.setImageResource(datosRecibidos.getIntExtra("img", imagenId));
            tituloEdit.setText(datosRecibidos.getStringExtra("titleEdit"));
            descripEdit.setText(datosRecibidos.getStringExtra("textEdit"));
            fechaEdit.setText(datosRecibidos.getStringExtra("dateEdit"));
        }

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirGaleria();
            }
        });

        fechaEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePicker = new DatePickerDialog(
                        Update.this,
                        (view, selectedYear, selectedMonth, selectedDay) -> {
                            String dateWithFormat = String.format("%02d/%02d/%04d", selectedDay, selectedMonth + 1, selectedYear);

                            fechaEdit.setText(dateWithFormat);
                        },
                        year, month, day
                );
                datePicker.show();
            }
        });

        back = findViewById(R.id.backEdit);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(Update.this, Activity2.class);
            }
        });

        save = findViewById(R.id.saveEdit);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void abrirGaleria(){
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
        Intent datosActualizados = new Intent();

        // Si el usuario seleccionó una nueva imagen, la enviamos redimensionada
        if (imagenSeleccionada != null) {
            Bitmap bitmapParaEnviar = redimensionarBitmap(imagenSeleccionada, 300, 300);
            datosActualizados.putExtra("imagen", bitmapParaEnviar);
        }

        datosActualizados.putExtra("indexEdit", posicionEditar);
        datosActualizados.putExtra("Title", tituloEdit.getText().toString());
        datosActualizados.putExtra("Descri", descripEdit.getText().toString());
        datosActualizados.putExtra("datePublis", fechaEdit.getText().toString());

        setResult(RESULT_OK, datosActualizados);
        super.finish();
    }
}
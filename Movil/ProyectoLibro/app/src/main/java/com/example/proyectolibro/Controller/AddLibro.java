package com.example.proyectolibro.Controller;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectolibro.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class AddLibro extends AppCompatActivity {
    private TextView img;
    private EditText title;
    private EditText des;
    private EditText dataPubli;
    private Button save;
    private Button back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_libro);

        img = findViewById(R.id.img);
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

    @Override
    public void finish(){
        Intent addOrUpdateDatos = new Intent();
        addOrUpdateDatos.putExtra("imagen", img.getText());
        addOrUpdateDatos.putExtra("Title", title.getText().toString());
        addOrUpdateDatos.putExtra("Descri", des.getText().toString());
        addOrUpdateDatos.putExtra("datePublis", dataPubli.getText().toString());
        setResult(RESULT_OK, addOrUpdateDatos);

        super.finish();
    }
}
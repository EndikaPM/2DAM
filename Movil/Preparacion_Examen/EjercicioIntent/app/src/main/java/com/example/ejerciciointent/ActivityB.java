package com.example.ejerciciointent;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityB extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b_activity);

        //optenemos datos del intent
        String reciboString = getIntent().getStringExtra("myStringKey");
        double reciboDouble = getIntent().getDoubleExtra("myDoubleKey", 0.0);

        //Mostrar los datos en un Toast
        Toast.makeText(this,"String de actividad a: "+reciboString
        +", Numero actividad a " + reciboDouble, Toast.LENGTH_LONG).show();

        String[] opeciones = {"opcion1","opion2","opcion3","opion4","opcion5","opio62","opcion35","opion45"};
        AutoCompleteTextView textoAutocompletado = (AutoCompleteTextView) findViewById(R.id.autocompletado);
        ArrayAdapter<String> apaptador = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, opeciones);

        textoAutocompletado.setAdapter(apaptador);
    }
}

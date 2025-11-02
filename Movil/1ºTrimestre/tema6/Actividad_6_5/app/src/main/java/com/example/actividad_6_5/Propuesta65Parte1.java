package com.example.actividad_6_5;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Propuesta65Parte1 extends AppCompatActivity {

    TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.propuesta_6_5_parte1);

        texto = (TextView) findViewById(R.id.text);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
            texto.setTextColor(Color.RED);
        if (item.getItemId() == (R.id.op1)){
            texto.setText("Pulsado Lunes");
            return true;
        }else if (item.getItemId() == (R.id.op2)){
            texto.setText("Pulsado Martes");
            return true;
        }else if (item.getItemId() == (R.id.op3)){
            texto.setText("Pulsado Miercoles");
            return true;
        }else if (item.getItemId() == (R.id.op4)){
            texto.setText("Pulsado Jueves");
            return true;
        }else if (item.getItemId() == (R.id.op5)){
            texto.setText("Pulsado Viernes");
            return true;
        }else if (item.getItemId() == (R.id.op6)){
            texto.setText("Pulsado Sabado");
            return true;
        }else if (item.getItemId() == (R.id.op7)){
            texto.setText("Pulsado Domingo");
            return true;
        }else{
            return super.onOptionsItemSelected(item);
        }

    }
}
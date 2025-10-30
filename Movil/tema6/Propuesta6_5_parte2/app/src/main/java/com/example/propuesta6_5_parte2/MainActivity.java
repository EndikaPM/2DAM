package com.example.propuesta6_5_parte2;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private TextView texto;
    private static final int lunes = 0;
    private static final int martes = 1;
    private static final int miercoles = 2;
    private static final int jueves = 3;
    private static final int viernes = 4;
    private static final int sabado = 5;
    private static final int domingo = 6;
    private static final int enero = 10;
    private static final int febrero = 11;
    private static final int marzo = 12;
    private static final int abril = 13;
    private static final int mayo = 14;
    private static final int junio = 15;
    private static final int julio = 16;
    private static final int agosto = 17;
    private static final int septiembre = 18;
    private static final int octubre = 19;
    private static final int noviembre = 20;
    private static final int diciembre = 21;

    private static final int diasSem = 31;
    private static final int mesAno = 32;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texto = findViewById(R.id.text2);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        SubMenu diaSem = menu.addSubMenu(Menu.NONE, diasSem, Menu.NONE, "Dias de la semana");
        diaSem.add(Menu.NONE, lunes, Menu.NONE, "Lunes");
        diaSem.add(Menu.NONE, martes, Menu.NONE, "Martes");
        diaSem.add(Menu.NONE, miercoles, Menu.NONE, "Miercoles");
        diaSem.add(Menu.NONE, jueves, Menu.NONE, "Jueves");
        diaSem.add(Menu.NONE, viernes, Menu.NONE, "Viernes");
        diaSem.add(Menu.NONE, sabado, Menu.NONE, "Sabado");
        diaSem.add(Menu.NONE, domingo, Menu.NONE, "Domingo");
        SubMenu meseAno = menu.addSubMenu(Menu.NONE, mesAno, Menu.NONE, "Meses del año");
        meseAno.add(Menu.NONE,enero, Menu.NONE, "Enero");
        meseAno.add(Menu.NONE,febrero, Menu.NONE, "Febreo");
        meseAno.add(Menu.NONE,marzo, Menu.NONE, "Marzo");
        meseAno.add(Menu.NONE,abril, Menu.NONE, "Abril");
        meseAno.add(Menu.NONE,mayo, Menu.NONE, "Mayo");
        meseAno.add(Menu.NONE,junio, Menu.NONE, "Junio");
        meseAno.add(Menu.NONE,julio, Menu.NONE, "Julio");
        meseAno.add(Menu.NONE,agosto, Menu.NONE, "Agosto");
        meseAno.add(Menu.NONE,septiembre, Menu.NONE, "Septiembre");
        meseAno.add(Menu.NONE,octubre, Menu.NONE, "Octubre");
        meseAno.add(Menu.NONE,noviembre, Menu.NONE, "Noviembre");
        meseAno.add(Menu.NONE,diciembre, Menu.NONE, "Diciembre");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        texto.setTextColor(Color.RED);
        if (menuItem.getItemId() == (lunes)){
            texto.setText("Pulsado el Lunes");
            return true;
        }else if (menuItem.getItemId() == martes){
            texto.setTextColor(Color.BLUE);
            texto.setText("Pulsado el Martes");
            return true;
        }else if (menuItem.getItemId() == miercoles){
            texto.setText("Pulsado el Miércoles");
            return true;
        }else if (menuItem.getItemId() == jueves){
            texto.setText("Pulsado el Jueves");
            return true;
        }else if (menuItem.getItemId() == viernes){
            texto.setText("Pulsado el Viernes");
            return true;
        }else if (menuItem.getItemId() == sabado){
            texto.setText("Pulsado el Sábado");
            return true;
        }else if (menuItem.getItemId() == domingo){
            texto.setText("Pulsado el Domingo");
            return true;
        }else if (menuItem.getItemId() == enero){
            texto.setText("Pulsado Enero");
            return true;
        }else if (menuItem.getItemId() == febrero){
            texto.setText("Pulsado Febrero");
            return true;
        }else if (menuItem.getItemId() == marzo){
            texto.setText("Pulsado Marzo");
            return true;
        }else if (menuItem.getItemId() == abril){
            texto.setText("Pulsado Abril");
            return true;
        }else if (menuItem.getItemId() == mayo){
            texto.setText("Pulsado Mayo");
            return true;
        }else if (menuItem.getItemId() == junio){
            texto.setText("Pulsado Junio");
            return true;
        }else if (menuItem.getItemId() == julio){
            texto.setText("Pulsado Julio");
            return true;
        }else if (menuItem.getItemId() == agosto){
            texto.setText("Pulsado Agosto");
            return true;
        }else if (menuItem.getItemId() == septiembre){
            texto.setText("Pulsado Septiembre");
            return true;
        }else if (menuItem.getItemId() == octubre){
            texto.setText("Pulsado Octubre");
            return true;
        }else if (menuItem.getItemId() == noviembre){
            texto.setText("Pulsado Noviembre");
            return true;
        }else if (menuItem.getItemId() == diciembre){
            texto.setText("Pulsado Diciembre");
            return true;
        }else{
            return super.onOptionsItemSelected(menuItem);
        }
    }
}
package com.example.figura6_8;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView menuContextual;
    TextView menurespuesta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menuContextual = findViewById(R.id.menuContext);
        registerForContextMenu(menuContextual);
        menurespuesta = findViewById(R.id.menuRespuesta);


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo ){
        super.onCreateContextMenu(menu, v,menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_complet, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem menuItem){
        if (menuItem.getItemId() == R.id.op1){
            menurespuesta.setText(menuItem.getTitle().toString());
        } else if (menuItem.getItemId() == R.id.op2) {
            menurespuesta.setText(menuItem.getTitle().toString());
        }else if (menuItem.getItemId() == R.id.op3) {
            menurespuesta.setText(menuItem.getTitle().toString());
        }else if (menuItem.getItemId() == R.id.op4) {
            menurespuesta.setText(menuItem.getTitle().toString());
        }else if (menuItem.getItemId() == R.id.op5) {
            menurespuesta.setText(menuItem.getTitle().toString());
        }else if (menuItem.getItemId() == R.id.op6) {
            menurespuesta.setText(menuItem.getTitle().toString());
        }else if (menuItem.getItemId() == R.id.op7) {
            menurespuesta.setText(menuItem.getTitle().toString());
        }else if (menuItem.getItemId() == R.id.enero) {
            menurespuesta.setText(menuItem.getTitle().toString());
        }else if (menuItem.getItemId() == R.id.febrero) {
            menurespuesta.setText(menuItem.getTitle().toString());
        }else if (menuItem.getItemId() == R.id.marzo) {
            menurespuesta.setText(menuItem.getTitle().toString());
        }else if (menuItem.getItemId() == R.id.abril) {
            menurespuesta.setText(menuItem.getTitle().toString());
        }else if (menuItem.getItemId() == R.id.mayo) {
            menurespuesta.setText(menuItem.getTitle().toString());
        }else if (menuItem.getItemId() == R.id.junio) {
            menurespuesta.setText(menuItem.getTitle().toString());
        }else if (menuItem.getItemId() == R.id.julio) {
            menurespuesta.setText(menuItem.getTitle().toString());
        }else if (menuItem.getItemId() == R.id.agosto) {
            menurespuesta.setText(menuItem.getTitle().toString());
        }else if (menuItem.getItemId() == R.id.septiempre) {
            menurespuesta.setText(menuItem.getTitle().toString());
        }else if (menuItem.getItemId() == R.id.octubre) {
            menurespuesta.setText(menuItem.getTitle().toString());
        }else if (menuItem.getItemId() == R.id.novienbre) {
            menurespuesta.setText(menuItem.getTitle().toString());
        }else if (menuItem.getItemId() == R.id.dicienbre) {
            menurespuesta.setText(menuItem.getTitle().toString());
        }else{
            menurespuesta.setText("NO has seleccionado ningun menu");
        }
        return super.onContextItemSelected(menuItem);
    }
}
package com.example.figura6_9;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] datos = {
                "Opción 1", "Opción 2", "Opción 3"
        };

        lista = (ListView) findViewById(R.id.listado);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,datos);
        lista.setAdapter(adapter);
        registerForContextMenu(lista);
    }

    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(contextMenu, view,menuInfo);
        MenuInflater inflater = getMenuInflater();
        AdapterView.AdapterContextMenuInfo info =(AdapterView.AdapterContextMenuInfo) menuInfo;
        contextMenu.setHeaderTitle(lista.getAdapter().getItem(info.position).toString());
        switch(info.position){
            case 0:
                inflater.inflate(R.menu.menu_complet, contextMenu);
            case 1:
                inflater.inflate(R.menu.menu_day_week, contextMenu);
            case 2:
                inflater.inflate(R.menu.menu_month_year, contextMenu);
        }
    }
}
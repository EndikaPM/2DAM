package com.example.actividad6_3;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txt = (TextView) findViewById(R.id.txt);
        Spinner spi = (Spinner) findViewById(R.id.spi);

        final String datos [] = {"Desplega para elegir","España", "Portugal", "Francia", "Inglaterra"};
        ArrayAdapter<String> adap = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);

        spi.setAdapter(adap);


        spi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String add = (String) parent.getAdapter().getItem(position);
                txt.setText(add);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                String add = (String) parent.getAdapter().getItem(0);
                txt.setText(add);
            }
        });
    }
}
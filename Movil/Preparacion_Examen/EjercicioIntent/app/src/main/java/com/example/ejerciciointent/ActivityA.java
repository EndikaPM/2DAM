package com.example.ejerciciointent;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class ActivityA extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_activity);

        MaterialButton button = (MaterialButton) findViewById(R.id.button_send);
        EditText texto = (EditText) findViewById(R.id.textoEnviar);
        TextView textoPantalla = (TextView) findViewById(R.id.vistaTexto);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myString = texto.getText().toString();;
                //double myDouble = 123.45;

                Intent intent = new Intent(ActivityA.this, ActivityB.class);
                intent.putExtra("myStringKey", myString);
                //intent.putExtra("myDoubleKey", myDouble);
                //startActivity(intent);
                SpannableString cambioColor = new SpannableString(myString);
                cambioColor.setSpan(new ForegroundColorSpan(Color.BLUE),0,
                                    myString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                textoPantalla.append("\n"+cambioColor);
            }
        });


    }
}
package com.example.actividad_11_9;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView tvInfo;
    private View touchArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = findViewById(R.id.tvInfo);
        touchArea = findViewById(R.id.touchArea);


        touchArea.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                manejarEventoMultiTouch(event);
                return true;
            }
        });

    }

    private void manejarEventoMultiTouch(MotionEvent event) {

        int pointerCount = event.getPointerCount();


        StringBuilder info = new StringBuilder();


        int action = event.getActionMasked();
        int pointerIndex = event.getActionIndex();


        info.append("EVENTO PRINCIPAL: ");
        info.append(obtenerNombreEvento(action)).append("\n");
        info.append(" en punto ").append(pointerIndex).append("\n\n");


        for (int i = 0; i < pointerCount; i++) {
            int pointerId = event.getPointerId(i);
            float x = event.getX(i);
            float y = event.getY(i);


            info.append("PUNTO ").append(i + 1).append("\n");

            info.append("ID: ").append(pointerId).append("\n");
            info.append("Posición X: ").append(String.format("%.2f", x)).append(" px\n");
            info.append("Posición Y: ").append(String.format("%.2f", y)).append(" px\n");
        }


        tvInfo.setText(info.toString());
    }

    private String obtenerNombreEvento(int action) {
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                return "ACTION_DOWN";
            case MotionEvent.ACTION_UP:
                return "ACTION_UP ";
            case MotionEvent.ACTION_POINTER_DOWN:
                return "ACTION_POINTER_DOWN";
            case MotionEvent.ACTION_POINTER_UP:
                return "ACTION_POINTER_UP";
            case MotionEvent.ACTION_MOVE:
                return "ACTION_MOVE";
            case MotionEvent.ACTION_CANCEL:
                return "ACTION_CANCEL";
            default:
                return "EVENTO DESCONOCIDO (" + action + ")";
        }
    }
}
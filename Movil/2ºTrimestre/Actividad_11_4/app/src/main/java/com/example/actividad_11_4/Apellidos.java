package com.example.actividad_11_4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.View;

import androidx.annotation.NonNull;

public class Apellidos extends View {

    private String apellidos = "Perez Mas";

    public Apellidos(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        Paint pincel = new Paint();
        pincel.setAntiAlias(true);// Para que las letras no se vean pixeladas

        // --- 1. Tamaño y Color básico ---
        pincel.setColor(Color.RED);
        pincel.setTextSize(50);
        canvas.drawText(apellidos, 100, 100, pincel);

        // --- 2. Justificación (Alineación) ---
        pincel.setColor(Color.BLUE);
        pincel.setTextSize(80);
        pincel.setTextAlign(Paint.Align.RIGHT);// Paint.Align.RIGHT alinea el texto a la izquierda
        canvas.drawText(apellidos,1000,250,pincel);

        // --- 3. Escala horizontal (Hacer el texto más ancho o estrecho) ---
        pincel.setColor(Color.MAGENTA);
        pincel.setTextSize(60);
        pincel.setTextScaleX(2.0f);
        canvas.drawText(apellidos,700, 500,pincel);
        pincel.setTextScaleX(1.0f); // Restaurar escala X

        // --- 4. Estilo de trazo (contorno ) ---
        pincel.setColor(Color.GREEN);
        pincel.setTextSize(70);
        pincel.setStyle(Paint.Style.STROKE);
        canvas.drawText(apellidos,600,900,pincel);
        pincel.setStyle(Paint.Style.FILL);

        // --- 5. Inclinación (skewX) ---
        pincel.setColor(Color.CYAN);
        pincel.setTextSize(70);
        pincel.setTextSkewX(-0.5f); // Inclinación tipo cursiva
        canvas.drawText(apellidos, 800, 1250, pincel);
        pincel.setTextSkewX(0); // Reset de inclinación

        // --- 6. Rotación (Inclinación de tod el lienzo) ---
        pincel.setColor(Color.BLACK);
        pincel.setTextSize(90);
        pincel.setTypeface(Typeface.DEFAULT_BOLD);

        canvas.save(); // Guardamos el estado actual del canvas
        canvas.rotate(15, 200, 850); // Rotamos 15 grados usando el punto de dibujo como eje
        canvas.drawText(apellidos, 900, 1500, pincel);
        canvas.restore(); // Volvemos a la rotación original para no afectar a futuros dibujos
    }
}

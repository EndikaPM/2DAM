package com.example.actividad_11_3;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Dibujo11_3 extends View {

    public Dibujo11_3(Context context) {
        super(context);
    }

    protected void onDraw(android.graphics.Canvas canvas) {
        super.onDraw(canvas);

        Paint pincelCuadrado = new Paint();
        pincelCuadrado.setColor(Color.argb(255,255,0,0));
        pincelCuadrado.setStyle(Paint.Style.STROKE);
        pincelCuadrado.setStrokeWidth(10);

        Paint pincelOvalo = new Paint();
        pincelOvalo.setColor(Color.argb(255,0,0,255));
        pincelOvalo.setStyle(Paint.Style.STROKE);
        pincelOvalo.setStrokeWidth(10);

        Paint pincelCirculo = new Paint();
        pincelCirculo.setColor(Color.argb(255,0,0,0));
        pincelCirculo.setStyle(Paint.Style.STROKE);
        pincelCirculo.setStrokeWidth(10);

        canvas.drawRect(300, 400, 800, 700, pincelCuadrado);
        canvas.drawOval(299,399,798,698, pincelOvalo);
        canvas.drawCircle(550, 550, 120,pincelCirculo);

        Paint cuadradoRojoSinRelleno = new Paint();
        cuadradoRojoSinRelleno.setColor(Color.RED);
        cuadradoRojoSinRelleno.setStyle(Paint.Style.STROKE);
        cuadradoRojoSinRelleno.setStrokeWidth(10);

        Paint cuadradoRojoRelleno = new Paint();
        cuadradoRojoRelleno.setColor(Color.RED);
        cuadradoRojoRelleno.setStyle(Paint.Style.FILL);

        Paint cuadradoRellenoYBorde = new Paint();
        cuadradoRellenoYBorde.setColor(Color.RED);
        cuadradoRellenoYBorde.setStyle(Paint.Style.FILL_AND_STROKE);
        cuadradoRellenoYBorde.setStrokeWidth(10);

        canvas.drawRect(150, 800, 350, 1000, cuadradoRojoSinRelleno);
        canvas.drawRect(450, 800, 650, 1000, cuadradoRojoRelleno);
        canvas.drawRect(750,800,950,1000,cuadradoRellenoYBorde);
    }
}

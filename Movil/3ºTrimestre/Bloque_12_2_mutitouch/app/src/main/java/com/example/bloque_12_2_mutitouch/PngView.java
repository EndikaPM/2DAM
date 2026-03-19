package com.example.bloque_12_2_mutitouch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageView;

public class PngView extends SurfaceView implements Runnable{
    Thread gameThread;
    boolean running = false;
    SurfaceHolder holder;
    ImageView pikachu;


    public PngView (Context context){
        super(context);
        holder = getHolder();
        pikachu.findViewById(R.drawable.pikachu);
    }

    @Override
    public void run() {
        while(running){
            if(!holder.getSurface().isValid())continue;
            Canvas canvas = holder.lockCanvas();
            canvas.drawColor(Color.WHITE);

            holder.unlockCanvasAndPost(canvas);
        }

        try{
            Thread.sleep(16);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void resume() {
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void pause() {
        running = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

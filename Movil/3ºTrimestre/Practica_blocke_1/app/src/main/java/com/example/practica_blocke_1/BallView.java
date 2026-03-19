package com.example.practica_blocke_1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class BallView extends SurfaceView implements Runnable {
    Thread gameThread;
    boolean running = false;
    SurfaceHolder holder;
    Paint paint;

    public BallView (Context context){
        super(context);
        holder = getHolder();
        paint = new Paint();
        paint.setColor(Color.RED);
    }

    @Override
    public void run() {
          while(running){
              if(!holder.getSurface().isValid())continue;
              Canvas canvas = holder.lockCanvas();
              canvas.drawColor(Color.WHITE);
              canvas.drawCircle(400,400,100, paint);
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

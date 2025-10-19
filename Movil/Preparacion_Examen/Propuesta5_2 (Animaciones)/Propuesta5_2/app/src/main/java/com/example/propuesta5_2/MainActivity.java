package com.example.propuesta5_2;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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

        TextView text = (TextView) findViewById(R.id.text);
        Animation alfaAnim1 = AnimationUtils.loadAnimation(this, R.anim.alfa1);
        Animation alfaAnim2 = AnimationUtils.loadAnimation(this, R.anim.alfa2);
        Animation scaleAnim1 = AnimationUtils.loadAnimation(this, R.anim.escala1);
        Animation scaleAnim2 = AnimationUtils.loadAnimation(this, R.anim.escala2);
        Animation moveAnim1 = AnimationUtils.loadAnimation(this, R.anim.mueve1);
        Animation moveAnim2 = AnimationUtils.loadAnimation(this, R.anim.mueve2);
        Animation rotateAnim1 = AnimationUtils.loadAnimation(this, R.anim.rotar1);
        Animation rotateAnim2 = AnimationUtils.loadAnimation(this, R.anim.rotar2);
        Animation rotateAnim3 = AnimationUtils.loadAnimation(this, R.anim.rotar3);
        Animation someAnim1 = AnimationUtils.loadAnimation(this, R.anim.varios1);
        Animation someAnim2 = AnimationUtils.loadAnimation(this, R.anim.varios2);

        text.setAnimation(someAnim2);
    }
}
package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;

public class Starting extends AppCompatActivity {
    LottieAnimationView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);
        lv = (LottieAnimationView) findViewById(R.id.animationView);
        lv.setAnimation(R.raw.tic2);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Starting.this,First.class);
                startActivity(intent);
                finish();
            }
        },2000);

    }
}
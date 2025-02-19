package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        TextView textV1 = (TextView) findViewById(R.id.text1);
//        ImageButton gameboard_back = (ImageButton) findViewById(R.id.backTostart);
          overridePendingTransition(R.anim.go_in,R.anim.go_out);
//
//        Shader myShader = new LinearGradient(
//                250, 0, 0, 0,
//                Color.parseColor("#1C335E"), Color.parseColor("#00ABB1"),
//                Shader.TileMode.CLAMP);
//        textV1.getPaint().setShader( myShader );

//        gameboard_back.setOnClickListener(view -> finish());
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.come_out,R.anim.close_left);
    }
}
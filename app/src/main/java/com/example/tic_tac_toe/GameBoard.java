package com.example.tic_tac_toe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;

public class GameBoard extends AppCompatActivity {
    TextView T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, tx1, tx2, tv1, p1, p2, btn1, btn2;
    String[] isEmpty = {"Yes","Yes","Yes","Yes","Yes","Yes","Yes","Yes","Yes"};
    Button Reset, sc;
    ImageView t13, t46, t79, t17, t28, t39, t37, t19, player, robot_player;
    private boolean freshlyStarted, isEmptyBoard;
    int mCount, mVar, cH;
    String[] st = new String[4];
    String nm1, nm2, score;
    private static int p1s;
    private static int p2s;
    private static MediaPlayer music5, music6, music7, music8;
    ConstraintLayout constraintLayout;
    private static Snackbar msg1, msg2;

    public boolean cal1(@NonNull TextView a , TextView b , TextView c , TextView d , TextView e, TextView f, TextView g, TextView h, String s, String s1, MediaPlayer music) {
        if ((a.getText().toString().equals("X")) && (b.getText().toString() .equals("X") ) && (c.getText().toString() .equals("X"))) {

            if(h.getText().equals("X")) {
                music.start();
                d.setVisibility(View.VISIBLE);
                d.setBackgroundResource(R.drawable.win_red);
                d.setText(s);
                g.setVisibility(View.GONE);
                p1s++;
                score = ("Score - "+p1s);
                e.setText(score);
            }
            else {
                music.start();
                d.setVisibility(View.VISIBLE);
                d.setBackgroundResource(R.drawable.win_red);
                d.setText(s1);
                g.setVisibility(View.GONE);
                p2s++;
                score = ("Score - "+p2s);
                f.setText(score);
            }

        } else if ((a.getText().toString().equals("O")) && (b.getText().toString().equals("O") ) && (c.getText().toString().equals("O"))) {

            if(h.getText().equals("O")) {
                music.start();
                d.setVisibility(View.VISIBLE);
                d.setBackgroundResource(R.drawable.win_blue);
                d.setText(s);
                g.setVisibility(View.GONE);
                p1s++;
                score = ("Score - "+p1s);
                e.setText(score);
            }
            else {
                music.start();
                d.setVisibility(View.VISIBLE);
                d.setBackgroundResource(R.drawable.win_blue);
                d.setText(s1);
                g.setVisibility(View.GONE);
                p2s++;
                score = ("Score - "+p2s);
                f.setText(score);
            }

        } else
        {
            return false;
        }
        return true;
    }
    public void cal2(TextView a, TextView b, MediaPlayer music){
        if(isBoardFilledUp())
        {
            music.start();
            a.setVisibility(View.VISIBLE);
            a.setBackgroundResource(R.drawable.game_draw);
            a.setText(R.string.gameover);
            b.setVisibility(View.GONE);
        }
    }
    public void setSnackbarLayout(@NonNull Snackbar sb) {
        View snackLayout = getLayoutInflater().inflate(R.layout.custom_snackbar,null);
        sb.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout customSnackLayout = (Snackbar.SnackbarLayout) sb.getView();
        customSnackLayout.setPadding(80,0,80,60);
        TextView SnackButton = snackLayout.findViewById(R.id.OK);
        TextView SnackText = snackLayout.findViewById(R.id.sntxt);
        if (sb == msg1){
            SnackText.setText(R.string.scores_are_already_0);
        } else if (sb == msg2){
            SnackText.setText(R.string.board_is_already_set_to_play);
        }
        SnackButton.setOnClickListener(view -> sb.dismiss());
        customSnackLayout.addView(snackLayout,0);
    }
    public void setSymbolOnBoard(TextView textview) {
        mVar = (mCount % 2);
        mCount++;
        if (isEmptyBoard){
            isEmptyBoard = false;
        }
        if(mVar==0) {
            textview.setText("O");
            tv1.setBackgroundResource(R.drawable.turn_play_red);
            tv1.setText(R.string.x_turn);
            int red =0xFF3e59c7;
            textview.setTextColor(red);
            textview.setEnabled(false);
        }
        else {
            textview.setText("X");
            tv1.setBackgroundResource(R.drawable.turn_play_blue);
            tv1.setText(R.string.o_turn);
            int blue = 0xFFf2071b;
            textview.setTextColor(blue);
            textview.setEnabled(false);
        }
    }
    public void resetGame() {
        mCount = cH;
        isEmptyBoard = true;
        
        T1.setEnabled(true);T2.setEnabled(true);T3.setEnabled(true);T4.setEnabled(true);T5.setEnabled(true);T6.setEnabled(true);T7.setEnabled(true);T8.setEnabled(true);T9.setEnabled(true);
        T1.setText("");T2.setText("");T3.setText("");T4.setText("");T5.setText("");T6.setText("");T7.setText("");T8.setText("");T9.setText("");

        T10.setVisibility(View.GONE);

        t19.setVisibility(View.GONE);
        t39.setVisibility(View.GONE);
        t37.setVisibility(View.GONE);
        t13.setVisibility(View.GONE);
        t17.setVisibility(View.GONE);
        t28.setVisibility(View.GONE);
        t79.setVisibility(View.GONE);
        t46.setVisibility(View.GONE);

        isEmpty = new String[]{"Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes"};

        if (mCount == 1) {
            tv1.setVisibility(View.VISIBLE);
            tv1.setBackgroundResource(R.drawable.turn_play_red);
            tv1.setText(R.string.x_turn);
        }
        else {
            tv1.setVisibility(View.VISIBLE);
            tv1.setBackgroundResource(R.drawable.turn_play_blue);
            tv1.setText(R.string.o_turn);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (freshlyStarted) {
            if (st[0].equals("X") && st[1].equals("O")) {
                mCount = 1;
                cH = 1;
                btn1.setBackgroundResource(R.drawable.turn_play_red);
                btn1.setText(st[0]);
                btn2.setBackgroundResource(R.drawable.turn_play_blue);
                btn2.setText(st[1]);
                tv1.setBackgroundResource(R.drawable.turn_play_red);
                tv1.setText(R.string.x_turn);
                robot_player.setBackgroundResource(R.drawable.player_blue);
                player.setBackgroundResource(R.drawable.player_red);

            } else {
                mCount = 0;
                cH = 0;
                btn1.setBackgroundResource(R.drawable.turn_play_blue);
                btn1.setText(st[0]);
                btn2.setBackgroundResource(R.drawable.turn_play_red);
                btn2.setText(st[1]);
                tv1.setBackgroundResource(R.drawable.turn_play_blue);
                tv1.setText(R.string.o_turn);
                robot_player.setBackgroundResource(R.drawable.player_red);
                player.setBackgroundResource(R.drawable.player_blue);

            }
            nm1 = ("Winner - " + st[2]);
            nm2 = ("Winner - " + st[3]);
            tx1.setText(st[2]);
            tx1.setSelected(true);
            tx2.setText(st[3]);
            tx2.setSelected(true);

            p1s = 0;
            p2s = 0;
            T10.setVisibility(View.GONE);

            music5 = MediaPlayer.create(this, R.raw.button_click);
            music6 = MediaPlayer.create(this, R.raw.winner);
            music7 = MediaPlayer.create(this, R.raw.game_over);
            music8 = MediaPlayer.create(this, R.raw.board_click);

            msg1 = Snackbar.make(constraintLayout, " ", Snackbar.LENGTH_SHORT);
            msg2 = Snackbar.make(constraintLayout, " ", Snackbar.LENGTH_SHORT);
            setSnackbarLayout(msg1);
            setSnackbarLayout(msg2);
            freshlyStarted = false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameboard);

        constraintLayout = findViewById(R.id.ConstraintLayout);

        t13 = findViewById(R.id.t13);
        t46 = findViewById(R.id.t46);
        t79 = findViewById(R.id.t79);
        t17 = findViewById(R.id.t17);
        t28 = findViewById(R.id.t28);
        t39 = findViewById(R.id.t39);
        t37 = findViewById(R.id.t37);
        t19 = findViewById(R.id.t19);
        tx1 = findViewById(R.id.P1);
        tx2 = findViewById(R.id.P2);
        tv1 = findViewById(R.id.tv1);
        T1 = findViewById(R.id.T1);
        T2 = findViewById(R.id.T2);
        T3 = findViewById(R.id.T3);
        T4 = findViewById(R.id.T4);
        T5 = findViewById(R.id.T5);
        T6 = findViewById(R.id.T6);
        T8 = findViewById(R.id.T8);
        T9 = findViewById(R.id.T9);
        T7 = findViewById(R.id.T7);
        T10 = findViewById(R.id.T10);
        p1 = findViewById(R.id.sc1);
        p2 = findViewById(R.id.sc2);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        Reset = findViewById(R.id.Reset);
        sc = findViewById(R.id.Score);
        player = findViewById(R.id.player);
        robot_player = findViewById(R.id.robot_player);

        Intent intent = getIntent();
        st = intent.getStringArrayExtra("str_array");

        freshlyStarted = true;
        isEmptyBoard = true;

                                                                // Coding Starts

        T1.setOnClickListener(v -> {

            music8.start();

            if (msg1.isShown()){ msg1.dismiss();}
            if (msg2.isShown()){ msg2.dismiss();}

            setSymbolOnBoard(T1);
            isEmpty[0] = "No";

            if(cal1(T1, T2, T3, T10, p1, p2, tv1, btn1, nm1, nm2, music6)) {
                T4.setEnabled(false);T5.setEnabled(false);T6.setEnabled(false);T7.setEnabled(false);T8.setEnabled(false);T9.setEnabled(false);
                t13.setVisibility(View.VISIBLE);
            }
            else if(cal1(T1, T5, T9, T10, p1, p2, tv1, btn1, nm1, nm2, music6)){
                T2.setEnabled(false);T3.setEnabled(false);T4.setEnabled(false);T6.setEnabled(false);T7.setEnabled(false);T8.setEnabled(false);
                t19.setVisibility(View.VISIBLE);
            }
            else if(cal1(T1, T4, T7, T10, p1, p2, tv1, btn1, nm1, nm2, music6)){
                T2.setEnabled(false);T3.setEnabled(false);T5.setEnabled(false);T6.setEnabled(false);T8.setEnabled(false);T9.setEnabled(false);
                t17.setVisibility(View.VISIBLE);
            }
            else {
                cal2(T10, tv1, music7);
            }
        });
        T2.setOnClickListener(v -> {

            music8.start();
            if (msg1.isShown()){ msg1.dismiss();}
            if (msg2.isShown()){ msg2.dismiss();}

            setSymbolOnBoard(T2);
            isEmpty[1] = "No";

            if(cal1(T1, T2, T3, T10, p1, p2, tv1, btn1, nm1, nm2, music6)) {
                T4.setEnabled(false);T5.setEnabled(false);T6.setEnabled(false);T7.setEnabled(false);T8.setEnabled(false);T9.setEnabled(false);
                t13.setVisibility(View.VISIBLE);
            }
            else if(cal1(T2, T5, T8, T10, p1, p2, tv1, btn1, nm1, nm2, music6)) {
                T1.setEnabled(false);T3.setEnabled(false);T4.setEnabled(false);T6.setEnabled(false);T7.setEnabled(false);T9.setEnabled(false);
                t28.setVisibility(View.VISIBLE);
            }
            else {
                cal2(T10, tv1, music7);
            }
        });
        T3.setOnClickListener(v -> {

            music8.start();
            if (msg1.isShown()){ msg1.dismiss();}
            if (msg2.isShown()){ msg2.dismiss();}

            setSymbolOnBoard(T3);
            isEmpty[2] = "No";

            if(cal1(T1, T2, T3, T10, p1, p2, tv1, btn1, nm1, nm2, music6)) {
                T4.setEnabled(false);T5.setEnabled(false);T6.setEnabled(false);T7.setEnabled(false);T8.setEnabled(false);T9.setEnabled(false);
                t13.setVisibility(View.VISIBLE);
            }
            else if(cal1(T3, T5, T7, T10, p1, p2, tv1, btn1, nm1, nm2, music6)){
                T1.setEnabled(false);T2.setEnabled(false);T4.setEnabled(false);T6.setEnabled(false);T8.setEnabled(false);T9.setEnabled(false);
                t37.setVisibility(View.VISIBLE);
            }
            else if(cal1(T3, T6, T9, T10, p1, p2, tv1, btn1, nm1, nm2, music6)) {
                T1.setEnabled(false);T2.setEnabled(false);T4.setEnabled(false);T5.setEnabled(false);T7.setEnabled(false);T8.setEnabled(false);
                t39.setVisibility(View.VISIBLE);
            }
            else {
                cal2(T10, tv1, music7);
            }
        });
        T4.setOnClickListener(v -> {

            music8.start();
            if (msg1.isShown()){ msg1.dismiss();}
            if (msg2.isShown()){ msg2.dismiss();}

            setSymbolOnBoard(T4);
            isEmpty[3] = "No";

            if(cal1(T4, T1, T7, T10, p1, p2, tv1, btn1, nm1, nm2, music6)){
                T2.setEnabled(false);T3.setEnabled(false);T5.setEnabled(false);T6.setEnabled(false);T8.setEnabled(false);T9.setEnabled(false);
                t17.setVisibility(View.VISIBLE);
            }
            else if(cal1(T4, T5, T6, T10, p1, p2, tv1, btn1, nm1, nm2, music6)) {
                T1.setEnabled(false);T2.setEnabled(false);T3.setEnabled(false);T7.setEnabled(false);T8.setEnabled(false);T9.setEnabled(false);
                t46.setVisibility(View.VISIBLE);
            }
            else {
                cal2(T10, tv1, music7);
            }
        });
        T5.setOnClickListener(v -> {

            music8.start();
            if (msg1.isShown()){ msg1.dismiss();}
            if (msg2.isShown()){ msg2.dismiss();}

            setSymbolOnBoard(T5);
            isEmpty[4] = "No";

            if(cal1(T5, T2, T8, T10, p1, p2, tv1, btn1, nm1, nm2, music6)) {
                T1.setEnabled(false);T6.setEnabled(false);T3.setEnabled(false);T7.setEnabled(false);T4.setEnabled(false);T9.setEnabled(false);
                t28.setVisibility(View.VISIBLE);
            }
            else if(cal1(T5, T1, T9, T10, p1, p2, tv1, btn1, nm1, nm2, music6)){
                T2.setEnabled(false);T6.setEnabled(false);T3.setEnabled(false);T7.setEnabled(false);T4.setEnabled(false);T8.setEnabled(false);
                t19.setVisibility(View.VISIBLE);
            }
            else if(cal1(T5, T3, T7, T10, p1, p2, tv1, btn1, nm1, nm2, music6)) {
                T2.setEnabled(false);T6.setEnabled(false);T1.setEnabled(false);T9.setEnabled(false);T4.setEnabled(false);T8.setEnabled(false);
                t37.setVisibility(View.VISIBLE);
            }
            else if(cal1(T5, T4, T6, T10, p1, p2, tv1, btn1, nm1, nm2, music6)) {
                T2.setEnabled(false);T1.setEnabled(false);T3.setEnabled(false);T7.setEnabled(false);T9.setEnabled(false);T8.setEnabled(false);
                t46.setVisibility(View.VISIBLE);
            }
            else {
                cal2(T10, tv1, music7);
            }
        });
        T6.setOnClickListener(v -> {

            music8.start();
            if (msg1.isShown()){ msg1.dismiss();}
            if (msg2.isShown()){ msg2.dismiss();}

            setSymbolOnBoard(T6);
            isEmpty[5] = "No";

            if(cal1(T6, T5, T4, T10, p1, p2, tv1, btn1, nm1, nm2, music6)) {
                T2.setEnabled(false);T1.setEnabled(false);T3.setEnabled(false);T7.setEnabled(false);T9.setEnabled(false);T8.setEnabled(false);
                t46.setVisibility(View.VISIBLE);
            }
            else if(cal1(T6, T3, T9, T10, p1, p2, tv1, btn1, nm1, nm2, music6)) {
                T2.setEnabled(false);T1.setEnabled(false);T4.setEnabled(false);T7.setEnabled(false);T5.setEnabled(false);T8.setEnabled(false);
                t39.setVisibility(View.VISIBLE);
            }
            else {
                cal2(T10, tv1, music7);
            }
        });
        T7.setOnClickListener(v -> {

            music8.start();
            if (msg1.isShown()){ msg1.dismiss();}
            if (msg2.isShown()){ msg2.dismiss();}

            setSymbolOnBoard(T7);
            isEmpty[6] = "No";

            if(cal1(T7, T5, T3, T10, p1, p2, tv1, btn1, nm1, nm2, music6)) {
                T2.setEnabled(false);T1.setEnabled(false);T6.setEnabled(false);T4.setEnabled(false);T9.setEnabled(false);T8.setEnabled(false);
                t37.setVisibility(View.VISIBLE);
            }
            else if(cal1(T7, T8, T9, T10, p1, p2, tv1, btn1, nm1, nm2, music6)) {
                T2.setEnabled(false);T1.setEnabled(false);T3.setEnabled(false);T5.setEnabled(false);T4.setEnabled(false);T6.setEnabled(false);
                t79.setVisibility(View.VISIBLE);
            }
            else if(cal1(T7, T4, T1, T10, p1, p2, tv1, btn1, nm1, nm2, music6)) {
                T2.setEnabled(false);T5.setEnabled(false);T3.setEnabled(false);T6.setEnabled(false);T9.setEnabled(false);T8.setEnabled(false);
                t17.setVisibility(View.VISIBLE);
            }
            else {
                cal2(T10, tv1, music7);
            }
        });
        T8.setOnClickListener(v -> {

            music8.start();
            if (msg1.isShown()){ msg1.dismiss();}
            if (msg2.isShown()){ msg2.dismiss();}

            setSymbolOnBoard(T8);
            isEmpty[7] = "No";

            if(cal1(T8, T9, T7, T10, p1, p2, tv1, btn1, nm1, nm2, music6)) {
                T2.setEnabled(false);T1.setEnabled(false);T3.setEnabled(false);T4.setEnabled(false);T5.setEnabled(false);T6.setEnabled(false);
                t79.setVisibility(View.VISIBLE);
            }
            else if(cal1(T8, T5, T2, T10, p1, p2, tv1, btn1, nm1, nm2, music6)) {
                T7.setEnabled(false);T1.setEnabled(false);T3.setEnabled(false);T4.setEnabled(false);T9.setEnabled(false);T6.setEnabled(false);
                t28.setVisibility(View.VISIBLE);
            }
            else {
                cal2(T10, tv1, music7);
            }
        });
        T9.setOnClickListener(v -> {

            music8.start();
            if (msg1.isShown()){ msg1.dismiss();}
            if (msg2.isShown()){ msg2.dismiss();}

            setSymbolOnBoard(T9);
            isEmpty[8] = "No";

            if(cal1(T9, T5, T1, T10, p1, p2, tv1, btn1, nm1, nm2, music6)) {
                T2.setEnabled(false);T8.setEnabled(false);T3.setEnabled(false);T4.setEnabled(false);T7.setEnabled(false);T6.setEnabled(false);
                t19.setVisibility(View.VISIBLE);
            }
            else if(cal1(T9, T8, T7, T10, p1, p2, tv1, btn1, nm1, nm2, music6)) {
                T2.setEnabled(false);T1.setEnabled(false);T3.setEnabled(false);T4.setEnabled(false);T5.setEnabled(false);T6.setEnabled(false);
                t79.setVisibility(View.VISIBLE);
            }
            else if(cal1(T9, T6, T3, T10, p1, p2, tv1, btn1, nm1, nm2, music6)) {
                T2.setEnabled(false);T8.setEnabled(false);T1.setEnabled(false);T4.setEnabled(false);T7.setEnabled(false);T5.setEnabled(false);
                t39.setVisibility(View.VISIBLE);
            }
            else {
                cal2(T10, tv1, music7);
            }
        });
        Reset.setOnClickListener(v -> {
            music5.start();
            if (music6.isPlaying() || music7.isPlaying()){
                music6.stop();
                music7.stop();
                try {
                    music6.prepare();
                    music7.prepare();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            if(isEmptyBoard){
                msg2.show();
            }
            else {
                resetGame();
            }
        });
        sc.setOnClickListener(v -> {
            music5.start();

            if((p1s == 0) && (p2s == 0)){
                msg1.show();
            }
            else {
                p1s = 0;
                score = ("Score - "+p1s);
                p1.setText(score);
                p2s = 0;
                score = ("Score - "+p2s);
                p2.setText(score);
            }
        });
    }
    private boolean isBoardFilledUp(){
        for (int n = 0; n<9; n++){
            if (isEmpty[n].equals("Yes")) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.come_out,R.anim.fade_out);
    }
}
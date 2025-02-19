package com.example.tic_tac_toe;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.Random;

public class GameBoardSinglePlayer extends AppCompatActivity {
    private static int p1s;
    private static int p2s;
    private static MediaPlayer music5, music6, music7, music8;
    private static Snackbar msg1, msg2;
    TextView T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, tx1, tx2, tv1, p1, p2, btn1, btn2, TV;
    String[] isEmpty = {"Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes"};
    TextView[] names;
    Intent intent;
    Random random;
    Handler handler;
    Button Reset, sc;
    ImageView t13, t46, t79, t17, t28, t39, t37, t19, player, robot_player;
    int mCount, cH, rndmPos, lastPos;
    String[] st = new String[4];
    String nm1, nm2, score;
    ConstraintLayout constraintLayout;
    private boolean freshlyStarted, isFromUser, isComputer, gameHasEnded, isEmptyBoard, isFirstMove;

    public boolean cal1(@NonNull TextView a, TextView b, TextView c, TextView d, TextView e, TextView f, TextView g, TextView h, String s, String s1, MediaPlayer music) {
        if ((a.getText().toString().equals("X")) && (b.getText().toString().equals("X")) && (c.getText().toString().equals("X"))) {

            if (h.getText().equals("X")) {
                music.start();
                gameHasEnded = true;
                d.setVisibility(View.VISIBLE);
                d.setBackgroundResource(R.drawable.win_red);
                d.setText(s);
                g.setVisibility(View.GONE);
                p1s++;
                score = ("Score - " + p1s);
                e.setText(score);
            } else {
                music.start();
                gameHasEnded = true;
                d.setVisibility(View.VISIBLE);
                d.setBackgroundResource(R.drawable.win_red);
                d.setText(s1);
                g.setVisibility(View.GONE);
                p2s++;
                score = ("Score - " + p2s);
                f.setText(score);
            }

        } else if ((a.getText().toString().equals("O")) && (b.getText().toString().equals("O")) && (c.getText().toString().equals("O"))) {

            if (h.getText().equals("O")) {
                music.start();
                gameHasEnded = true;
                d.setVisibility(View.VISIBLE);
                d.setBackgroundResource(R.drawable.win_blue);
                d.setText(s);
                g.setVisibility(View.GONE);
                p1s++;
                score = ("Score - " + p1s);
                e.setText(score);
            } else {
                music.start();
                gameHasEnded = true;
                d.setVisibility(View.VISIBLE);
                d.setBackgroundResource(R.drawable.win_blue);
                d.setText(s1);
                g.setVisibility(View.GONE);
                p2s++;
                score = ("Score - " + p2s);
                f.setText(score);
            }

        } else {
            return false;
        }
        return true;
    }

    public void cal2(TextView a, TextView b, MediaPlayer music) {
        if (isBoardFilledUp()) {
            music.start();
            a.setVisibility(View.VISIBLE);
            a.setBackgroundResource(R.drawable.game_draw);
            a.setText(R.string.gameover);
            b.setVisibility(View.GONE);
            gameHasEnded = true;
        }
    }

    public void setSnackbarLayout(@NonNull Snackbar sb) {
        View snackLayout = getLayoutInflater().inflate(R.layout.custom_snackbar, null);
        sb.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout customSnackLayout = (Snackbar.SnackbarLayout) sb.getView();
        customSnackLayout.setPadding(80, 0, 80, 60);
        TextView SnackButton = snackLayout.findViewById(R.id.OK);
        TextView SnackText = snackLayout.findViewById(R.id.sntxt);
        if (sb == msg1) {
            SnackText.setText(R.string.scores_are_already_0);
        } else if (sb == msg2) {
            SnackText.setText(R.string.board_is_already_set_to_play);
        }
        SnackButton.setOnClickListener(view -> sb.dismiss());
        customSnackLayout.addView(snackLayout, 0);
    }

    public void setSymbolOnBoard(TextView textview, boolean byUser) {
        if (isEmptyBoard) {
            isEmptyBoard = false;
        }

        if (mCount == 0) {
            textview.setText("O");
            tv1.setBackgroundResource(R.drawable.turn_play_red);
            tv1.setText(R.string.x_turn);
            int red = 0xFF3e59c7;
            textview.setTextColor(red);
            if (byUser) {
                textview.setEnabled(false);
            }
            mCount = 1;
        } else {
            textview.setText("X");
            tv1.setBackgroundResource(R.drawable.turn_play_blue);
            tv1.setText(R.string.o_turn);
            int blue = 0xFFf2071b;
            textview.setTextColor(blue);
            if (byUser) {
                textview.setEnabled(false);
            }
            mCount = 0;
        }
    }

    public void resetGame() {
        handler.removeCallbacksAndMessages(null);
        mCount = cH;

        T1.setEnabled(true);
        T2.setEnabled(true);
        T3.setEnabled(true);
        T4.setEnabled(true);
        T5.setEnabled(true);
        T6.setEnabled(true);
        T7.setEnabled(true);
        T8.setEnabled(true);
        T9.setEnabled(true);
        T1.setText("");
        T2.setText("");
        T3.setText("");
        T4.setText("");
        T5.setText("");
        T6.setText("");
        T7.setText("");
        T8.setText("");
        T9.setText("");

        T10.setVisibility(View.GONE);

        t19.setVisibility(View.GONE);
        t39.setVisibility(View.GONE);
        t37.setVisibility(View.GONE);
        t13.setVisibility(View.GONE);
        t17.setVisibility(View.GONE);
        t28.setVisibility(View.GONE);
        t79.setVisibility(View.GONE);
        t46.setVisibility(View.GONE);

        if (mCount == 1) {
            tv1.setVisibility(View.VISIBLE);
            tv1.setBackgroundResource(R.drawable.turn_play_red);
            tv1.setText(R.string.x_turn);
        } else {
            tv1.setVisibility(View.VISIBLE);
            tv1.setBackgroundResource(R.drawable.turn_play_blue);
            tv1.setText(R.string.o_turn);
        }

        isEmpty = new String[]{"Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes"};
        gameHasEnded = false;
        isComputer = false;
        isFromUser = true;
        isEmptyBoard = true;
        isFirstMove = true;
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
                robot_player.setBackgroundResource(R.drawable.robot);
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
                robot_player.setBackgroundResource(R.drawable.robot);
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

        handler = new Handler();
        random = new Random();
        names = new TextView[]{T1, T2, T3, T4, T5, T6, T7, T8, T9, T10};

        intent = getIntent();
        st = intent.getStringArrayExtra("str_array");
        isFromUser = true;
        isComputer = false;
        gameHasEnded = false;
        isEmptyBoard = true;
        freshlyStarted = true;
        isFirstMove = true;

        T1.setOnClickListener(v -> {
            if ((isFromUser) && (!gameHasEnded)) {
                music8.start();
                if (msg1.isShown() || msg2.isShown()) {
                    msg1.dismiss();
                    msg2.dismiss();
                }
                isEmpty[0] = "No";
                for (int z = 0; z < 9; z++) {
                    if (isEmpty[z].equals("Yes")) {
                        names[z].setEnabled(false);
                    }
                }

                isFromUser = false;
                isComputer = true;
                setSymbolOnBoard(T1, true);
            }

            if (cal1(T1, T2, T3, T10, p1, p2, tv1, btn1, nm1, nm2, music6)) {
                T4.setEnabled(false);T5.setEnabled(false);T6.setEnabled(false);T7.setEnabled(false);T8.setEnabled(false);T9.setEnabled(false);
                t13.setVisibility(View.VISIBLE);
            } else if (cal1(T1, T5, T9, T10, p1, p2, tv1, btn1, nm1, nm2, music6)) {
                T2.setEnabled(false);T3.setEnabled(false);T4.setEnabled(false);T6.setEnabled(false);T7.setEnabled(false);T8.setEnabled(false);
                t19.setVisibility(View.VISIBLE);
            } else if (cal1(T1, T4, T7, T10, p1, p2, tv1, btn1, nm1, nm2, music6)) {
                T2.setEnabled(false);T3.setEnabled(false);T5.setEnabled(false);T6.setEnabled(false);T8.setEnabled(false);T9.setEnabled(false);
                t17.setVisibility(View.VISIBLE);
            } else {
                cal2(T10, tv1, music7);
            }
            if ((isComputer) && (!gameHasEnded)) {
                if (isFirstMove) {
                    handler.postDelayed(this::setSymbolComputerRandom, 500);
                    isFirstMove = !isFirstMove;
                } else {
                    TV = callMethod(lastPos);
                    if (TV == null) {
                        TV = tOne();
                    }
                    if (TV != null) {
                        handler.postDelayed(() -> setSymbolComputer(TV), 500);
                    } else {
                        handler.postDelayed(this::setSymbolComputerRandom, 500);
                    }
                }
            }
        });

        T2.setOnClickListener(v -> {

            if ((isFromUser) && (!gameHasEnded)) {
                music8.start();

                if (msg1.isShown() || msg2.isShown()) {
                    msg1.dismiss();
                    msg2.dismiss();
                }
                isEmpty[1] = "No";
                for (int z = 0; z < 9; z++) {
                    if (isEmpty[z].equals("Yes")) {
                        names[z].setEnabled(false);
                    }
                }

                isFromUser = false;
                isComputer = true;
                setSymbolOnBoard(T2, true);
            }

            if (cal1(T1, T2, T3, T10, p1, p2, tv1, btn1, nm1, nm2, music6)) {
                T4.setEnabled(false);T5.setEnabled(false);T6.setEnabled(false);T7.setEnabled(false);T8.setEnabled(false);T9.setEnabled(false);
                t13.setVisibility(View.VISIBLE);
            } else if (cal1(T2, T5, T8, T10, p1, p2, tv1, btn1, nm1, nm2, music6)) {
                T1.setEnabled(false);T3.setEnabled(false);T4.setEnabled(false);T6.setEnabled(false);T7.setEnabled(false);T9.setEnabled(false);
                t28.setVisibility(View.VISIBLE);
            } else {
                cal2(T10, tv1, music7);
            }
            if ((isComputer) && (!gameHasEnded)) {
                if (isFirstMove) {
                    handler.postDelayed(this::setSymbolComputerRandom, 500);
                    isFirstMove = !isFirstMove;
                } else {
                    TV = callMethod(lastPos);
                    if (TV == null) {
                        TV = tTwo();
                    }
                    if (TV == null){
                        TV = tOne();
                    }
                    if (TV == null){
                        TV = tThree();
                    }
                    if (TV != null) {
                        handler.postDelayed(() -> setSymbolComputer(TV), 500);
                    } else {
                        handler.postDelayed(this::setSymbolComputerRandom, 500);
                    }
                }
            }
        });

        T3.setOnClickListener(v -> {

            if ((isFromUser) && (!gameHasEnded)) {
                music8.start();

                if (msg1.isShown() || msg2.isShown()) {
                    msg1.dismiss();
                    msg2.dismiss();
                }
                isEmpty[2] = "No";
                for (int z = 0; z < 9; z++) {
                    if (isEmpty[z].equals("Yes")) {
                        names[z].setEnabled(false);
                    }
                }

                isFromUser = false;
                isComputer = true;
                setSymbolOnBoard(T3, true);
            }

            if (cal1(T1, T2, T3, T10, p1, p2, tv1, btn1, nm1, nm2, music6)) {
                T4.setEnabled(false);T5.setEnabled(false);T6.setEnabled(false);T7.setEnabled(false);T8.setEnabled(false);T9.setEnabled(false);
                t13.setVisibility(View.VISIBLE);
            } else if (cal1(T3, T5, T7, T10, p1, p2, tv1, btn1, nm1, nm2, music6)) {
                T1.setEnabled(false);T2.setEnabled(false);T4.setEnabled(false);T6.setEnabled(false);T8.setEnabled(false);T9.setEnabled(false);
                t37.setVisibility(View.VISIBLE);
            } else if (cal1(T3, T6, T9, T10, p1, p2, tv1, btn1, nm1, nm2, music6)) {
                T1.setEnabled(false);T2.setEnabled(false);T4.setEnabled(false);T5.setEnabled(false);T7.setEnabled(false);T8.setEnabled(false);
                t39.setVisibility(View.VISIBLE);
            } else {
                cal2(T10, tv1, music7);
            }
            if ((isComputer) && (!gameHasEnded)) {
                if (isFirstMove) {
                    handler.postDelayed(this::setSymbolComputerRandom, 500);
                    isFirstMove = !isFirstMove;
                } else {
                    TV = callMethod(lastPos);
                    if (TV == null) {
                        TV = tThree();
                    }
                    if (TV != null) {
                        handler.postDelayed(() -> setSymbolComputer(TV), 500);
                    } else {
                        handler.postDelayed(this::setSymbolComputerRandom, 500);
                    }
                }
            }
        });

        T4.setOnClickListener(v -> {

            if ((isFromUser) && (!gameHasEnded)) {
                music8.start();

                if (msg1.isShown() || msg2.isShown()) {
                    msg1.dismiss();
                    msg2.dismiss();
                }
                isEmpty[3] = "No";
                for (int z = 0; z < 9; z++) {
                    if (isEmpty[z].equals("Yes")) {
                        names[z].setEnabled(false);
                    }
                }

                isFromUser = false;
                isComputer = true;
                setSymbolOnBoard(T4, true);
            }

            if (cal1(T4, T1, T7, T10, p1, p2, tv1, btn1, nm1, nm2, music6)) {
                T2.setEnabled(false);T3.setEnabled(false);T5.setEnabled(false);T6.setEnabled(false);T8.setEnabled(false);T9.setEnabled(false);
                t17.setVisibility(View.VISIBLE);
            } else if (cal1(T4, T5, T6, T10, p1, p2, tv1, btn1, nm1, nm2, music6)) {
                T1.setEnabled(false);T2.setEnabled(false);T3.setEnabled(false);T7.setEnabled(false);T8.setEnabled(false);T9.setEnabled(false);
                t46.setVisibility(View.VISIBLE);
            } else {
                cal2(T10, tv1, music7);
            }
            if ((isComputer) && (!gameHasEnded)) {
                if (isFirstMove) {
                    handler.postDelayed(this::setSymbolComputerRandom, 500);
                    isFirstMove = !isFirstMove;
                } else {
                    TV = callMethod(lastPos);
                    if (TV == null) {
                        TV = tFour();
                    }
                    if (TV == null){
                        TV = tOne();
                    }
                    if (TV == null){
                        TV = tThree();
                    }
                    if (TV != null) {
                        handler.postDelayed(() -> setSymbolComputer(TV), 500);
                    } else {
                        handler.postDelayed(this::setSymbolComputerRandom, 500);
                    }
                }
            }
        });

        T5.setOnClickListener(v -> {

            if ((isFromUser) && (!gameHasEnded)) {
                music8.start();

                if (msg1.isShown() || msg2.isShown()) {
                    msg1.dismiss();
                    msg2.dismiss();
                }
                isEmpty[4] = "No";
                for (int z = 0; z < 9; z++) {
                    if (isEmpty[z].equals("Yes")) {
                        names[z].setEnabled(false);
                    }
                }

                isFromUser = false;
                isComputer = true;
                setSymbolOnBoard(T5, true);
            }

            if (cal1(T5, T2, T8, T10, p1, p2, tv1, btn1, nm1, nm2, music6)) {
                T1.setEnabled(false);T6.setEnabled(false);T3.setEnabled(false);T7.setEnabled(false);T4.setEnabled(false);T9.setEnabled(false);
                t28.setVisibility(View.VISIBLE);
            } else if (cal1(T5, T1, T9, T10, p1, p2, tv1, btn1, nm1, nm2, music6)) {
                T2.setEnabled(false);T6.setEnabled(false);T3.setEnabled(false);T7.setEnabled(false);T4.setEnabled(false);T8.setEnabled(false);
                t19.setVisibility(View.VISIBLE);
            } else if (cal1(T5, T3, T7, T10, p1, p2, tv1, btn1, nm1, nm2, music6)) {
                T2.setEnabled(false);T6.setEnabled(false);T1.setEnabled(false);T9.setEnabled(false);T4.setEnabled(false);T8.setEnabled(false);t37.setVisibility(View.VISIBLE);
            } else if (cal1(T5, T4, T6, T10, p1, p2, tv1, btn1, nm1, nm2, music6)) {
                T2.setEnabled(false);T1.setEnabled(false);T3.setEnabled(false);T7.setEnabled(false);T9.setEnabled(false);T8.setEnabled(false);
                t46.setVisibility(View.VISIBLE);
            } else {
                cal2(T10, tv1, music7);
            }
            if ((isComputer) && (!gameHasEnded)) {
                if (isFirstMove) {
                    handler.postDelayed(this::setSymbolComputerRandom, 500);
                    isFirstMove = !isFirstMove;
                } else {
                    TV = callMethod(lastPos);
                    if (TV == null) {
                        TV = tFive();
                    }
                    if (TV != null) {
                        handler.postDelayed(() -> setSymbolComputer(TV), 500);
                    } else {
                        handler.postDelayed(this::setSymbolComputerRandom, 500);
                    }
                }
            }
        });

        T6.setOnClickListener(v -> {

            if ((isFromUser) && (!gameHasEnded)) {
                music8.start();

                if (msg1.isShown() || msg2.isShown()) {
                    msg1.dismiss();
                    msg2.dismiss();
                }
                isEmpty[5] = "No";
                for (int z = 0; z < 9; z++) {
                    if (isEmpty[z].equals("Yes")) {
                        names[z].setEnabled(false);
                    }
                }

                isFromUser = false;
                isComputer = true;
                setSymbolOnBoard(T6, true);
            }

            if (cal1(T6, T5, T4, T10, p1, p2, tv1, btn1, nm1, nm2, music6)) {
                T2.setEnabled(false);T1.setEnabled(false);T3.setEnabled(false);T7.setEnabled(false);T9.setEnabled(false);T8.setEnabled(false);
                t46.setVisibility(View.VISIBLE);
            } else if (cal1(T6, T3, T9, T10, p1, p2, tv1, btn1, nm1, nm2, music6)) {
                T2.setEnabled(false);T1.setEnabled(false);T4.setEnabled(false);T7.setEnabled(false);T5.setEnabled(false);T8.setEnabled(false);
                t39.setVisibility(View.VISIBLE);
            } else {
                cal2(T10, tv1, music7);
            }
            if ((isComputer) && (!gameHasEnded)) {
                if (isFirstMove) {
                    handler.postDelayed(this::setSymbolComputerRandom, 500);
                    isFirstMove = !isFirstMove;
                } else {
                    TV = callMethod(lastPos);
                    if (TV == null) {
                        TV = tSix();
                    }
                    if (TV == null){
                        TV = tOne();
                    }
                    if (TV == null){
                        TV = tThree();
                    }
                    if (TV != null) {
                        handler.postDelayed(() -> setSymbolComputer(TV), 500);
                    } else {
                        handler.postDelayed(this::setSymbolComputerRandom, 500);
                    }
                }
            }
        });

        T7.setOnClickListener(v -> {

            if ((isFromUser) && (!gameHasEnded)) {
                music8.start();

                if (msg1.isShown() || msg2.isShown()) {
                    msg1.dismiss();
                    msg2.dismiss();
                }
                isEmpty[6] = "No";
                for (int z = 0; z < 9; z++) {
                    if (isEmpty[z].equals("Yes")) {
                        names[z].setEnabled(false);
                    }
                }

                isFromUser = false;
                isComputer = true;
                setSymbolOnBoard(T7, true);
            }

            if (cal1(T7, T5, T3, T10, p1, p2, tv1, btn1, nm1, nm2, music6)) {
                T2.setEnabled(false);T1.setEnabled(false);T6.setEnabled(false);T4.setEnabled(false);T9.setEnabled(false);T8.setEnabled(false);
                t37.setVisibility(View.VISIBLE);
            } else if (cal1(T7, T8, T9, T10, p1, p2, tv1, btn1, nm1, nm2, music6)) {
                T2.setEnabled(false);T1.setEnabled(false);T3.setEnabled(false);T5.setEnabled(false);T4.setEnabled(false);T6.setEnabled(false);
                t79.setVisibility(View.VISIBLE);
            } else if (cal1(T7, T4, T1, T10, p1, p2, tv1, btn1, nm1, nm2, music6)) {
                T2.setEnabled(false);T5.setEnabled(false);T3.setEnabled(false);T6.setEnabled(false);T9.setEnabled(false);T8.setEnabled(false);
                t17.setVisibility(View.VISIBLE);
            } else {
                cal2(T10, tv1, music7);
            }
            if ((isComputer) && (!gameHasEnded)) {
                if (isFirstMove) {
                    handler.postDelayed(this::setSymbolComputerRandom, 500);
                    isFirstMove = !isFirstMove;
                } else {
                    TV = callMethod(lastPos);
                    if (TV == null) {
                        TV = tSeven();
                    }
                    if (TV != null) {
                        handler.postDelayed(() -> setSymbolComputer(TV), 500);
                    } else {
                        handler.postDelayed(this::setSymbolComputerRandom, 500);
                    }
                }
            }
        });

        T8.setOnClickListener(v -> {

            if ((isFromUser) && (!gameHasEnded)) {
                music8.start();

                if (msg1.isShown() || msg2.isShown()) {
                    msg1.dismiss();
                    msg2.dismiss();
                }
                isEmpty[7] = "No";
                for (int z = 0; z < 9; z++) {
                    if (isEmpty[z].equals("Yes")) {
                        names[z].setEnabled(false);
                    }
                }

                isFromUser = false;
                isComputer = true;
                setSymbolOnBoard(T8, true);
            }

            if (cal1(T8, T9, T7, T10, p1, p2, tv1, btn1, nm1, nm2, music6)) {
                T2.setEnabled(false);T1.setEnabled(false);T3.setEnabled(false);T4.setEnabled(false);T5.setEnabled(false);T6.setEnabled(false);
                t79.setVisibility(View.VISIBLE);
            } else if (cal1(T8, T5, T2, T10, p1, p2, tv1, btn1, nm1, nm2, music6)) {
                T7.setEnabled(false);T1.setEnabled(false);T3.setEnabled(false);T4.setEnabled(false);T9.setEnabled(false);T6.setEnabled(false);
                t28.setVisibility(View.VISIBLE);
            } else {
                cal2(T10, tv1, music7);
            }
            if ((isComputer) && (!gameHasEnded)) {
                if (isFirstMove) {
                    handler.postDelayed(this::setSymbolComputerRandom, 500);
                    isFirstMove = !isFirstMove;
                } else {
                    TV = callMethod(lastPos);
                    if (TV == null) {
                        TV = tEight();
                    }
                    if (TV == null){
                        TV = tOne();
                    }
                    if (TV == null){
                        TV = tThree();
                    }
                    if (TV != null) {
                        handler.postDelayed(() -> setSymbolComputer(TV), 500);
                    } else {
                        handler.postDelayed(this::setSymbolComputerRandom, 500);
                    }
                }
            }
        });

        T9.setOnClickListener(v -> {

            if ((isFromUser) && (!gameHasEnded)) {
                music8.start();

                if (msg1.isShown() || msg2.isShown()) {
                    msg1.dismiss();
                    msg2.dismiss();
                }
                isEmpty[8] = "No";
                for (int z = 0; z < 9; z++) {
                    if (isEmpty[z].equals("Yes")) {
                        names[z].setEnabled(false);
                    }
                }

                isFromUser = false;
                isComputer = true;
                setSymbolOnBoard(T9, true);
            }
            if (cal1(T9, T5, T1, T10, p1, p2, tv1, btn1, nm1, nm2, music6)) {
                T2.setEnabled(false);T8.setEnabled(false);T3.setEnabled(false);T4.setEnabled(false);T7.setEnabled(false);T6.setEnabled(false);
                t19.setVisibility(View.VISIBLE);
            } else if (cal1(T9, T8, T7, T10, p1, p2, tv1, btn1, nm1, nm2, music6)) {
                T2.setEnabled(false);T1.setEnabled(false);T3.setEnabled(false);T4.setEnabled(false);T5.setEnabled(false);T6.setEnabled(false);
                t79.setVisibility(View.VISIBLE);
            } else if (cal1(T9, T6, T3, T10, p1, p2, tv1, btn1, nm1, nm2, music6)) {
                T2.setEnabled(false);T8.setEnabled(false);T1.setEnabled(false);T4.setEnabled(false);T7.setEnabled(false);T5.setEnabled(false);
                t39.setVisibility(View.VISIBLE);
            } else {
                cal2(T10, tv1, music7);
            }
            if ((isComputer) && (!gameHasEnded)) {
                if (isFirstMove) {
                    handler.postDelayed(this::setSymbolComputerRandom, 500);
                    isFirstMove = !isFirstMove;
                } else {
                    TV = callMethod(lastPos);
                    if (TV == null) {
                        TV = tNine();
                    }
                    if (TV != null) {
                        handler.postDelayed(() -> setSymbolComputer(TV), 500);
                    } else {
                        handler.postDelayed(this::setSymbolComputerRandom, 500);
                    }
                }
            }
        });
        // Reset Button
        Reset.setOnClickListener(v -> {
            music5.start();
            if (music6.isPlaying() || music7.isPlaying()) {
                music6.stop();
                music7.stop();
                try {
                    music6.prepare();
                    music7.prepare();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (isEmptyBoard) {
                msg2.show();
            } else {
                resetGame();
            }
        });
        // Score Button
        sc.setOnClickListener(v -> {
            music5.start();

            if ((p1s == 0) && (p2s == 0)) {
                msg1.show();
            } else {
                p1s = 0;
                score = ("Score - " + p1s);
                p1.setText(score);
                p2s = 0;
                score = ("Score - " + p2s);
                p2.setText(score);
            }
        });
    }

    private void setSymbolComputer(TextView checkTV) {
        int i = 0;
        while (!names[i].equals(checkTV)) {
            i++;
        }
        music8.start();
        isComputer = false;
        names[i].setEnabled(true);
        setSymbolOnBoard(names[i], false);
        checkTV.performClick();
        isEmpty[i] = "No";
        for (int z = 0; z < 9; z++) {
            if ((!names[z].toString().equals(checkTV.toString())) && (isEmpty[z].equals("Yes")) && (!gameHasEnded)) {
                names[z].setEnabled(true);
            }
        }
        isFromUser = true;
        lastPos = i;
        names[i].setEnabled(false);
    }

    private void setSymbolComputerRandom() {
        rndmPos = random.nextInt(9);
        while (isEmpty[rndmPos].equals("No")) {
            rndmPos = random.nextInt(9);
        }
        if (isEmpty[rndmPos].equals("Yes")) {
            music8.start();
            names[rndmPos].setEnabled(true);
            isComputer = false;
            setSymbolOnBoard(names[rndmPos], false);
            names[rndmPos].performClick();
            isEmpty[rndmPos] = "No";
            for (int z = 0; z < 9; z++) {
                if ((z != rndmPos) && (isEmpty[z].equals("Yes")) && (!gameHasEnded)) {
                    names[z].setEnabled(true);
                }
            }
            isFromUser = true;
            lastPos = rndmPos;
            names[rndmPos].setEnabled(false);
        }
    }

    @Nullable
    private TextView tOne() {
    if ((T1.getText().toString().equals(T2.getText().toString())) && (T3.getText().toString().equals(""))){
        return T3;
    } else if ((T1.getText().toString().equals(T3.getText().toString())) && (T2.getText().toString().equals(""))) {
        return T2;
    } else if ((T1.getText().toString().equals(T4.getText().toString())) && (T7.getText().toString().equals(""))) {
        return T7;
    } else if ((T1.getText().toString().equals(T7.getText().toString())) && (T4.getText().toString().equals(""))) {
        return T4;
    } else if ((T1.getText().toString().equals(T5.getText().toString())) && (T9.getText().toString().equals(""))) {
        return T9;
    } else if ((T1.getText().toString().equals(T9.getText().toString())) && (T5.getText().toString().equals(""))) {
        return T5;
    } else {return null;}
    }

    @Nullable
    private TextView tTwo() {
        if ((T2.getText().toString().equals(T5.getText().toString())) && (T8.getText().toString().equals(""))) {
            return T8;
        } else if ((T2.getText().toString().equals(T3.getText().toString())) && (T1.getText().toString().equals(""))) {
            return T1;
        } else if ((T2.getText().toString().equals(T1.getText().toString())) && (T3.getText().toString().equals(""))) {
            return T3;
        } else if ((T2.getText().toString().equals(T8.getText().toString())) && (T5.getText().toString().equals(""))) {
            return T5;
        } else {
            return null;
        }
    }

    @Nullable
    private TextView tThree() {
        if ((T3.getText().toString().equals(T2.getText().toString())) && (T1.getText().toString().equals(""))) {
            return T1;
        } else if ((T3.getText().toString().equals(T1.getText().toString())) && (T2.getText().toString().equals(""))) {
            return T2;
        } else if ((T3.getText().toString().equals(T6.getText().toString())) && (T9.getText().toString().equals(""))) {
            return T9;
        } else if ((T3.getText().toString().equals(T9.getText().toString())) && (T6.getText().toString().equals(""))) {
            return T6;
        } else if ((T3.getText().toString().equals(T5.getText().toString())) && (T7.getText().toString().equals(""))) {
            return T7;
        } else if ((T3.getText().toString().equals(T7.getText().toString())) && (T5.getText().toString().equals(""))) {
            return T5;
        } else {
            return null;
        }
    }

    @Nullable
    private TextView tFour() {
        if ((T4.getText().toString().equals(T1.getText().toString())) && (T7.getText().toString().equals(""))) {
            return T7;
        } else if ((T4.getText().toString().equals(T7.getText().toString())) && (T1.getText().toString().equals(""))) {
            return T1;
        } else if ((T4.getText().toString().equals(T5.getText().toString())) && (T6.getText().toString().equals(""))) {
            return T6;
        } else if ((T4.getText().toString().equals(T6.getText().toString())) && (T5.getText().toString().equals(""))) {
            return T5;
        } else {
            return null;
        }
    }

    @Nullable
    private TextView tFive() {
        if ((T5.getText().toString().equals(T1.getText().toString())) && (T9.getText().toString().equals(""))) {
            return T9;
        } else if ((T5.getText().toString().equals(T9.getText().toString())) && (T1.getText().toString().equals(""))) {
            return T1;
        } else if ((T5.getText().toString().equals(T3.getText().toString())) && (T7.getText().toString().equals(""))) {
            return T7;
        } else if ((T5.getText().toString().equals(T7.getText().toString())) && (T3.getText().toString().equals(""))) {
            return T3;
        } else if ((T5.getText().toString().equals(T4.getText().toString())) && (T6.getText().toString().equals(""))) {
            return T6;
        } else if ((T5.getText().toString().equals(T6.getText().toString())) && (T4.getText().toString().equals(""))) {
            return T4;
        } else if ((T5.getText().toString().equals(T2.getText().toString())) && (T8.getText().toString().equals(""))) {
            return T8;
        } else if ((T5.getText().toString().equals(T8.getText().toString())) && (T2.getText().toString().equals(""))) {
            return T2;
        } else {
            return null;
        }
    }

    @Nullable
    private TextView tSix() {
        if ((T6.getText().toString().equals(T5.getText().toString())) && (T4.getText().toString().equals(""))) {
            return T4;
        } else if ((T6.getText().toString().equals(T4.getText().toString())) && (T5.getText().toString().equals(""))) {
            return T5;
        } else if ((T6.getText().toString().equals(T3.getText().toString())) && (T9.getText().toString().equals(""))) {
            return T9;
        } else if ((T6.getText().toString().equals(T9.getText().toString())) && (T3.getText().toString().equals(""))) {
            return T3;
        } else {
            return null;
        }
    }

    @Nullable
    private TextView tSeven() {
        if ((T7.getText().toString().equals(T5.getText().toString())) && (T3.getText().toString().equals(""))) {
            return T3;
        } else if ((T7.getText().toString().equals(T3.getText().toString())) && (T5.getText().toString().equals(""))) {
            return T5;
        } else if ((T7.getText().toString().equals(T8.getText().toString())) && (T9.getText().toString().equals(""))) {
            return T9;
        } else if ((T7.getText().toString().equals(T9.getText().toString())) && (T8.getText().toString().equals(""))) {
            return T8;
        } else if ((T7.getText().toString().equals(T4.getText().toString())) && (T1.getText().toString().equals(""))) {
            return T1;
        } else if ((T7.getText().toString().equals(T1.getText().toString())) && (T4.getText().toString().equals(""))) {
            return T4;
        } else {
            return null;
        }
    }

    @Nullable
    private TextView tEight() {
        if ((T8.getText().toString().equals(T5.getText().toString())) && (T2.getText().toString().equals(""))) {
            return T2;
        } else if ((T8.getText().toString().equals(T9.getText().toString())) && (T7.getText().toString().equals(""))) {
            return T7;
        } else if ((T8.getText().toString().equals(T2.getText().toString())) && (T5.getText().toString().equals(""))) {
            return T5;
        } else if ((T8.getText().toString().equals(T7.getText().toString())) && (T9.getText().toString().equals(""))) {
            return T9;
        } else {
            return null;
        }
    }

    @Nullable
    private TextView tNine() {
        if ((T9.getText().toString().equals(T8.getText().toString())) && (T7.getText().toString().equals(""))) {
            return T7;
        } else if ((T9.getText().toString().equals(T7.getText().toString())) && (T8.getText().toString().equals(""))) {
            return T8;
        } else if ((T9.getText().toString().equals(T6.getText().toString())) && (T3.getText().toString().equals(""))) {
            return T3;
        } else if ((T9.getText().toString().equals(T3.getText().toString())) && (T6.getText().toString().equals(""))) {
            return T6;
        } else if ((T9.getText().toString().equals(T5.getText().toString())) && (T1.getText().toString().equals(""))) {
            return T1;
        } else if ((T9.getText().toString().equals(T1.getText().toString())) && (T5.getText().toString().equals(""))) {
            return T5;
        } else {
            return null;
        }
    }

    @Nullable
    private TextView callMethod(int x) {
        if (x == 0) {
            return tOne();
        } else if (x == 1) {
            return tTwo();
        } else if (x == 2) {
            return tThree();
        } else if (x == 3) {
            return tFour();
        } else if (x == 4) {
            return tFive();
        } else if (x == 5) {
            return tSix();
        } else if (x == 6) {
            return tSeven();
        } else if (x == 7) {
            return tEight();
        } else if (x == 8) {
            return tNine();
        } else {
            return null;
        }
    }

    private boolean isBoardFilledUp() {
        for (int n = 0; n < 9; n++) {
            if (isEmpty[n].equals("Yes")) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.come_out, R.anim.fade_out);
    }
}

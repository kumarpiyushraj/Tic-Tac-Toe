package com.example.tic_tac_toe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class NameSymbol extends AppCompatActivity {

    EditText et1, et2;
    boolean c, single_mode = false, isfreshlyStarted;
    View view;
    Intent i, j;
    TextView tx2, tx3, tx5, tx6;
    String[] sz = new String[4];
    RadioButton rb1, rb2, rb3, rb4, pvc, pvp;
    Button b1, b2;
    ViewFlipper ViewF;
    MediaPlayer music2, music3, music4;
    AlertDialog.Builder builder;
    InputMethodManager im;

    @Override
    protected void onStart() {
        super.onStart();

        if (isfreshlyStarted) {
            builder.setTitle("Message!");
            builder.setIcon(R.drawable.baseline_circle_notifications_24);
            builder.setCancelable(false);
            builder.setPositiveButton("Yes", (dialog, which) -> dialog.cancel());

            rb1.setOnClickListener(v -> {
                music2.start();
                hideKeyboard();

                c = rb1.isChecked();
                if (c) {
                    rb4.setChecked(true);
                    et2.clearFocus();
                    et1.clearFocus();
                    sz[0] = "X";
                    sz[1] = "O";
                }
            });
            rb2.setOnClickListener(v -> {
                music2.start();
                hideKeyboard();

                c = rb2.isChecked();
                if (c) {
                    rb3.setChecked(true);
                    et2.clearFocus();
                    et1.clearFocus();
                    sz[0] = "O";
                    sz[1] = "X";
                }
            });
            rb3.setOnClickListener(v -> {
                music2.start();
                hideKeyboard();

                c = rb3.isChecked();
                if (c) {
                    rb2.setChecked(true);
                    et2.clearFocus();
                    et1.clearFocus();
                    sz[0] = "O";
                    sz[1] = "X";
                }
            });
            rb4.setOnClickListener(v -> {
                music2.start();
                hideKeyboard();

                c = rb4.isChecked();
                if (c) {
                    rb1.setChecked(true);
                    et2.clearFocus();
                    et1.clearFocus();
                    sz[0] = "X";
                    sz[1] = "O";
                }
            });
            b1.setOnClickListener(v -> {
                music3.start();
                i = new Intent(NameSymbol.this, GameBoardSinglePlayer.class);
                j = new Intent(NameSymbol.this, GameBoard.class);
                i.putExtra("str_array", sz);
                j.putExtra("str_array", sz);

                if (rb1.isChecked() && rb4.isChecked()) {

                    if (et1.getText().toString().isEmpty() || et2.getText().toString().isEmpty()) {
                        builder.setMessage("Please enter your name.");
                        AlertDialog ad = builder.create();
                        music4.start();
                        ad.show();
                    } else {
                        sz[2] = et1.getText().toString();
                        sz[3] = et2.getText().toString();
                        et2.clearFocus();
                        et1.clearFocus();
                        if (single_mode) {
                            startActivity(i);
                        } else {
                            startActivity(j);
                        }
                    }
                } else if (rb2.isChecked() && rb3.isChecked()) {
                    if (et1.getText().toString().isEmpty() || et2.getText().toString().isEmpty()) {
                        builder.setMessage("Please enter your name.");
                        AlertDialog ad = builder.create();
                        music4.start();
                        ad.show();
                    } else {
                        sz[2] = et1.getText().toString();
                        sz[3] = et2.getText().toString();
                        et2.clearFocus();
                        et1.clearFocus();
                        if (single_mode) {
                            startActivity(i);
                        } else {
                            startActivity(j);
                        }
                    }
                } else {
                    if (et1.getText().toString().isEmpty() || et2.getText().toString().isEmpty()) {
                        builder.setMessage("Please choose your symbols and enter your name, too.");
                        AlertDialog ad = builder.create();
                        music4.start();
                        ad.show();
                    } else {
                        builder.setMessage("Please choose your symbols.");
                        AlertDialog ad = builder.create();
                        music4.start();
                        ad.show();
                    }
                }
            });

            pvc.setOnClickListener(v -> {
                music2.start();
                view = this.getCurrentFocus();
                if (view == null) view = new View(this);
                im.hideSoftInputFromWindow(view.getWindowToken(), 0);
            });

            pvp.setOnClickListener(v -> {
                music2.start();
                view = this.getCurrentFocus();
                if (view == null) view = new View(this);
                im.hideSoftInputFromWindow(view.getWindowToken(), 0);
            });

            b2.setOnClickListener(view -> {
                music3.start();
                if (pvc.isChecked()) {
                    tx5.setText(R.string.player);
                    tx2.setText(R.string.player);
                    tx6.setText(R.string.computer);
                    tx3.setText(R.string.computer);
                    et2.setPadding(0,0,0,0);
                    et1.setImeOptions(EditorInfo.IME_ACTION_DONE);
                    et1.setRawInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
                    et2.setText(R.string.computer);
                    et2.setEnabled(false);
                    single_mode = true;
                    ViewF.showNext();
                    b2.setEnabled(false);
                    pvc.setEnabled(false);
                    pvp.setEnabled(false);
                } else if (pvp.isChecked()) {
                    ViewF.showNext();
                    et1.setImeOptions(EditorInfo.IME_ACTION_NEXT);
                    et1.setRawInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
                    et2.setImeOptions(EditorInfo.IME_ACTION_DONE);
                    et2.setRawInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
                    b2.setEnabled(false);
                    pvc.setEnabled(false);
                    pvp.setEnabled(false);
                } else {
                    builder.setMessage("Please select one mode.");
                    AlertDialog ad = builder.create();
                    music4.start();
                    ad.show();
                }
            });
            isfreshlyStarted = false;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.name_symbol);

        ViewF = findViewById(R.id.selectOptions);
        tx5 = findViewById(R.id.tx5);
        tx6 = findViewById(R.id.tx6);
        tx2 = findViewById(R.id.tx2);
        tx3 = findViewById(R.id.tx3);

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);

        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        pvc = findViewById(R.id.rbpvc);
        pvp = findViewById(R.id.rbpvp);

        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.ContinueButton);

        music2 = MediaPlayer.create(this,R.raw.radiobutton);
        music3 = MediaPlayer.create(this,R.raw.button_click);
        music4 = MediaPlayer.create(this,R.raw.dialog_msg);

        builder = new AlertDialog.Builder(NameSymbol.this,R.style.AlertDialogTheme);

        im = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        isfreshlyStarted = true;
    }

    private void hideKeyboard(){
        view = this.getCurrentFocus();
        if (view == null) view = new View(this);
        im.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.come_out,R.anim.fade_out);
    }
}
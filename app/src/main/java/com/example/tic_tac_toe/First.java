package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
public class First extends AppCompatActivity {
        Button b1;
        ImageButton options;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_first);
                b1 = (Button) findViewById(R.id.B1);
                options = (ImageButton) findViewById(R.id.options);

                final MediaPlayer music = MediaPlayer.create(this,R.raw.button_click);
                final MediaPlayer music1 = MediaPlayer.create(this,R.raw.menu_sound);

            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.custom_toast,null);
            TextView toasttv = (TextView) layout.findViewById(R.id.toastText);
            toasttv.setText(R.string.toastMessage);
            Toast toast = new Toast(First.this);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);

            Context wrapper = new ContextThemeWrapper(this,R.style.BasePopupMenu);

                options.setOnClickListener(v -> {
                    music1.start();
                    options.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.popup_menu,null));
                        PopupMenu popupMenu = new PopupMenu(wrapper,options);
                        popupMenu.getMenuInflater().inflate(R.menu.popup_menu,popupMenu.getMenu());
                        popupMenu.setOnMenuItemClickListener(item -> {
                                switch (item.getItemId()) {
                                        case R.id.about:
                                                startActivity(new Intent(First.this,AboutActivity.class));
                                                return true;
                                        case R.id.share:
                                                toast.show();
                                                return true;

                                        default: return  First.super.onContextItemSelected(item);
                                }
                        });
                        popupMenu.show();

                        popupMenu.setOnDismissListener(popupMenu1 -> options.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.popup_menu_design,null)));
                });

                b1.setOnClickListener(v -> {
                    music.start();
                        startActivity(new Intent(First.this,NameSymbol.class));
                });
        }
}
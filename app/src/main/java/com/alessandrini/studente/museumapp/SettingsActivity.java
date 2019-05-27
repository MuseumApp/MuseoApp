package com.alessandrini.studente.museumapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Set;


public class SettingsActivity extends AppCompatActivity {

    private TextView opt1,opt2;
    private ImageView back;
    String URL = "https://play.google.com/store/apps/details?id=com.alessandrini.studente.museumapp&hl=it";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);



        opt1=findViewById(R.id.ShareApp);
        opt2=findViewById(R.id.ShowTutorial);
        back=findViewById(R.id.settingsback);

        opt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT,URL);
                intent.setType("text/plain");
                startActivity(Intent.createChooser(intent,"Scegli come condividere l'app"));
            }
        });

        opt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this,SliderActivity.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingsActivity.super.onBackPressed();
                finish();
            }
        });




    }

}

package com.example.studente.museumapp;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class ObjectActivity extends AppCompatActivity {
  private ImageView backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ogg);
        backButton = findViewById(R.id.backbuttonobj);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObjectActivity.super.onBackPressed();
            }
        });
    }

}



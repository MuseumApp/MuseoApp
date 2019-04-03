package com.example.studente.museumapp;

import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SalaActivity extends AppCompatActivity {

    private Risorse risorse;
    private int image;
    private int posizione;
    private String titolo;
    private String descrizione;
    private ImageView boximamgine;
    private TextView boxtitolo;
    private ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sala);
        setTitle("sala");
        risorse = new Risorse();
         posizione = getIntent().getIntExtra("posizione",0);
         image = risorse.getImmagine(posizione);
         titolo = risorse.getTitolo(posizione);
         back = findViewById(R.id.backbutton);
        // back.setColorFilter(Color.WHITE);


        boximamgine= findViewById(R.id.imageView4);
        boxtitolo =findViewById(R.id.titolosala);
        boximamgine.setImageDrawable(getDrawable(image));
        boxtitolo.setText(titolo);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SalaActivity.super.onBackPressed();
            }
        });




        Fade fade = new Fade();
        View decor = getWindow().getDecorView();
        getWindow().setEnterTransition(fade);
        getWindow().setExitTransition(fade);

    }

}

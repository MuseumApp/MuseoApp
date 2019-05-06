package com.example.studente.museumapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class ObjectActivity extends AppCompatActivity {
  private ImageView backButton;
  private Risorse risorse;
    private int image;
    private int posizione;
    private String titolo;
    private String descrizione;


    private ImageView imageView;
    private TextView titoloOgg;
    private TextView descOgg;

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

        setTitle("oggetto");
        risorse = new Risorse();
        posizione = getIntent().getIntExtra("position",0);
        image = risorse.getOggetto(posizione);
        titolo = risorse.getTitoliOggetti(posizione);
        descrizione = risorse.getDescrizioniOggetti(posizione);

        imageView=findViewById(R.id.row_wallet_redeem_img);
        imageView.setImageResource(image);

        titoloOgg = findViewById(R.id.textView3);
        titoloOgg.setText(titolo);

        descOgg = findViewById(R.id.textView2);
        descOgg.setText(descrizione);


        try
        {
            descrizione = risorse.getDescrizioneSala(posizione);

        }catch (Exception e )
        {

        }



        Fade fade = new Fade();
        View decor = getWindow().getDecorView();
        getWindow().setEnterTransition(fade);
        getWindow().setExitTransition(fade);


    }

}



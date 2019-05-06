package com.example.studente.museumapp;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class SalaActivity extends AppCompatActivity {

    private Risorse risorse;
    private int image;
    private int posizione;
    private String titolo;
    private String descrizione;
    private ImageView boximamgine;
    private TextView boxtitolo;
    private TextView descizioneTextView;
    private ImageView back;
    private ImageView objbutton;
    private LinearLayout descrizioneBox;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        objbutton.clearAnimation();
        objbutton.invalidate();

        objbutton.setVisibility(View.INVISIBLE);
        overridePendingTransition(R.anim.push_from_up, R.anim.pull_from_down);
    }

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
         descrizioneBox = findViewById(R.id.descrizione);

        // back.setColorFilter(Color.WHITE);
        objbutton = findViewById(R.id.buttonobject);
        try
        {
            descrizione = risorse.getDescrizioneSala(posizione);

            descizioneTextView = new TextView(getApplicationContext());




            descizioneTextView.setText(descrizione);

            descrizioneBox.addView(descizioneTextView);
        }catch (Exception e )
        {

        }




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


        Animation pulse = AnimationUtils.loadAnimation(this, R.anim.pulse);
        objbutton.startAnimation(pulse);

        objbutton.setColorFilter(R.color.colorAccent);
        objbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SalaActivity.this, ObjectActivity.class);
                intent.putExtra("position", posizione);
                startActivity(intent);
                overridePendingTransition(R.anim.pull_from_down, R.anim.push_from_up);

            }
        });


        Fade fade = new Fade();
        View decor = getWindow().getDecorView();
        getWindow().setEnterTransition(fade);
        getWindow().setExitTransition(fade);

    }

}

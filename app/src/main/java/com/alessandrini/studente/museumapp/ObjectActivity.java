package com.alessandrini.studente.museumapp;


import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.transition.Fade;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ceylonlabs.imageviewpopup.ImagePopup;
import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;


public class ObjectActivity extends AppCompatActivity {
  private ImageView backButton;
  private Risorse risorse;
    private int image;
    private int posizione;
    private String titolo;
    private String descrizione;
    private LinearLayout linearLayout;

    private PhotoView imageView;
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
        linearLayout = findViewById(R.id.descrizioneobj);
        titolo = risorse.getTitoliOggetti(posizione);
        descrizione = risorse.getDescrizioniOggetti(posizione);

        imageView=findViewById(R.id.objsfondo);
        titoloOgg = findViewById(R.id.textView3);
        titoloOgg.setText(titolo);

        descOgg = findViewById(R.id.textView2);

        descOgg = new TextView(getApplicationContext());



        descOgg.setTextSize(20);
        descOgg.setText(descrizione);
        descOgg.setTextColor(Color.parseColor("#696969"));
        descOgg.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        linearLayout.addView(descOgg);
        try {
            Picasso.get().load(image).fit().centerCrop().placeholder(R.drawable.logo_museo_ridotto).into(imageView);
        }
        catch (Exception e)
        {
            imageView.setImageResource(R.drawable.logo_museo_ridotto);
        }






        try
        {
            descrizione = risorse.getDescrizioneSala(posizione);

        }catch (Exception e )
        {
                ///ciaooo
        }



        Fade fade = new Fade();
        View decor = getWindow().getDecorView();
        getWindow().setEnterTransition(fade);
        getWindow().setExitTransition(fade);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

                final ImagePopup imagePopup = new ImagePopup(this);
                imagePopup.setWindowHeight(height); // Optional
                imagePopup.setWindowWidth(width); // Optional
                imagePopup.setBackgroundColor(Color.BLACK);  // Optional
                //imagePopup.setFullScreen(true); // Optional
                imagePopup.setHideCloseIcon(false);  // Optional
                imagePopup.setImageOnClickClose(true);  // Optional




                imagePopup.initiatePopup(getDrawable(image));

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        /** Initiate Popup view **/
                        try {
                            imagePopup.viewPopup();
                        }
                        catch (Exception e)
                        {

                        }


                    }
                });


            }





}



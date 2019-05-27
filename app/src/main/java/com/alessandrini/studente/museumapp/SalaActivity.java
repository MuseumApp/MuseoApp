package com.alessandrini.studente.museumapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.transition.Fade;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ceylonlabs.imageviewpopup.ImagePopup;
import com.github.chrisbanes.photoview.PhotoView;

public class SalaActivity extends AppCompatActivity {

    private Risorse risorse;
    private int image;
    private int posizione;
    private String titolo;
    private String descrizione;
    private PhotoView boximamgine;
    private TextView boxtitolo;
    private TextView descizioneTextView;
    private ImageView back;
    private ImageView objbutton;
    private LinearLayout descrizioneBox;
    private TextView button2;
    private String link;
    private TextView openLink1;
    private ImageView openLink2;
    private ConstraintLayout root;
    private View view;
    private LayoutInflater Inflater;
    private String POSITION = "stanza" ;
    private WebView webView;
    private Boolean alreadyOpened;


    @Override
    public void onBackPressed() {
        if(POSITION.equals("stanza"))
        {

            objbutton.clearAnimation();
            openLink2.clearAnimation();
            openLink2.invalidate();
            objbutton.invalidate();
            super.onBackPressed();

            //objbutton.setVisibility(View.INVISIBLE);
            if(!alreadyOpened)
                overridePendingTransition(R.anim.push_from_up, R.anim.pull_from_down);
        }
        else
        {

          SalaActivity.this.recreate();

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sala);
        setTitle("sala");
        CreaStanza();


    }
    public void openObj()
    {
        Intent intent = new Intent(SalaActivity.this, ObjectActivity.class);
        intent.putExtra("position", posizione);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_from_down, R.anim.push_from_up);
    }
    public  void ChangeLayout(int layout , String position)
    {
        view = Inflater.inflate(layout, root, false);
        root.removeAllViews();
        root.addView(view);
        POSITION = position;

    }
    private void openLink()
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("apriurl", link);

        intent.putExtra("position",posizione);
        startActivity(intent);
    }
    private void CreaStanza()
    {
        risorse = new Risorse();
        posizione = getIntent().getIntExtra("posizione",0);
        alreadyOpened = getIntent().getBooleanExtra("opened",false);
        System.out.println("bbbb"+posizione);
        image = risorse.getImmagine(posizione);
        titolo = risorse.getTitolo(posizione);
        back = findViewById(R.id.backbutton);
        descrizioneBox = findViewById(R.id.descrizione);
        button2 = findViewById(R.id.buottonobj2);
        openLink1 = findViewById(R.id.openLink);
        root=findViewById(R.id.root);
        openLink2 = findViewById(R.id.link2);
        Inflater = (LayoutInflater) getApplicationContext().getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);


        // back.setColorFilter(Color.WHITE);
        objbutton = findViewById(R.id.buttonobject);
        try
        {
            descrizione = risorse.getDescrizioneSala(posizione);

            descizioneTextView = new TextView(getApplicationContext());



            descizioneTextView.setTextSize(20);
            descizioneTextView.setText(descrizione);
            descizioneTextView.setTextColor(Color.parseColor("#696969"));
            descizioneTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            descrizioneBox.addView(descizioneTextView);
        }catch (Exception e )
        {

        }

        link = risorse.getLinkSale(posizione);

        openLink1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLink();

            }
        });
        openLink2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLink();
            }
        });






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
        objbutton.setColorFilter(R.color.colorAccent);
        objbutton.startAnimation(pulse);
        openLink2.startAnimation(pulse);

        objbutton.setColorFilter(R.color.colorAccent);
        objbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openObj();

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openObj();
            }
        });

        Fade fade = new Fade();
        View decor = getWindow().getDecorView();
        getWindow().setEnterTransition(fade);
        getWindow().setExitTransition(fade);

        int width = boximamgine.getDrawable().getIntrinsicWidth();
        int height = boximamgine.getDrawable().getIntrinsicHeight();

        final ImagePopup imagePopup = new ImagePopup(this);
        //imagePopup.setWindowHeight(height); // Optional
       // imagePopup.setWindowWidth(width); // Optional
        imagePopup.setBackgroundColor(Color.BLACK);  // Optional
        imagePopup.setFullScreen(true); // Optional
        imagePopup.setHideCloseIcon(false);  // Optional
        imagePopup.setImageOnClickClose(true);
        // Optional


        Drawable  drawable = getDrawable(image);
        imagePopup.initiatePopup(drawable);
        //imagePopup.set

        boximamgine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /** Initiate Popup view **/

                imagePopup.viewPopup();

            }
        });






    }

}

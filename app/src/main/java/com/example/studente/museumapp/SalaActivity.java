package com.example.studente.museumapp;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;

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
    private TextView button2;
    private String link;
    private TextView openLink1;
    private ImageView openLink2;
    private ConstraintLayout root;
    private View view;
    private LayoutInflater Inflater;
    private String POSITION = "stanza" ;
    private WebView webView;


    @Override
    public void onBackPressed() {
        if(POSITION.equals("stanza"))
        {
            super.onBackPressed();
            objbutton.clearAnimation();
            objbutton.invalidate();

            objbutton.setVisibility(View.INVISIBLE);
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
        objbutton.clearAnimation();
        ChangeLayout(R.layout.web_view,"sito");
        webView =view.findViewById(R.id.webView);
        webView.loadUrl(link);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return false;
            }
        });
    }
    private void CreaStanza()
    {
        risorse = new Risorse();
        posizione = getIntent().getIntExtra("posizione",0);
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
        objbutton.startAnimation(pulse);

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


    }

}

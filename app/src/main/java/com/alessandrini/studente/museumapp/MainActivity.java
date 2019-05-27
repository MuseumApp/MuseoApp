package com.alessandrini.studente.museumapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.renderscript.ScriptIntrinsicLUT;
import android.transition.Fade;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
     BottomAppBar bottomAppBar;
    private FloatingActionButton floatingActionButton;
    public LinearLayout container;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private Risorse risorse;
    private final int VERTICAL_ITEM_SPACE = 30;
    public  LayoutInflater inflater;
    ProgressDialog progress;
    public  View view;
    private float   width , height;
    public String POSITION = "homepage";
    private int BOTTOM_HEIGHT = 0;
    private ConstraintLayout constraintLayout;
    private LinearLayout stanzaHeader;
    private LinearLayout headerDrawer;
    public WebView webView;
    private LinearLayout abruzzoImage;
    private String urlSito = "http://www.gentidabruzzo.com/";
    private String urlAles = "http://italessandrini.edu.it/";
    private View.OnClickListener mapscClickListener,museoclickListener,alesCLickListener;
    private LinearLayout sitoAlesButt,sitoMuseoButt;
    private double latitude = 42.461655;
    private double longitude = 14.212369;
    private int POSIZIONELINK;
    private NestedScrollView scrollView;
    private TextView deschome;
    private LinearLayout ccdesc;
    private ImageView homebg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Museo");
        setContentView(R.layout.activity_main);
        container = findViewById(R.id.container);
        Fade fade = new Fade();
        getWindow().setEnterTransition(fade);
        getWindow().setExitTransition(fade);
        inflater = (LayoutInflater) getApplicationContext().getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);

        floatingActionButton =  findViewById(R.id.fab);
        bottomAppBar = findViewById(R.id.bar);

         ChangeLayout(R.layout.homepage,"homepage");
         mapscClickListener =new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                //startActivity(intent);
                String uri = String.format(Locale.ENGLISH, "geo:%f,%f", latitude, longitude,18.8f);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);

            }
        };
         museoclickListener = new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 apriSito(urlSito,100);
             }
         };
         alesCLickListener = new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 apriSito(urlAles,100);
             }
         };



        container = findViewById(R.id.container);

        bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);
        risorse= new Risorse();
        final NavigationView navigation =  findViewById(R.id.nav_view);
        final DrawerLayout drawer = findViewById(R.id.drawer_layouty);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                container = findViewById(R.id.container);
                switch (menuItem.getItemId()) // per ogni item del drawer
                {
                    case (R.id.news):
                        ChangeLayout(R.layout.activity_news,"news");
                        creaNews();
                        break;
                    case (R.id.stanze):
                        ChangeLayout(R.layout.activity_sale,"stanze");
                        creaSale();

                        break;
                    case (R.id.sito):
                        bottomAppBar.setVisibility(View.INVISIBLE);
                        apriSito(urlSito,100);

                        break;
                    case (R.id.homeDrawer):
                       ChangeLayout(R.layout.homepage , "homepage");
                       resetBottomBar();
                        resetHomepage();
                        break;
                    case (R.id.Itemcrediti):

                        ChangeLayout(R.layout.credits , "credits");
                        break;
                        
                    case (R.id.impostazioni):
                        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                        startActivity(intent);
                        //ChangeLayout(R.layout.settings,"settings");
                        break;
                    case (R.id.qr_main):

                        apriQr();
                        POSITION= "qr";

                }

                drawer.closeDrawer(GravityCompat.START);
                if(!POSITION.equals("qr") && !POSITION.equals("sito"))
                 resetBottomBar();
                return true;
            }
        });







        setSupportActionBar(bottomAppBar);


        bottomAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
            }
        });
        bottomAppBar.setHideOnScroll(true);
        bottomAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.qr:
                        apriQr();
                        POSITION= "qr";
                        break;
                }

                return false;
            }
        });

        ViewTreeObserver vto = bottomAppBar.getViewTreeObserver();
        vto.addOnGlobalLayoutListener (new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                bottomAppBar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                 width  = bottomAppBar.getLeft();
                 height = bottomAppBar.getTop();
                 BOTTOM_HEIGHT = bottomAppBar.getHeight();


            }
        });
        resetHomepage();
        ViewTreeObserver vto2 = ccdesc.getViewTreeObserver();
        vto.addOnGlobalLayoutListener (new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                checkScroll();

            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(POSITION.equals("homepage"))
               {
                   drawer.openDrawer(GravityCompat.START);
               }
                ChangeLayout(R.layout.homepage,"homepage");

                resetBottomBar();
                resetHomepage();
            }
        });
        floatingActionButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
              //  bottomAppBar.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                if(!POSITION.equals("sito") && !POSITION.equals("sitolinkstanza") && !POSITION.equals("newsopened"))
                    floatingActionButton.hide();
               // bottomAppBar.setBackgroundTint(myList);
                return false;
            }
        });

        bottomAppBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //bottomAppBar.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                floatingActionButton.show();
                //bottomAppBar.setBackgroundTint(myList2);

               // bottomAppBar.setFabCradleMargin(50);
                //bottomAppBar.setFabCradleRoundedCornerRadius(5);


            }
        });



        String prova = "No";
         prova = getIntent().getStringExtra("apriurl");
         int position = getIntent().getIntExtra("position",0);
         System.out.println("ppp>"+prova);

        if(prova !=null)
        {
            if(!prova.equals("No")) {
                apriSito(prova, position);

                bottomAppBar.setVisibility(View.INVISIBLE);
                POSIZIONELINK = position;
            }

        }


    }
    private void apriSito(String url,int position)
    {

        if(position  == 100 )
        {
            ChangeLayout(R.layout.web_view,"sito");
        }
        else
        {
            ChangeLayout(R.layout.web_view,"sitolinkstanza");
        }

        webView =view.findViewById(R.id.webView);
        webView.loadUrl(url);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;

    }

    public void creaNews() {
        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        URL url = null;
        progress = new ProgressDialog(this);
        progress.setTitle("News");
        progress.setMessage("Download news");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
        try {
            url = new URL("http://www.gentidabruzzo.com/?feed=rss2");
            new DownloadNews(MainActivity.this).execute(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }

    public void caricaNews(ArrayList<String[]> news) {
        adapter = new NewsAdapter(news, MainActivity.this);
        recyclerView.setAdapter(adapter);
        if(progress != null)   progress.dismiss();

        if(news ==null){
            ChangeLayout(R.layout.errore,"errore");
        }
    }
    public void creaSale() {
        String[] titoli = risorse.getTitoli();
        int images [] = risorse.getImmagini();
        recyclerView = view.findViewById(R.id.containerSale);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
        adapter = new SaleAdapter(images,titoli, MainActivity.this);
        recyclerView.setAdapter(adapter);

    }
    private void apriQr() {
        bottomAppBar.setVisibility(View.INVISIBLE);
        floatingActionButton.show();
        QR_manager qr_manager = new QR_manager(container,getApplicationContext(),this);
        qr_manager.displayCamera();

    }
    @Override
    public void onBackPressed() {



       System.out.println("position > " + POSITION);
        if(POSITION.equals("qr"))
        {
            ChangeLayout(R.layout.homepage,"homepage");
            resetBottomBar();
            resetHomepage();
        }
        else if(POSITION.equals("newsopened"))
        {
            if(webView.canGoBack()) {
                webView.goBack();
            }
            else {
                ChangeLayout(R.layout.activity_news, "news");
                creaNews();
            }
        }
        else if(POSITION.equals("sito"))
        {
            if(webView.canGoBack()) {
                webView.goBack();
            }
            else {
                ChangeLayout(R.layout.homepage, "homepage");
                resetBottomBar();
                resetHomepage();

            }
        }
        else if(POSITION.equals("homepage"))
        {
            new AlertDialog.Builder(this)
                    .setIcon(R.drawable.logo_museo_ridotto)
                    .setTitle("Esci")
                    .setMessage("Sei sicuro di voler uscire dall'app?")
                    .setPositiveButton("Si", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }

                    })
                    .setNegativeButton("Cancella", null)
                    .show();
        }
        else if(POSITION.equals("sitolinkstanza"))
        {
            Intent intent = new Intent(this, SalaActivity.class);
            intent.putExtra("posizione",POSIZIONELINK);
            intent.putExtra("opened",true);
            startActivity(intent);
            ChangeLayout(R.layout.activity_sale,"stanze");
            creaSale();
        }
        else
        {
            ChangeLayout(R.layout.homepage, "homepage");
            resetBottomBar();
            resetHomepage();
        }
        //if(POSITION.equals("stanze"))
       //{
       //     ChangeLayout(R.layout.homepage, "homepage");
       //     resetBottomBar();
       //     resetHomepage();
       // }





    }
    public void resetBottomBar()
    {
       // bottomAppBar.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
       bottomAppBar.setX(width);
       bottomAppBar.setY(height);
       bottomAppBar.setVisibility(View.VISIBLE);



    }
    public  void ChangeLayout(int layout , String position)
    {
        view = inflater.inflate(layout, container, false);
        container.removeAllViews();
        container.addView(view);
        POSITION = position;
        if(!POSITION.equals("homepage"))
             floatingActionButton.show();
        else
            floatingActionButton.hide();

    }
    public void resetHomepage()
    {
        abruzzoImage = view.findViewById(R.id.mapsbutton);
        sitoAlesButt = view.findViewById(R.id.alessbutt);
        sitoMuseoButt = view.findViewById(R.id.musobutt);
        scrollView = view.findViewById(R.id.scrollhome);
        deschome = view.findViewById(R.id.deschome);
        abruzzoImage.setOnClickListener(mapscClickListener);
        sitoAlesButt.setOnClickListener(alesCLickListener);
        sitoMuseoButt.setOnClickListener(museoclickListener);
        ccdesc = view.findViewById(R.id.ccdesc);
        floatingActionButton.hide();
        homebg =view.findViewById(R.id.abruzzoimage);
        Picasso.get().load(R.drawable.homeimage).fit().into(homebg);


    }

    public void checkScroll()
    {
        int counter = 18;


        try {
            ccdesc = view.findViewById(R.id.ccdesc);
            scrollView = view.findViewById(R.id.scrollhome);
            deschome = view.findViewById(R.id.deschome);
            int childHeight = ccdesc.getHeight();
            boolean isScrollable = scrollView.getHeight() < childHeight + scrollView.getPaddingTop() + scrollView.getPaddingBottom();
            System.out.println("scoll>" + isScrollable + " h >" + childHeight + " sh >" + scrollView.getHeight() +
                    " somma> " + childHeight + scrollView.getPaddingTop() + scrollView.getPaddingBottom());
            System.out.println("h>>" + deschome.getTextSize());

            if (!isScrollable) {
                deschome.setTextSize(counter + 2);
                deschome.setGravity(Gravity.CENTER);
                ccdesc.removeAllViews();
                ccdesc.addView(deschome);

            }
        }
        catch (Exception e)
        {



        }





    }
}

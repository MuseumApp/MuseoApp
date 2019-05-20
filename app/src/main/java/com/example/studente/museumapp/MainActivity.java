package com.example.studente.museumapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    public static final int ANIMATION_SPEED = 3000;
    private ConstraintLayout rootView;
    private View fromView, toView, shuttleView;
    private BottomAppBar bottomAppBar;
    private FloatingActionButton floatingActionButton;
    public LinearLayout container;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private Risorse risorse;
    private final int VERTICAL_ITEM_SPACE = 30;
    private ViewPager viewPager;
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
         ChangeLayout(R.layout.homepage,"homepage");
         mapscClickListener =new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("maps va");
                //Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                //startActivity(intent);
                String uri = String.format(Locale.ENGLISH, "geo:%f,%f", latitude, longitude,17.5f);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);

            }
        };
         museoclickListener = new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 apriSito(urlSito);
             }
         };
         alesCLickListener = new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 apriSito(urlAles);
             }
         };
        bottomAppBar = findViewById(R.id.bar);
        resetHomepage();
        container = findViewById(R.id.container);
        floatingActionButton =  findViewById(R.id.fab);
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
                        apriSito(urlSito);

                        break;
                    case (R.id.homeDrawer):
                       ChangeLayout(R.layout.homepage , "homepage");
                       resetBottomBar();
                        resetHomepage();
                        break;
                    case (R.id.Itemcrediti):

                        ChangeLayout(R.layout.credits , "credits");
                        break;
                    case (R.id.qr_main):
                        apriQr();
                        POSITION= "qr";

                }

                drawer.closeDrawer(GravityCompat.START);
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




    }
    private void apriSito(String url)
    {
        ChangeLayout(R.layout.web_view,"sito");
        webView =view.findViewById(R.id.webView);
        webView.loadUrl(url);
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
        constraintLayout = view.findViewById(R.id.newscontainer);
        constraintLayout.setMaxHeight(constraintLayout.getMaxHeight()-BOTTOM_HEIGHT);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        URL url = null;
        try {
            url = new URL("http://www.gentidabruzzo.com/?feed=rss2");
            new DownloadNews(MainActivity.this).execute(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
         progress = new ProgressDialog(this);
        progress.setTitle("new");
        progress.setMessage("Caricamento news");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

    }

    public void caricaNews(ArrayList<String[]> news) {
        adapter = new NewsAdapter(news, MainActivity.this);
        recyclerView.setAdapter(adapter);
        progress.dismiss();
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
        QR_manager qr_manager = new QR_manager(container,getApplicationContext(),this);
        qr_manager.displayCamera();
    }
    @Override
    public void onBackPressed() {
        System.out.println("position > " + POSITION);
        if(POSITION.equals("qr"))
        {
            ChangeLayout(R.layout.homepage,"homepage");
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
        else
        {
            new AlertDialog.Builder(this)
                    .setIcon(R.drawable.logo_museo_completo)
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








    }
    public void resetBottomBar()
    {
       bottomAppBar.setX(width);
       bottomAppBar.setY(height);

    }
    public  void ChangeLayout(int layout , String position)
    {
        view = inflater.inflate(layout, container, false);
        container.removeAllViews();
        container.addView(view);
        POSITION = position;

    }
    public void resetHomepage()
    {
        abruzzoImage = view.findViewById(R.id.mapsbutton);
        sitoAlesButt = findViewById(R.id.alessbutt);
        sitoMuseoButt = findViewById(R.id.musobutt);
        abruzzoImage.setOnClickListener(mapscClickListener);
        sitoAlesButt.setOnClickListener(alesCLickListener);
        sitoMuseoButt.setOnClickListener(museoclickListener);
    }

}

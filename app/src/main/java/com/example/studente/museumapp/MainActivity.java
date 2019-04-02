package com.example.studente.museumapp;

import android.app.Activity;
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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.example.studente.museumapp.adapter.NewsAdapter;
import com.example.studente.museumapp.adapter.SaleAdapter;

import com.example.studente.museumapp.asyncTasks.DownloadNews;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


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
    private final int VERTICAL_ITEM_SPACE = 90;
    private ViewPager viewPager;
   private  LayoutInflater inflater;
    ProgressDialog progress;
    private View view;
    private float   width , height;
    private String POSITION = "homepage";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("ciaoooo");
        setContentView(R.layout.activity_main);
        container = findViewById(R.id.container);
        Fade fade = new Fade();
        getWindow().setEnterTransition(fade);
        getWindow().setExitTransition(fade);
        inflater = (LayoutInflater) getApplicationContext().getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
         view = inflater.inflate(R.layout.homepage, container, false);
        container.removeAllViews();
        container.addView(view);


        bottomAppBar = findViewById(R.id.bar);


        container = findViewById(R.id.container);
        floatingActionButton =  findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (bottomAppBar.getFabAlignmentMode() == BottomAppBar.FAB_ALIGNMENT_MODE_CENTER) {
                    bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);
                } else {
                    bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
                }





            }
        });






        risorse= new Risorse();

        final NavigationView navigation =  findViewById(R.id.nav_view);
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layouty);

        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {



                container = findViewById(R.id.container);
                switch (menuItem.getItemId()) // per ogni item del drawer
                {
                    case (R.id.news):
                        System.out.println("news--------------------------");
                        view = inflater.inflate(R.layout.activity_news, container, false);
                        creaNews(view);
                        container.removeAllViews();
                        container.addView(view);
                        POSITION = "news";
                        break;
                    case (R.id.stanze):
                        view = inflater.inflate(R.layout.activity_sale, container, false);
                        creaSale( risorse.getTitoli(),risorse.immagini,view );
                        container.removeAllViews();
                        container.addView(view);
                        POSITION = "stanze";
                        break;
                    case (R.id.sito):
                        view = inflater.inflate(R.layout.homepage, container, false);
                        container.removeAllViews();
                        container.addView(view);
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.gentidabruzzo.com/"));
                        startActivity(browserIntent);
                        break;
                }

                drawer.closeDrawer(GravityCompat.START);
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
                    case R.id.action_settings:
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
                 System.out.println("x > "+width + "   y> " + height);

            }
        });


        floatingActionButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                view = inflater.inflate(R.layout.homepage, container, false);
                container.removeAllViews();
                container.addView(view);
                bottomAppBar.setX(width);
                bottomAppBar.setY(height);



                return true;
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;

    }

    public void creaNews(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
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
    public void creaSale(String[] titoli , int[] images,View view) {
        recyclerView = view.findViewById(R.id.containerSale);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
        adapter = new SaleAdapter(images,titoli, MainActivity.this);
        recyclerView.setAdapter(adapter);
    }
    private void apriQr() {
        Activity activity = (Activity) this;
        QR_manager qr_manager = new QR_manager(container,getApplicationContext(),this);
        qr_manager.displayCamera();
    }
    @Override
    public void onBackPressed() {
        if(POSITION.equals("qr"))
        {

            System.out.println("--------------------------------------");
            view = inflater.inflate(R.layout.homepage, container, false);
            container.removeAllViews();
            container.addView(view);
        }
        else
        {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
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





}

package com.alessandrini.studente.museumapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;


public class SplashActivity extends AppCompatActivity {
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final IntroManager introManager =new IntroManager(this);

        handler.postDelayed(new Runnable() {

            @Override
            public void run() {



                try {


                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    //if(introManager.check()) //per aprirlo solo la prima volta
                   // {
                     //   introManager.setFirstOpen(false);
                        intent = new Intent(getApplicationContext(), SliderActivity.class);
                  //  }


                    startActivity(intent);


                    finish();
                } catch (Exception ignored) {
                    ignored.printStackTrace();
                }
            }
        }, 3000);  // Give a 5 seconds delay.
    }
    @Override
    public void onDestroy() {
        super.onDestroy();

        //Remove all the callbacks otherwise navigation will execute even after activity is killed or closed.
        handler.removeCallbacksAndMessages(null);
    }
    }

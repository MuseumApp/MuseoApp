package com.example.studente.museumapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SliderActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private  Slider slider;
    private final int npunti = 3;
    private TextView[] punti;
    private LinearLayout linearLayout;
    private ImageButton b1,b2;
    private int posizione ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);
        viewPager = findViewById(R.id.viewpager);
        linearLayout = findViewById(R.id.LayoutPunti);
        slider = new Slider(this);
        viewPager.setAdapter(slider);
        b1 = findViewById(R.id.button2);
        b2 = findViewById(R.id.button3);
        b1.setVisibility(View.INVISIBLE);
        b1.setBackgroundResource(R.drawable.buttonstyle);
        b2.setBackgroundResource(R.drawable.buttonstyle);

        AddPunti(0);
        posizione = 0;

        viewPager.addOnPageChangeListener(viewlistener);

        b2.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                //Create the intent to start another activity
                if(posizione == npunti -1)
                {
                    Intent intent = new Intent(view.getContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{

                    viewPager.setCurrentItem(posizione + 1);
                }

            }
        });
        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                //Create the intent to start another activity

                    viewPager.setCurrentItem(posizione - 1);


            }
        });
    }

    public void AddPunti(int pos)
    {
        punti = new TextView[npunti];
        linearLayout.removeAllViews();
        for(int i=0;i<npunti;i++)
        {

            punti[i] = new TextView(this);
            punti[i].setText(Html.fromHtml("&#8226;"));
            punti[i].setTextSize(35);
            punti[i].setTextColor (getResources().getColor(R.color.colorPrimary));
            punti[i].setGravity(Gravity.CENTER);

            linearLayout.addView(punti[i]);
        }
        if (npunti > 0 )
        {
            punti[pos].setTextColor (getResources().getColor(R.color.colorPrimary));
        }


    }


    ViewPager.OnPageChangeListener viewlistener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {
        }

        @Override
        public void onPageSelected(int i) {
            AddPunti(i);
            posizione = i;
            if (posizione == 0 )
            {
                b1.setVisibility(View.INVISIBLE);
            }
            else if( posizione == npunti - 1)
            {
                b2.setImageResource(R.drawable.ic_approve_circular_button);
            }
            else
            {
                b1.setVisibility(View.VISIBLE);
                b2.setImageResource(R.drawable.ic_right_arrow_circular_button);
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {
        }
    };
}

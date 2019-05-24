package com.alessandrini.studente.museumapp;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class Slider extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;
    ConstraintLayout layout;
    TextView textView;

    public int[] Immagini = { //immagini - icone
            R.drawable.tutorial_home,
            R.drawable.tutorial_mappa,
            R.drawable.tutorial_qr
    };
   public String[] descrizione = {
           "Homepage sempre a portata di mano",
           "Vuoi sapere dove si trova il museo ? Utilizza la funzione integrata nell'app!",
           "Inquadrando il codice QR che trovi nel museo potrai accedere rapidamente alla sua stanza di riferimento"
   };

   // descrizioni e sfondi
    public int[] Backgrounds ={R.color.tutorialhome,R.color.tutorialmappa,R.color.tutorialqr};


    public Slider(Context context)
    {
        this.context=context;
    }


    @Override
    public int getCount() {
        return Immagini.length;

    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (ConstraintLayout) o;
    }



    @Override
    public Object instantiateItem(ViewGroup container , int position)
    {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider,container,false);
        ImageView imageView = view.findViewById(R.id.Imageview1);
        layout = view.findViewById(R.id.relativeLayout);
        layout.setBackgroundResource(Backgrounds[position]);
        textView = view.findViewById(R.id.textView6);
        textView.setText(descrizione[position]);

        Picasso.get().load(Immagini[position]).placeholder(Backgrounds[position]).into(imageView);
        container.addView(view);
        return view;
    }
    @Override
    public void destroyItem(ViewGroup container , int position , Object o)
    {
        container.removeView((ConstraintLayout)o);
    }
}

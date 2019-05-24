package com.alessandrini.studente.museumapp;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class SaleAdapter extends RecyclerView.Adapter<SaleAdapter.MyViewHolder>{


        private int[] images;
        private String [] titoli;

        private MainActivity mainActivity;


        public static class  MyViewHolder extends RecyclerView.ViewHolder {

            public ImageView sfondo;
            public TextView titolo;
            public View v;
            public CardView cardView;

            public MyViewHolder(View v) {
                super(v);
                this.v = v;
                sfondo = v.findViewById(R.id.ImmagineSala);
                titolo = v.findViewById(R.id.TitoloSala);
                cardView = v.findViewById(R.id.salaelement);
            }
        }


        public SaleAdapter(int[] images,String[] titoli, MainActivity mainActivity) {
            this.images = images;
            this.titoli = titoli;
            this.mainActivity = mainActivity;
        }


        @Override
        public SaleAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.sala_element, parent, false);
            SaleAdapter.MyViewHolder vh = new SaleAdapter.MyViewHolder(v);
            return vh;
        }


        @Override
        public void onBindViewHolder(SaleAdapter.MyViewHolder holder, final int position) {

            DisplayMetrics displayMetrics = new DisplayMetrics();
            mainActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int height = displayMetrics.heightPixels;
            int width = displayMetrics.widthPixels;
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) holder.cardView.getLayoutParams();
            params.topMargin = (height/100)*4;
            params.width=(width/100)*96;
            params.height=(int)(height/3.5);
            holder.cardView.setLayoutParams(params);

            Picasso.get().load(images[position]).fit().centerInside().placeholder(R.drawable.logo_museo_ridotto).into(holder.sfondo);



            holder.sfondo.setTransitionName("prova");
            holder.titolo.setText(titoli[position]);
            final ImageView imagine = holder.sfondo;
            System.out.println(imagine.toString());
            holder.v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // apri scheda sala
                    Intent intent = new Intent(mainActivity, SalaActivity.class);
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(mainActivity ,imagine, ViewCompat.getTransitionName(imagine));
                    intent.putExtra("posizione",position);
                    mainActivity.startActivity(intent, options.toBundle());

                }
            });
        }


        @Override
        public int getItemCount() {
            return images.length;
        }
    }


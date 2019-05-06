package com.example.studente.museumapp.adapter;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.studente.museumapp.MainActivity;
import com.example.studente.museumapp.R;
import com.example.studente.museumapp.SalaActivity;


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


            holder.sfondo.setImageResource(images[position]);
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


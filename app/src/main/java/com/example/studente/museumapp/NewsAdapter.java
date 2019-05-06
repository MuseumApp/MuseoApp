package com.example.studente.museumapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {
    private ArrayList<String[]> mDataset;
    private MainActivity mainActivity;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public TextView textView2;
        public ImageView pallaaa;
        public View v;
        public MyViewHolder(View v) {
            super(v);
            this.v=v;
            textView = v.findViewById(R.id.newsTitle);
            textView2 = v.findViewById(R.id.newsData);
            pallaaa = v.findViewById(R.id.palla);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public NewsAdapter(ArrayList<String[]> myDataset, MainActivity mainActivity) {
        mDataset = myDataset;
        this.mainActivity=mainActivity;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public NewsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.newselement, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.textView.setText(mDataset.get(position)[0]);
        holder.textView2.setText(mDataset.get(position)[2].substring(0,17));
        holder.pallaaa.setImageResource(R.drawable.button_pressed);
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.POSITION = "newsopened";
                view = mainActivity.inflater.inflate(R.layout.web_view, mainActivity.container, false);
                mainActivity.container.removeAllViews();
                mainActivity.container.addView(view);
                mainActivity.webView =view.findViewById(R.id.webView);
                mainActivity.webView.loadUrl(mDataset.get(position)[1]);
                mainActivity.webView.getSettings().setDomStorageEnabled(true);
                mainActivity.webView.getSettings().setJavaScriptEnabled(true);
                mainActivity.webView.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                        view.loadUrl(request.getUrl().toString());
                        return false;
                    }
                });
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
package com.alessandrini.studente.museumapp;

import android.content.Context;
import android.content.SharedPreferences;

public class IntroManager {

    private Context context;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public IntroManager(Context context)
    {
        this.context = context;
        preferences=context.getSharedPreferences("FirstOpen",0);
        editor = preferences.edit();
    }


    public void setFirstOpen(Boolean firstOpen)
    {
        editor.putBoolean("FirstOpen",firstOpen );
        editor.commit();
    }


    public Boolean check()
    {
        return preferences.getBoolean("FirstOpen",true);
    }

}

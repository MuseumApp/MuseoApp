package com.example.studente.museumapp;

import android.media.Image;

public class Risorse {

    int numero_stanze = 13;

    int []  immagini = { // immagini delle sale
            R.drawable.sala1,
            R.drawable.sala2,
            R.drawable.sala3,
            R.drawable.sala4,
            R.drawable.sala5,
            R.drawable.sala6,
            R.drawable.sala7,
            R.drawable.sala8,
            R.drawable.sala9,
            R.drawable.sala10,
            R.drawable.sala11,
            R.drawable.sala12,
            R.drawable.sala13,
            R.drawable.sala14
    };

    String [] titoli = {
            "L'archeologia dalla Preistoria al Medioevo",
            "Sacralità delle Grotte e Continuità dei Luoghi di Culto",
            "Continuità dei riti sacri e della cultura materiale",
            "Il pastore e il suo corredo",
            "La capanna in pietra a secco transumanza",
            "I ricoveri, gli stazzi la produzione del formaggio",
            "Il grano: dal seme alla farina",
            "Dall'oliveto all'olio. Produzione e raccolta del fieno",
            "La vite e il vino. Le attività complementari",
            "La casa: struttura, arredo e vita domestica",
            "Il lino e la lana: produzione, filatura e tessitura",
            "Vesti e ornamenti: Dal quotidiano al cerimoniale",
            "La Maiolica",
            "Il carcere borbonico e la borghesia",
    };
    String [] descrizioniStanza = { // descrizioni





    };

    String [] descrizioniOggetti = {

    };

    public int getNumero_stanze() {
        return numero_stanze;
    }

    public int[] getImmagini() {
        return immagini;
    }

    public String[] getTitoli() {
        return titoli;
    }

    public int getImmagine(int position)
    {
        return immagini[position];
    }

    public String getTitolo(int posizione)
    {
        return titoli[posizione];
    }
}

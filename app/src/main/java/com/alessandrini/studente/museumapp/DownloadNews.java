package com.alessandrini.studente.museumapp;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class DownloadNews extends AsyncTask<URL, Integer, ArrayList<String[]>> {
        MainActivity activity;

        public DownloadNews(MainActivity activity){
            this.activity = activity;
        }

        protected ArrayList<String[]> doInBackground(URL... urls) {
            if (isConnectedToInternet()) {
                StringBuffer newsRss=new StringBuffer();
                URL url = urls[0];
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = null;
                ArrayList<String[]> notizieTotali=new ArrayList<>();
                try {
                    builder = factory.newDocumentBuilder();
                    Document document = builder.parse("http://www.gentidabruzzo.com/?feed=rss2");
                    Element root = document.getDocumentElement();
                    NodeList news=document.getElementsByTagName("item");

                    for (int i=0;i<news.getLength();i++) {
                        Element notizia= (Element) news.item(i);
                        String title = notizia.getElementsByTagName("title").item(0).getTextContent();
                        String link = notizia.getElementsByTagName("link").item(0).getTextContent();
                        String pubDate = notizia.getElementsByTagName("pubDate").item(0).getTextContent();
                        notizieTotali.add(new String[]{title, link, pubDate});
                    }
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return notizieTotali;
            } else {
                // No Internet
                activity.ChangeLayout(R.layout.errore,"errore");
                return null;
            }

        }

        protected void onProgressUpdate(Integer... progress) {
        }

        protected void onPostExecute(ArrayList<String[]> notizie) {
            activity.caricaNews(notizie);

        }
    public boolean isConnectedToInternet(){
        ConnectivityManager connectivity = (ConnectivityManager)activity.getApplicationContext().getSystemService(activity.getApplicationContext().CONNECTIVITY_SERVICE);
        if (connectivity != null)
        {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }

        }
        return false;
    }
    }


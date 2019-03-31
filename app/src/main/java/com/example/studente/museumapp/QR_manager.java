package com.example.studente.museumapp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.hardware.Camera.Size;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;
import java.util.List;

public class QR_manager extends AppCompatActivity {


    private SurfaceView surfaceView;
    private CameraSource cameraSource;
    private TextView textView;
    private BarcodeDetector barcodeDetector;
    private LinearLayout container;
    private Context context;
    private final int RequestCam = 1001;
    private Activity activity;
    private Boolean c=false;




    public QR_manager(LinearLayout container , Context context, Activity activity)
    {
        this.container = container;
        this.context=context;
        this.activity= activity;


    }







    public void displayCamera()
    {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        LayoutInflater layoutInflater  = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view;
        container.removeAllViews();
        view = layoutInflater.inflate(R.layout.qr_scanner_layout,container,false);
        container.addView(view);

        surfaceView = view.findViewById(R.id.surfcaceview);

        barcodeDetector = new BarcodeDetector.Builder(context).setBarcodeFormats(Barcode.QR_CODE).build();

        cameraSource = new CameraSource.Builder(context,barcodeDetector).setAutoFocusEnabled(true).setRequestedPreviewSize(width,height).build();



        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                if(ActivityCompat.checkSelfPermission(context,Manifest.permission.CAMERA) !=
                PackageManager.PERMISSION_GRANTED)
                {
                      ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.CAMERA},RequestCam);
                    return;
                }
                else {

                    System.out.println("qui entra");
                    try {
                        cameraSource.start(surfaceView.getHolder());
                    } catch (IOException e) {
                        System.out.println("e>" + e.toString());
                    }
                }
                c=false;
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> qrCodes = detections.getDetectedItems();

                if(qrCodes.size() != 0 )
                {
                    textView.post(new Runnable() {
                        @Override
                        public void run() {
                          /*  Vibrator vibrator =  (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
                            vibrator.vibrate(100);*/
                            Decode(qrCodes.valueAt(0).displayValue);

                        }
                    });
                }
            }
        });
    }
    public void Decode(String a)
    {
        if(!c) {
            int stanza;
            try {
                stanza = Integer.parseInt(a);


            } catch (NumberFormatException e) {
                textView.setText("Stanza non trovata");
                return;
            }
            if (stanza > new Risorse().getNumero_stanze()) {
                return;
            } else {
                //apri stanza
                c = true;

                Intent intent = new Intent(activity, SalaActivity.class);
                intent.putExtra("posizione", stanza);
                activity.startActivity(intent);
            }
        }
    }
}

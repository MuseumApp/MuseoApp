package com.alessandrini.studente.museumapp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

import androidx.core.view.ViewCompat;
import androidx.appcompat.app.AppCompatActivity;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.squareup.picasso.Picasso;

import java.io.IOException;


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
    private  LayoutInflater layoutInflater;
    private ImageView imageView;
    private View view;
    public QR_manager(LinearLayout container , Context context, Activity activity)
    {
        this.container = container;
        this.context=context;
        this.activity= activity;
         layoutInflater  = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        this.container.removeAllViews();
        view = layoutInflater.inflate(R.layout.qr_indicator,container,false);
        this.container.addView(view);
        this.imageView = view.findViewById(R.id.qrborders);


    }
    public void displayCamera()
    {





        surfaceView = view.findViewById(R.id.surfcaceview);

       // System.out.println("w > " + dpWidth + "  e > " + dpHeight);

        barcodeDetector = new BarcodeDetector.Builder(context).setBarcodeFormats(Barcode.QR_CODE).build();

        cameraSource = new CameraSource.Builder(context,barcodeDetector).setAutoFocusEnabled(true)
                .setRequestedPreviewSize(5000,5000)
                .build();





        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {



            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                if(ActivityCompat.checkSelfPermission(context,Manifest.permission.CAMERA) !=
                PackageManager.PERMISSION_GRANTED)
                {
                      ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.CAMERA},RequestCam);

                    displayCamera();

                    return;
                }
                else {

                    System.out.println("qui entra");
                    try {
                        surfaceView.setWillNotDraw(false);
                        cameraSource.start(surfaceView.getHolder());

                        System.out.println("dimension> "+cameraSource.getPreviewSize());
                    } catch (IOException e) {
                        System.out.println("e>" + e.toString());
                    }
                }
                c=false;
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
//                System.out.println("w > " + cameraSource.getPreviewSize().getWidth() + "  e > " + cameraSource.getPreviewSize().getHeight());
                Picasso.get().load(R.drawable.square).into(imageView);
                imageView.setVisibility(View.INVISIBLE);
                imageView.setVisibility(View.VISIBLE);
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

                            Decode(qrCodes.valueAt(0).displayValue);


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

                return;
            }
            if (stanza > new Risorse().getNumero_stanze() && stanza < 1) {
                return;
            } else {
                //apri stanza
                c = true;

                Intent intent = new Intent(activity, SalaActivity.class);
                intent.putExtra("posizione", stanza-1);
                activity.startActivity(intent);
            }
        }
    }
}

package com.example.locationservice;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

public class IntentServiceLocation  extends IntentService {

    private static final String ACTION_LOCATION = "Rastreando...";
    private static String timeDataParam;
    private static String distanceDataParam;
    Handler handlerDisplay;
    LocationPrinter locationPrinter;
    //private AnimationDrawable animation;

    class DisplayToast implements Runnable {
        private final Context context;
        String stringText;

        DisplayToast(Context context, String text){
            this.context = context;
            this.stringText = text;
        }

        public void run(){
            Toast.makeText(context, stringText, Toast.LENGTH_SHORT).show();
        }
    }
    public IntentServiceLocation(){
        super("IntentServiceLocation");
        this.handlerDisplay = new Handler();
    }

    public void startActionLocation(Context context,String time, String distance) {
        try {
            this.timeDataParam = time;
            this.distanceDataParam = distance;
            Intent intent = new Intent(context, IntentServiceLocation.class);
            intent.setAction(ACTION_LOCATION);

            context.startService(intent);
        }
        catch (Exception ex)
        {
            String error = ex.getMessage();
            Log.e("Something wrong occur: ", error);
            //Toast.makeText(context, error, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null){
            String message = "Rastreando...";
            Log.d("onHandleIntent", "Location on Log...");
            handlerDisplay.post(new DisplayToast(this, "Rastreando..."));
            Toast.makeText(getApplicationContext(), message,
                    Toast.LENGTH_LONG).show();
            final String action = intent.getAction();
            if (ACTION_LOCATION.equals(action)) {
                handleActionLocation(timeDataParam, distanceDataParam);
            }
        }

    }

    private void handleActionLocation(String timeParam, String distanceParam){
                int time = Integer.valueOf(timeParam);
                float distance = Float.valueOf(distanceParam);
                Log.d("handleActionLocation", "time:" + time + " distance:" + distance);
                try {
                    Thread.sleep(5000);
                    locationPrinter = new LocationPrinter(this, new LocationPrinterParameters(time, distance, "RastreoActividad"));

                    locationPrinter.execute();
                    Thread.sleep(5000);
                    handlerDisplay.post(new DisplayToast(this,"Guardando ubicación en RastreoActividad.csv"));

                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                    locationPrinter.cancel(true);
                    Log.d("Something wrong occured",e.toString());
                }

            }
}

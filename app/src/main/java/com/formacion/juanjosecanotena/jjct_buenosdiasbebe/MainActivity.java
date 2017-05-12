package com.formacion.juanjosecanotena.jjct_buenosdiasbebe;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    public static final int PERIODO_MS = 10000;
    public static final int COD_INTENT_ALARMA = 55;

    public static AlarmManager alarmManager;
    public static PendingIntent pendingIntentAlarma;

    public static ArrayList<Despertar> historicoDespertares;
    public static Despertar despertar;
    public static Bitmap bitmapFoto;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button bProgramar = (Button)findViewById(R.id.bProgramar);
       final TimePicker tpHora = (TimePicker)findViewById(R.id.tpHora);

        bProgramar.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {

                Log.d("MENSAJE", "Click en PROGRAMAR" );
              Log.d("MENSAJE", "Hora programada: " + (new Integer(tpHora.getHour()).toString()) + " : " + (new Integer(tpHora.getMinute()).toString()) );

               programarAlarma();




            }
        });
    }//FIN onCreate

    private void programarAlarma(){


        Intent intentAlarma = new Intent(this, AlarmaReceiver.class);
        pendingIntentAlarma = PendingIntent.getBroadcast(this, COD_INTENT_ALARMA, intentAlarma, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        SimpleDateFormat dateformatter = new SimpleDateFormat("E dd/MM/yyyy 'a las' hh:mm:ss");

        Log.d("MENSAJE", "Tiempo ACTUAL "+ dateformatter.format(calendar.getTimeInMillis()));


        calendar.add(Calendar.SECOND,5);

        long tiempo = calendar.getTimeInMillis();


        //Log.d(getClass().getCanonicalName(), "Alarma ejecutándose "+ dateformatter.format(tiempo));

        Log.d("MENSAJE", "Tiempo programado "+ dateformatter.format(tiempo));


//alarmManager.set(AlarmManager.RTC_WAKEUP, tiempo, pi);

        //alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),PERIODO_MS,pendingIntentAlarma);

        alarmManager.set(AlarmManager.RTC_WAKEUP, tiempo, pendingIntentAlarma);

        historicoDespertares = new ArrayList<>();

    }//FIN programarAlarma


}

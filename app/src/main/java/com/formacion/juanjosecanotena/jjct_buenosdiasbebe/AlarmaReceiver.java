package com.formacion.juanjosecanotena.jjct_buenosdiasbebe;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AlarmaReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");

       //Reconfiguración alarma

        Calendar calendar_actual = Calendar.getInstance();
        long tiempo = calendar_actual.getTimeInMillis();
        SimpleDateFormat dateformatter = new SimpleDateFormat("E dd/MM/yyyy 'a las' hh:mm:ss");
        Log.d("MENSAJE", "Alarma ejecutándose "+ dateformatter.format(tiempo));
        tiempo = tiempo + MainActivity.PERIODO_MS;
        MainActivity.alarmManager.set(AlarmManager.RTC_WAKEUP, tiempo, MainActivity.pendingIntentAlarma);

        //Envío al servidor del historicoDespertares para que devuelva un despertar no repetido
/*
        PeticionBuenosDias peticionBuenosDias =new PeticionBuenosDias();
        peticionBuenosDias.execute();
*/


        PeticionDespertar peticionDespertar =new PeticionDespertar(context);
        peticionDespertar.execute(MainActivity.historicoDespertares);


/*
        try
        {



        }
        catch (Throwable t)
        {
            Log.e(getClass().getCanonicalName(), "ERRORAZO", t);
        }
*/


    }
}

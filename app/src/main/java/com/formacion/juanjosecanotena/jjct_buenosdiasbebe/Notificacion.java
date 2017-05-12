package com.formacion.juanjosecanotena.jjct_buenosdiasbebe;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by juanjosecanotena on 11/5/17.
 */

public class Notificacion {

    private Context context;

    public Notificacion(Context context){

        this.context = context;
    }



    public void lanzarNotificacion() {

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this.context)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("DESPIERTA")
                .setContentText("Buenos Días")
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL);

        Intent intentNotificacion = new Intent(this.context, NotificacionActivity.class);
        PendingIntent pendingIntentNotificacion = PendingIntent.getActivity(context,(int)System.currentTimeMillis(),intentNotificacion,PendingIntent.FLAG_ONE_SHOT);
        builder.setContentIntent(pendingIntentNotificacion);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification =builder.build();
        notificationManager.notify(200,notification);

/*
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.mipmap.ic_launcher_round);
        builder.setContentTitle("DESPIERTA");
        builder.setContentText("Buenos Días");
        builder.setAutoCancel(true);

        Intent intent = new Intent(context , NotificacionActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,(int)System.currentTimeMillis(),intent, PendingIntent.FLAG_ONE_SHOT);
        builder.setContentIntent(pendingIntent);
        builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(MainActivity.bitmapFoto));
*/
    }


}

package com.formacion.juanjosecanotena.jjct_buenosdiasbebe;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.MainThread;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by juanjosecanotena on 11/5/17.
 */

public class DescargaImagen extends AsyncTask<String,Void,Bitmap> {

    private Context context;

    public DescargaImagen(Context context){

        this.context = context;
    }


    private static final String URL_DIR_IMAGENES = "http://www.regalabien.com/BuenosDias/imagenes/";

    @Override
    protected Bitmap doInBackground(String... urls) {



        Bitmap bitmap = null;
        String urlFoto = null;
        URL url = null;
        HttpURLConnection httpURLConnection = null;
        int respuesta = 0;
        InputStream inputStream = null;



        try{
            urlFoto = URL_DIR_IMAGENES + urls[0];

            Log.d("MENSAJE","Descargar Imagen: "+urlFoto);
            url = new URL(urlFoto);
            httpURLConnection =(HttpURLConnection)url.openConnection();
            respuesta = httpURLConnection.getResponseCode();

            if(respuesta== HttpURLConnection.HTTP_OK){
                inputStream=httpURLConnection.getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
                inputStream.close();
            }
            else{
                Log.d("MENSAJE","RESPUESTA INCORRECTA");
            }
        }catch (Throwable t){
            Log.e("MENSAJE","ERROR",t);

        }finally {
            httpURLConnection.disconnect();


        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        Log.d("MENSAJE","onPostExecute bitmap");
        MainActivity.bitmapFoto = bitmap;

        Notificacion notificacion = new Notificacion(context);

        notificacion.lanzarNotificacion();

        //MainActivity mainActivity = new MainActivity();
        //mainActivity.notificar();


    }
}

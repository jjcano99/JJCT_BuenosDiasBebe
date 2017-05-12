package com.formacion.juanjosecanotena.jjct_buenosdiasbebe;

import android.content.Context;
import android.os.AsyncTask;

import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URLEncoder;
import java.util.ArrayList;

import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;


/**
 * Created by juanjosecanotena on 9/5/17.
 */

public class PeticionDespertar extends AsyncTask<ArrayList<Despertar>,Void,Despertar> {

    private Context context;

    public PeticionDespertar(Context context){

        this.context = context;
    }



    private static final String URL_SERVER = "http://www.regalabien.com/PeticionesWeb.asmx/BuenosDias";

    @Override
    protected Despertar doInBackground(ArrayList<Despertar>... params) {

        Log.d("MENSAJE","PeticionDespertar");



        String historicoDespertaresJSON = "";
        URL url=null;
        HttpURLConnection httpURLConnection = null;
        int responseCode=0;
        InputStream inputStream = null;
        OutputStream outputStream=null;
        InputStreamReader inputStreamReader = null;
        //String query="";

        Despertar despertar = new Despertar();
        ArrayList<Despertar> historicoDespertares ;

        historicoDespertares = params[0];


        //Convertimos el historico de despertares a JSON si contiene algún elemento

        if(historicoDespertares != null && historicoDespertares.size()>0) {
            Gson gson = new Gson();
            historicoDespertaresJSON = gson.toJson(historicoDespertares);
        }else
            historicoDespertaresJSON="VACIO";

        //query=String.format("historicoDespertares=%s", historicoDespertaresJSON);

        //Conectamos con el servidor para enviarle en POST historicoDespertaresJSON y luego recibir el despertarJSON



        try {

            url=new URL(URL_SERVER);
            httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true); // Es POST
            httpURLConnection.setRequestProperty("Accept-Charset","UTF-8");
            httpURLConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=utf-8");

            outputStream = httpURLConnection.getOutputStream();
            outputStream.write(String.format("historicoDespertaresJSON=%s", historicoDespertaresJSON).getBytes("utf-8"));

            Log.d("MENSAJE", "outputStream: " + String.format("historicoDespertaresJSON=%s", historicoDespertaresJSON));

            responseCode=httpURLConnection.getResponseCode();
            Log.d("MENSAJE","CÓDIGO DE RESPUESTA: "+new Integer(responseCode).toString());


            switch (responseCode) {

                case HttpURLConnection.HTTP_NO_CONTENT:

                    Log.d("MENSAJE", "SIN MENSAJE");
                    break;

                case HttpURLConnection.HTTP_OK:

                    Log.d("MENSAJE", "TODO OK");
                    inputStream = httpURLConnection.getInputStream();
                    //inputStreamReader = new InputStreamReader(inputStream,"iso-8859-1");
                    inputStreamReader = new InputStreamReader(inputStream, "utf-8");

                    //Log.d("MENSAJE", "RESPUESTA: " + inputStreamReader.getEncoding().toString());

                   // Log.d("MENSAJE", "RESPUESTA2: " + new java.util.Scanner(inputStream, "UTF-8").useDelimiter("\\A").next());

             //       BufferedReader br = new BufferedReader(inputStreamReader);
             //       Log.d("MENSAJE", "RESPUESTA3: " + br.readLine());

                    Gson gson = new Gson();
                    despertar = gson.fromJson(inputStreamReader, new TypeToken<Despertar>() {}.getType());

                    if(MainActivity.historicoDespertares != null   && MainActivity.historicoDespertares.size()== 5) MainActivity.historicoDespertares=new ArrayList<Despertar>();

                    MainActivity.historicoDespertares.add(despertar);




                    inputStream.close();



                    Log.d("MENSAJE", "RESPUESTA4: " + despertar.getCita() + " " + despertar.getFoto()+ " " + despertar.getMelodia());
                    break;

                default:
                    Log.d("MENSAJE", "ERROR");
                    break;

            }



        }catch (Exception e){

            e.printStackTrace();
        }


        return despertar;
    }

    @Override
    protected void onPostExecute(Despertar despertar) {
        super.onPostExecute(despertar);

        MainActivity.despertar=despertar;

        DescargaImagen descargaImagen = new DescargaImagen(context);
        descargaImagen.execute(despertar.getFoto());


    }
}

/*




 */
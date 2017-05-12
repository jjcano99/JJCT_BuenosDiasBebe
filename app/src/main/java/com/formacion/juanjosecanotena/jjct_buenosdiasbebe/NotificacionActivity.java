package com.formacion.juanjosecanotena.jjct_buenosdiasbebe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class NotificacionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacion);

        ImageView ivFotoDespertar=(ImageView)findViewById(R.id.fotoDespertar);
        TextView tvCitaDespertar=(TextView)findViewById(R.id.citaDespertar);


        ivFotoDespertar.setImageBitmap(MainActivity.bitmapFoto);
        tvCitaDespertar.setText(MainActivity.despertar.getCita());

    }
}

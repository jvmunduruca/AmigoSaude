package br.com.amigosaude.app.amigosaude;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by joaom on 22/03/2018.
 */

public class Splash_activity extends AppCompatActivity{

    private static final String TAG = "Splash_activity";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

        String nomes = "Bruno Luiz Conestabile Silva"
                + System.getProperty ("line.separator")
                + "Gian Victor de Mello"
                + System.getProperty ("line.separator")
                + "Giovana Montes Maletta"
                + System.getProperty ("line.separator")
                + "Jeferson Ferreira Lemos"
                + System.getProperty ("line.separator")
                + "João Munduruca"
                + System.getProperty ("line.separator")
                + "Paula Viana Barros";
        TextView txt_Nomes = (TextView)findViewById(R.id.nomes);
        txt_Nomes.setText(nomes);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // INICIA APOS 5 SEGUNDOS
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Log.d(TAG, "Handler: Splash");
                startActivity(new Intent(getBaseContext(), Home_Activity.class));
                finish();

            }
        }, 3000);

    }


}

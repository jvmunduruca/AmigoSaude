package br.com.amigosaude.app.amigosaude;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;

/**
 * Created by joaom on 22/03/2018.
 */

public class Splash_activity extends AppCompatActivity{

    private static final String TAG = "Splash_activity";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

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
        }, 2000);

    }


}

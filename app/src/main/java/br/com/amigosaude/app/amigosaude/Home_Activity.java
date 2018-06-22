package br.com.amigosaude.app.amigosaude;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class Home_Activity extends AppCompatActivity {

    private static final String TAG = "home_activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        Log.d(TAG, "Home Iniciada: ");
    }

    public void postos(View view) {
        startActivity(new Intent(getBaseContext(), SelUnidade_Activity.class));
    }

    public void emergencia(View view) {
        startActivity(new Intent(getBaseContext(), Tel_Activity.class));
    }

}

package br.com.amigosaude.app.amigosaude;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import br.com.amigosaude.app.amigosaude.R;

public class PS_activity extends AppCompatActivity {

    private static final String TAG = "PS_activity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ps_layout);
        Log.d(TAG, "Inicio");

    }
}




package br.com.amigosaude.app.amigosaude;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class Home_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
    }

    public void postos(View view) {
        startActivity(new Intent(getBaseContext(), SelUnidade_Activity.class));
    }

    public void emergencia(View view) {
        startActivity(new Intent(getBaseContext(), Tel_Activity.class));
    }

}

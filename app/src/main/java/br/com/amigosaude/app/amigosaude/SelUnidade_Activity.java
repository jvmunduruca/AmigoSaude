package br.com.amigosaude.app.amigosaude;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SelUnidade_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selunidade_layout);
    }

    public void prontosSocorros(View view) {
        startActivity(new Intent(getBaseContext(), PS_activity.class));
        finish();
    }

    public void amas(View view) {
        startActivity(new Intent(getBaseContext(), Amas_Activity.class));
        finish();
    }
}

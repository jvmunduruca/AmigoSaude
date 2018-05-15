package br.com.amigosaude.app.amigosaude;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PS_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ps_layout);
    }

    public void InformaUnidade(View view) {
        startActivity(new Intent(getBaseContext(), Map_activity.class));
        finish();
    }
}

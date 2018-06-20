package br.com.amigosaude.app.amigosaude;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Ubs_Activiy extends AppCompatActivity {

    //variaveis
    TextView tv;
    RelativeLayout Unid;
    String unid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ubs_layout);
    }

    public void informaUnidade1(View view) {
        tv = findViewById(R.id.endereco);
        Unid = findViewById(R.id.unidade1);
        unid = tv.getText().toString();
        Unid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Map_activity.class);
                i.putExtra("unid", unid);
                startActivity(i);
            }
        });
    }
}

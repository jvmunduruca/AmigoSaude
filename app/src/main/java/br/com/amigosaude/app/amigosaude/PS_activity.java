package br.com.amigosaude.app.amigosaude;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class PS_activity extends AppCompatActivity {

    private static final String TAG = "PS_activity";

    //variaveis
    Ps_CardAdapter PsCard;
    List<Ps> listaPS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ps_layout);
        Log.d(TAG,"Inicio");

        initListaPS();

    }

    private void initListaPS(){
        Log.d(TAG,"preenche a lista");
        listaPS = new ArrayList<>();

        listaPS.add(
                new Ps(
                        R.drawable.icone_socorro,
                        "Hospital Grajaú",
                        "Rua tal, 50"
                        )
        );
        listaPS.add(
                new Ps(
                        R.drawable.icone_socorro,
                        "UBS Socorro",
                        "Rua aquela, 20"
                )
        );

        initPsCard();
    }

    private void initPsCard(){
        Log.d(TAG, "initPsCard: monta a lista");
        RecyclerView recyclerView = findViewById(R.id.Uni_PS);
        Ps_CardAdapter adapter = new Ps_CardAdapter(this, (ArrayList<Ps>) listaPS);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


    }

    public void InformaUnidade(View view) {
        startActivity(new Intent(getBaseContext(), Map_activity.class));

    }
}

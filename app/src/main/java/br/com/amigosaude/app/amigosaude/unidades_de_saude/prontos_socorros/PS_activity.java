package br.com.amigosaude.app.amigosaude.unidades_de_saude.prontos_socorros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.amigosaude.app.amigosaude.R;

public class PS_activity extends AppCompatActivity {

    private static final String TAG = "PS_activity";

    //variaveis
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
                        1,
                        R.drawable.icone_socorro,
                        "Pronto Socorro Municipal Santo Amaro",
                        "Avenida Adolfo Pinheiro, 805. CEP: 4733100",
                        "5523-1777"
                        )
        );

        listaPS.add(
                new Ps(
                        2,
                        R.drawable.icone_socorro,
                        "Pronto Socorro Hospital Santa Casa de Santo Amaro",
                        "Rua Isabel Schimdt, 59. CEP: 4743030",
                        "5525-8700"
                )
        );

        listaPS.add(
                new Ps(
                        2,
                        R.drawable.icone_socorro,
                        "PS/Hospital Estadual Regional Sul",
                        "Rua General Roberto Alves de Carvalho Filho, 270. CEP: 4744000 ",
                        "5694-8200"
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
}

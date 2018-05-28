package br.com.amigosaude.app.amigosaude;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Tel_Activity extends AppCompatActivity {

    private static final String TAG = "Tel_Activity";

    Tel_Card telCard;
    List<Tel> listaTel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tel_layout);

        initLista();
    }

    private void initLista(){
        Log.d(TAG,"initLista");
        listaTel = new ArrayList<>();

        listaTel.add(
                new Tel(
                        1,
                        "Policia Militar",
                        "190",
                        R.drawable.icone_socorro
                )
        );
        listaTel.add(
                new Tel(
                        1,
                        "Ambulância",
                        "192",
                        R.drawable.icone_socorro
                )
        );
        listaTel.add(
                new Tel(
                        1,
                        "Corpo de Bombeiros",
                        "193",
                        R.drawable.icone_socorro
                )
        );
        listaTel.add(
                new Tel(
                        1,
                        "Defesa Civil",
                        "199",
                        R.drawable.icone_socorro
                )
        );

        initTelCard();
    }

    private void initTelCard(){
        Log.d(TAG,"initTelCard");
        RecyclerView mRecyclerView = findViewById(R.id.opcaoTelefone);
        Tel_Card novoTelCard = new Tel_Card(this,listaTel);
        mRecyclerView.setAdapter(novoTelCard);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        // Codding With Mitch
    }

}

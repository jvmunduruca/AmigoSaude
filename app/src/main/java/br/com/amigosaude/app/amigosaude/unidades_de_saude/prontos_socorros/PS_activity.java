package br.com.amigosaude.app.amigosaude.unidades_de_saude.prontos_socorros;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.amigosaude.app.amigosaude.R;

public class PS_activity extends AppCompatActivity {

    private static final String TAG = "PS_activity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ps_layout);
        ListView listaDePostos = findViewById(R.id.listaPS);
        Log.d(TAG, "Inicio");


    }

    public class posto {
        private String nome;
        private String endereco;

        public posto() {

        };

        public posto(String nome, String endereco) {
            super();
            this.nome = nome;
            this.endereco = endereco;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getEndereco() {
            return endereco;
        }

        public void setEndereco(String endereco) {
            this.endereco = endereco;
        }


        private List<posto> gerarPS(String nome, String endereco) {
            List<posto> postos = new ArrayList<posto>();
            postos.add(gerarPS("PS municipal", "santo amaro"));

            return posto;
        }

    }

}




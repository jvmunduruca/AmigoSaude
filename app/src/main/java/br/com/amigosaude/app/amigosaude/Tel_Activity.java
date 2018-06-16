package br.com.amigosaude.app.amigosaude;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.amigosaude.app.amigosaude.R;

public class Tel_Activity extends AppCompatActivity {

    private static final String TAG = "Tel_Activity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tel_layout);
    }

    public void ligar(View view) {
        Log.d(TAG, "ligar: Clicado!");
        Toast.makeText(this,"Modo de teste // Ligação não permitida", Toast.LENGTH_SHORT).show();
    }
}

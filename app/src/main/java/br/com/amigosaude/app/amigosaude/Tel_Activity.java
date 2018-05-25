package br.com.amigosaude.app.amigosaude;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Tel_Activity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    Tel_Card telCard;

    List<Tel> listaTel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tel_layout);

        listaTel = new ArrayList<>();

        mRecyclerView = (RecyclerView) findViewById(R.id.opcaoTelefone);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

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

        telCard = new Tel_Card(this,listaTel);
        mRecyclerView.setAdapter(telCard);
    }

    public void ligar192(View view) {
        String uri = "tel:" + "192"; // Ver forma de pegar numero do item da lista
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse(uri));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(intent);
    }
}

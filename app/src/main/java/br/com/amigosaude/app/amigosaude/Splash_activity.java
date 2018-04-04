package br.com.amigosaude.app.amigosaude;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

/**
 * Created by joaom on 22/03/2018.
 */

public class Splash_activity extends AppCompatActivity{

    private static final String TAG = "Splash_activity";
    private static final int ERROR_DIALOG_REQUEST = 9001;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int COD_REQ_PERMISSOES = 1;

    //VARIAVEIS
    private Boolean permissoesDadas = false;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        // INICIA O MAPA APOS 5 SEGUNDOS
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                // CARREGAR O BANCO DE DADOS

                // VERIFICA AS PERMISSÕES
                pegaPermissoes();

                iniciaMapa();
            }
        }, 5000);

    }

    public void iniciaMapa(){
        Log.d(TAG,"iniciaMapa : Chamando activity Map");
        if(isServicesOK()){
            startActivity(new Intent(getBaseContext(), Map_activity.class));
            finish();
        } else {
            Toast.makeText(this,"Não foi possivel iniciar",Toast.LENGTH_SHORT);
        }
    }

    public boolean isServicesOK(){
        Log.d(TAG, "isServicesOK: checando versão do Goggle Services");

        int disponivel = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);

        if(disponivel == ConnectionResult.SUCCESS){
            //Tudo OK com o Google Services
            Log.d(TAG, "isServicesOK; Google Plays Services está OK");
            return true;
        }else if(GoogleApiAvailability.getInstance().isUserResolvableError(disponivel)){
            // um erro ocorreu mais pode ser resolvido
            Log.d(TAG, "isServicesOK: Ocorreu um erro e pode ser resolvido");
            Toast.makeText(this,"Google Services sendo resolvido", Toast.LENGTH_SHORT).show();
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(this, disponivel, ERROR_DIALOG_REQUEST);
        } else {
            Toast.makeText(this,"Não é possivel fazer solicitações ao Mapa", Toast.LENGTH_SHORT);
        }
        return false;
    }

    private void pegaPermissoes(){
        Log.d(TAG,"pegarPermissoes : Verificando permissões");
        String[] permissoes = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                permissoesDadas = true;
                Log.d(TAG,"pegarPermissoes : Permissoes OK, iniciar mapa");
                return;
            } else {
                ActivityCompat.requestPermissions(Splash_activity.this,permissoes, COD_REQ_PERMISSOES);
            }

        } else {
            ActivityCompat.requestPermissions(Splash_activity.this,permissoes, COD_REQ_PERMISSOES);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissoesDadas = false;
        switch (requestCode){
            case COD_REQ_PERMISSOES:{
                if (grantResults.length > 0){
                    for(int i = 0; i < grantResults.length; i++){
                        if(grantResults[i] != PackageManager.PERMISSION_GRANTED){
                            permissoesDadas = false;
                            return;
                        }
                    }
                    permissoesDadas = true;
                    iniciaMapa();
                }
            }
        }
    }
}

package br.com.amigosaude.app.amigosaude;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // CARREGAR O BANCO DE DADOS

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(isServicesOK()){
                    startActivity(new Intent(getBaseContext(), Map_activity.class));
                    finish();
                }

            }
        }, 5000);
    }

    public boolean isServicesOK(){
        Log.d(TAG, "isServicesOK: checando versão do Goggle Services");

        int disponivel = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);

        if(disponivel == ConnectionResult.SUCCESS){
            //Tudo OK com o Google Services
            Log.d(TAG, "isServicesOK; Google Plays Services está OK");
            Toast.makeText(this,"Google Services OK", Toast.LENGTH_SHORT).show();
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

}

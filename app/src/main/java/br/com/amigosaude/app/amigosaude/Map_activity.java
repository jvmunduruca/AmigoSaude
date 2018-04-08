package br.com.amigosaude.app.amigosaude;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class Map_activity extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = "Map_activity";

    //VARIAVEIS
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationClient;
    private static final float ZOOM_PADRAO= 15f;
    private static final int ERROR_DIALOG_REQUEST = 9001;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int COD_REQ_PERMISSOES = 1;
    private Boolean permissoesDadas = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_layout);

        pegaPermissoes();

        iniciaMapa();

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
                ActivityCompat.requestPermissions(this,permissoes, COD_REQ_PERMISSOES);
            }

        } else {
            ActivityCompat.requestPermissions(this,permissoes, COD_REQ_PERMISSOES);
        }
    }

    public void iniciaMapa(){
        Log.d(TAG,"iniciaMapa : Chamando activity Map");
        if(isServicesOK()){
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
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

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Toast.makeText(Map_activity.this,"Mapa OK",Toast.LENGTH_SHORT).show();
        mMap = googleMap;


        //PEGA A LOCALIZAÇÃO DO USUÁRIO - Em contrução
        if(permissoesDadas){
            Toast.makeText(Map_activity.this,"Meu Local",Toast.LENGTH_SHORT);

            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                return;
            }
            pegaLocalDisp();
            mMap.setMyLocationEnabled(true);
        } else {
            // ADICIONA UM MARCADOR NA ETECIA E MOVE O MAPA
            LatLng etecia = new LatLng(-23.7049869, -46.6904032);
            mMap.addMarker(new MarkerOptions().position(etecia).title("ETECIA"));
            moveCamera(etecia,ZOOM_PADRAO);
        }


    }

    public void pegaLocalDisp(){
        Log.d(TAG,"pegaLocalDisp: pegando localização");
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        try{
            if(permissoesDadas){
                Task location = mFusedLocationClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Map_activity.this,"Pegando localização atual",Toast.LENGTH_SHORT).show();
                            Log.d(TAG,"onComplete: Localização atual encontrada");
                            Location localAtual = (Location) task.getResult();

                            moveCamera(new LatLng(localAtual.getLatitude(),localAtual.getLongitude()),ZOOM_PADRAO);
                        }else {
                            Log.d(TAG,"onComplete: Localização atual NÂO encontrada");
                            Toast.makeText(Map_activity.this,"Local atual indisponível",Toast.LENGTH_SHORT).show();


                        }
                    }
                });
            }
        }catch (SecurityException e){
            Toast.makeText(Map_activity.this,"Erro SecurityException pegaLocalDisp",Toast.LENGTH_SHORT);
        }

    }

    private void moveCamera(LatLng latLng, float zoom){
        Log.d(TAG,"moveCamera: movendo a camera para LAT"+latLng.latitude+", LNG: "+latLng.longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
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

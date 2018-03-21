package br.com.amigosaude.app.amigosaude;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
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
import com.google.android.gms.tasks.OnSuccessListener;

public class Map_activity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationClient;

    private static final String TAG = "Map_activity";
    private static final int ERROR_DIALOG_REQUEST = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_layout);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if(isServicesOK()){
            init();
        }


    }

    private void init(){
        Toast.makeText(this,"init() iniciado", Toast.LENGTH_SHORT);
    }

    public boolean isServicesOK(){
        Log.d(TAG, "isServicesOK: checando versão do Goggle Services");

        int disponivel = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(Map_activity.this);

        if(disponivel == ConnectionResult.SUCCESS){
            //Tudo OK com o Google Services
            Log.d(TAG, "isServicesOK; Google Plays Services está OK");
            return true;
        }else if(GoogleApiAvailability.getInstance().isUserResolvableError(disponivel)){
            // um erro ocorreu mais pode ser resolvido
            Log.d(TAG, "isServicesOK: Ocorreu um erro e pode ser resolvido");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(Map_activity.this, disponivel, ERROR_DIALOG_REQUEST);
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
        mMap = googleMap;

        // CONFIGURAÇOES DE VISUALIZAÇÃO DO MAPA
        mMap.setMinZoomPreference(10);


        //PEGA A LOCALIZAÇÃO DO USUÁRIO - Em contrução
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            Toast.makeText(Map_activity.this,"Localização OK",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        // ADICIONA UM MARCADOR NA ETECIA E MOVE O MAPA
        LatLng etecia = new LatLng(-23.7049869, -46.6904032);
        mMap.addMarker(new MarkerOptions().position(etecia).title("ETECIA"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(etecia));
    }
}

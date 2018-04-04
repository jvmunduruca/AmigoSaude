package br.com.amigosaude.app.amigosaude;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.Map;

public class Map_activity extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = "Map_activity";

    //VARIAVEIS
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationClient;
    private Boolean meuLocalOk = true;
    private static final float ZOOM_PADRAO= 15f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_layout);

        initMap();

    }

    public void initMap(){
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        if(meuLocalOk){
            Toast.makeText(Map_activity.this,"Meu Local",Toast.LENGTH_SHORT);

            pegaLocalDisp();

            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                return;
            }
            mMap.setMyLocationEnabled(true);
        }

        // ADICIONA UM MARCADOR NA ETECIA E MOVE O MAPA
        //LatLng etecia = new LatLng(-23.7049869, -46.6904032);
        //mMap.addMarker(new MarkerOptions().position(etecia).title("ETECIA"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(etecia));
    }

    public void pegaLocalDisp(){
        Log.d(TAG,"pegaLocalDisp: pegando localização");
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        try{
            if(meuLocalOk){
                Task location = mFusedLocationClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Map_activity.this,"Pegando localização atual",Toast.LENGTH_SHORT).show();
                            Log.d(TAG,"onComplete: Localização atual encontrada");
                            Location localAtual = (Location) task.getResult();

                            moveCamera(new LatLng(localAtual.getLongitude(),localAtual.getLongitude()),ZOOM_PADRAO);
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
}

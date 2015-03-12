package com.elpoeta.menulateralslide;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.elpoeta.menulateralslide.database.orm.Seas01;
import com.elpoeta.menulateralslide.staticdata.ListaElementos;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class Mapa extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        setUpMapIfNeeded();



    }

    @Override
    public void onMapReady(GoogleMap map) {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(41.889, -87.622), 16));

        // You can customize the marker image using images bundled with
        // your app, or dynamically generated bitmaps.
        //***********************
        //Aqui estaba creado el primer punto
        //***********************
        /*map.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher))
                .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                .position(new LatLng(41.889, -87.622)));*/
    }


    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            //ListaElementos listaEles = new ListaElementos();
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            ArrayList<Seas01> proof = ListaElementos.getDatosSeas();
            for (int i = 0; i < proof.size(); i++) {
                String lon = proof.get(i).getLongitud();
                String lat = proof.get(i).getLatitud();
                double lonD = Double.parseDouble(lon);
                double latD = Double.parseDouble(lat);
                LatLng point = new LatLng(lonD, latD);
                MarkerOptions marcador = new MarkerOptions();
                marcador.position(point);
                mMap.addMarker(marcador);
            }


            //mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
            //       .getMap(); EL ORIGINAL
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        //smMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }
}

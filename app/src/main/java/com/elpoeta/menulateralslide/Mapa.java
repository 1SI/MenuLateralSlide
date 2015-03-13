package com.elpoeta.menulateralslide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Mapa extends Fragment {
    MapView mapView;
    GoogleMap map;
    LatLng point;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_mapa, container, false);
// Gets the MapView from the XML layout and creates it
        mapView = (MapView) v.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        point = new LatLng(41.889, -87.622);
        MarkerOptions marcador = new MarkerOptions();
        map.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher))
                .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                .position(point));
        map.addMarker(marcador);
// Gets to GoogleMap from the MapView and does initialization stuff
        map = mapView.getMap();
        map.getUiSettings().setMyLocationButtonEnabled(false);
        map.setMyLocationEnabled(true);

// Needs to call MapsInitializer before doing any CameraUpdateFactory calls
        MapsInitializer.initialize(this.getActivity());
// Updates the location and zoom of the MapView
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(43.1, -87.9), 10);
        map.animateCamera(cameraUpdate);
        return v;
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}


/*
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
            mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker()).position(point));
        }



    }

    private void crearMapa(){
        ArrayList<Seas01> proof = ListaElementos.getDatosSeas();

        for (int i = 0; i < proof.size(); i++) {
            String lon = proof.get(i).getLongitud();
            String lat = proof.get(i).getLatitud();
            double lonD = Double.parseDouble(lon);
            double latD = Double.parseDouble(lat);
            LatLng point = new LatLng(lonD, latD);
            MarkerOptions marcador = new MarkerOptions();
            mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher))
                    .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                    .position(point));
            mMap.addMarker(marcador);
        }
    }

    @Override
    public void onMapReady(GoogleMap map) {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(41.889, -87.622), 16));

        // You can customize the marker image using images bundled with
        // your app, or dynamically generated bitmaps.
        /*/
/***********************
 //Aqui estaba creado el primer punto
 /*/
/***********************
 */
/*map.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher))
                .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                .position(new LatLng(41.889, -87.622)));*//*



    }


    @Override
    protected void onResume() {
        super.onResume();
        //setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            //ListaElementos listaEles = new ListaElementos();



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
*/

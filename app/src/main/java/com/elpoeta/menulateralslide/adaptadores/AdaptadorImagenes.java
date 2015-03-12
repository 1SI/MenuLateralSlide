package com.elpoeta.menulateralslide.adaptadores;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * Created by lalotone on 12/03/15.
 */
public class AdaptadorImagenes extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Iterator iter = DatosSesion.getListaContactos().entrySet().iterator();

        // Each row in the list stores country name, currency and flag
        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> hm = new HashMap<String, String>();

        while (iter.hasNext()) {
            Map.Entry tupla = (Map.Entry) iter.next();
            Contacto datos = (Contacto) tupla.getValue();
            hm.put("nombre", "Persona : " + datos.getNombre() + " " + datos.getApellido());
            hm.put("email", "D : " + datos.getEmail());
            hm.put("flag", Integer.toString(R.drawable.contacto));
            aList.add(hm);
        }
        // Keys used in Hashmap
        String[] from = {"nombre", "email", "flag"};

        // Ids of views in listview_layout
        int[] to = {R.id.txt, R.id.cur, R.id.flag};

        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(), aList, R.layout.listview_layout, from, to);
        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}

package com.elpoeta.menulateralslide;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elpoeta.menulateralslide.thread.GetData;

/**
 * Created by user on 26/08/2014.
 */
public class Seccion1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        new GetData().execute();
        View rootView = inflater.inflate(R.layout.seccion1, container, false);

        return rootView;
    }
}

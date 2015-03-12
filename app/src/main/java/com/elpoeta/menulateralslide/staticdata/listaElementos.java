package com.elpoeta.menulateralslide.staticdata;

import com.elpoeta.menulateralslide.database.orm.seas01;

import java.util.ArrayList;

/**
 * Created by yonseca on 12/03/15.
 */
public class listaElementos {

    public static ArrayList<seas01> datosSeas;

    public static ArrayList<seas01> getDatosSeas() {
        return datosSeas;
    }

    public static void setDatosSeas(ArrayList<seas01> datosSeas) {
        listaElementos.datosSeas = datosSeas;
    }
}

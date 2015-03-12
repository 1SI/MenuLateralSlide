package com.elpoeta.menulateralslide.staticdata;

import com.elpoeta.menulateralslide.database.orm.Seas01;

import java.util.ArrayList;

/**
 * Created by yonseca on 12/03/15.
 */
public class ListaElementos {

    public static ArrayList<Seas01> datosSeas;

    public static ArrayList<Seas01> getDatosSeas() {
        return datosSeas;
    }

    public static void setDatosSeas(ArrayList<Seas01> datosSeas) {
        ListaElementos.datosSeas = datosSeas;
    }
}

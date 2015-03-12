package com.elpoeta.menulateralslide.data;

/**
 * Created by lalotone on 12/03/15.
 */
public class Item {
    private String rutaImagen;
    private String descripcion;

    public Item(){

    }
    public Item(String rutaImagen, String descripcion){
        this.rutaImagen = rutaImagen;
        this.descripcion = descripcion;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

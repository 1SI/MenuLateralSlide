package com.elpoeta.menulateralslide.database.connect;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Post {

    private InputStream is = null;
    private String respuesta = "";

    /**
     * Hace una petición HTTP a un servidor, lee la respuesta y devuelve el resultado
     * como un un objeto casteable a JSONArray o JSONObject, dependiendo de la respuesta
     *
     * @param parametros - Un ArrayList de String con parámetros clave-valor que
     *                   se enviarán al servidor. Los valores 0, 2, 4... son las claves, y sus consecutivos
     *                   (1, 3, 5...) los valores que les corresponden.
     * @param URL        La URL a la que se le envían los datos por HTTP
     * @return Un Object con los datos que se han recuperado, en JSON
     */
    public Object getServerData(ArrayList<String> parametros, String URL) {

        conectaPost(parametros, URL);

        if (is != null) {
            getRespuestaPost();
        }
        Log.e("Respuesta: ", respuesta);
        if (respuesta != null && respuesta.trim() != "") {
            Object datos = getJsonObject();
            return datos;
        } else {
            return null;
        }

    }

    /**
     * Pilla el ArrayList en el que están los datos metidos a pelo, los convierte a un
     * BasicNameValuePair y hace la petición HTTP al servidor.
     *
     * @param parametros - ArrayList de Strings. El contenido son claves y valores, en el
     *                   orden de clave, valor, clave, valor. Los elementos pares (0,2,4...) son claves, y
     *                   los que les siguen (1, 3, 5... respectivamente) los valores.
     * @param URL
     */
    private void conectaPost(ArrayList<String> parametros, String URL) {
        ArrayList<BasicNameValuePair> nameValuePairs;
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(URL);
            nameValuePairs = new ArrayList<BasicNameValuePair>();
            if (parametros != null) {

                // Pilla una clave, pilla un valor, avanza dos posiciones
                for (int i = 0; i < parametros.size(); i += 2) {
                    nameValuePairs.add(new BasicNameValuePair((String) parametros.get(i), (String) parametros.get(i + 1)));
                }
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            }
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        } catch (Exception e) {
            Log.e("log_tag", "Error in http connection " + e.toString());
        }
    }

    /**
     * Coge la respuesta del servidor
     */
    private void getRespuestaPost() {
        try {
            // Ese iso-8859-1 huele mal.
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            String datos = sb.toString();
            Log.e("log_tag", datos);
            Scanner pillaJson = new Scanner(datos);

            /* 000webhost clava un script al final de cada respuesta; esto pilla solamente
             * lo interesante */
            respuesta = pillaJson.nextLine();
            Log.e("log_tag", "Cadena JSon " + respuesta.toString());
        } catch (Exception e) {
            Log.e("log_tag", "Error converting result " + e.toString());
        }
    }

    /**
     * Devuelve un objeto que contiene los datos del JSON que ha escupido el servidor
     *
     * @return Un objeto casteable a JSONArray o JSONObject con los datos pedidos al servidor.
     */
    @SuppressWarnings("finally")
    private Object getJsonObject() {
        try {
            Object datos = respuesta.startsWith("[")
                    ? new JSONArray(respuesta)
                    : new JSONObject(respuesta);
            return datos;
        } catch (Exception e) {
            Log.e("", "Error al crear el jsonObject");
            return null;
        }
    }
}

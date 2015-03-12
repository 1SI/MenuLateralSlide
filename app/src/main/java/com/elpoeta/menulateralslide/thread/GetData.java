package com.elpoeta.menulateralslide.thread;

import android.os.AsyncTask;
import android.util.Log;

import com.elpoeta.menulateralslide.database.connect.Post;
import com.elpoeta.menulateralslide.database.orm.Seas01;
import com.elpoeta.menulateralslide.staticdata.ListaElementos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by yonseca on 12/03/15.
 */
public class GetData extends AsyncTask<Void, Void, JSONArray> {

    /**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p/>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @param params The parameters of the task.
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     */
    @Override
    protected JSONArray doInBackground(Void... params) {
        try {
            /*
             * Exigencias del guión: Para conectar a la base de datos
             * hay que montar una petición HTTP, y en esa petción HTTP
             * hay que incluir los datos. La clase Post monta todo, pero
             * está preparada apra grabajar con ArrayLists, y de ahí si
             * eso montar un NameValuePair que contenga los datos.
             *
             * En esencia: Sí, están metidos a pelo. Sí, en un
             * ArrayList, sin más. Y sí, mejor meterlos con cuidadito y
             * en orden.
             *
             * Clave, valor, clave, valor.
             */
            ArrayList<String> parametros = new ArrayList<String>();

            /* CLASE QUE UTILIZAMOS PARA CONECTAR POR POST */
            Post post = new Post();

            /*
             * Si todo va bien, recibirá un array en JSON que contendrá
             * los datos para montar la lista
             */
            Object datos = post.getServerData(parametros,
                    "http://thedevpotato.net76.net/getSEAS.php");
            return (JSONArray) datos;

            // Y ya.
        } catch (Exception e) {
            // Un reventón aquí no es deseable...
            return null;
        }
    }

    /**
     * <p>Runs on the UI thread after {@link #doInBackground}. The
     * specified result is the value returned by {@link #doInBackground}.</p>
     * <p/>
     * <p>This method won't be invoked if the task was cancelled.</p>
     *
     * @see #onPreExecute
     * @see #doInBackground
     * @see #onCancelled(Object)
     */
    protected void onPostExecute(JSONArray datos) {

        ArrayList<Seas01> listaDatos = new ArrayList<>();

        for (int i = 0; i < datos.length(); i++) {
            try {
                JSONObject datosContacto = datos.getJSONObject(i);
                Seas01 fila = new Seas01(
                        datosContacto.getInt("ID"),
                        datosContacto.getString("NOMBRE"),
                        datosContacto.getString("DESCRIPCION"),
                        datosContacto.getString("LONGITUD"),
                        datosContacto.getString("LATITUD"),
                        datosContacto.getString("IMAGEN")
                );
                listaDatos.add(fila);
                ListaElementos.setDatosSeas(listaDatos);
            } catch (JSONException e) {
                Log.e("Reventón", "Error al leer el JSON de la lista de contactos");
                ListaElementos.setDatosSeas(null);
            }
        }
    }
}

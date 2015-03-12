package com.elpoeta.menulateralslide.database.connect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class SQLConnector extends Thread {

	/**
	 * Crea una URL con el formato correcto para hacer el login. 
	 * @return 
	 */
	public String loginURL(String user, String pass) {
		
		String link = "http://postalog.netne.net/app/login.php&";
		String data = null;

		try {
			// Crea un URL y le añade la información de user y pass
			URL url = new URL(link);
			data = URLEncoder.encode("username", "UTF-8") + "="
					+ URLEncoder.encode(user, "UTF-8");
			data += "&" + URLEncoder.encode("password", "UTF-8") + "="
					+ URLEncoder.encode(pass, "UTF-8");
			data = dbListen(url, data);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return data;
	}

	/**
	 * Crea la conexión a la base de datos y obtiene los datos de user y pass
	 * @return Un String con datos si la cosa ha ido bien, un null si la cosa ha
	 *         ido mal.
	 */
	public String dbListen(URL url, String data) {

		try {
			// Crea la conexión y obtiene datos
			URLConnection conn = url.openConnection();
			OutputStreamWriter wr = new OutputStreamWriter(
					conn.getOutputStream());
			wr.write(data);
			wr.flush();

			// Lee la respuesta del servidor
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line = null;

			while ((line = reader.readLine()) != null) {
				sb.append(line);
				break;
			}
			data = sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return data;
	}

	@Override
	public void run() {
		super.run();
		
	}
	
	
}

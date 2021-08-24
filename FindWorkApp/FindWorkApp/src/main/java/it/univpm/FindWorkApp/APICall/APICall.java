package it.univpm.FindWorkApp.APICall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class APICall {

	private String url;

	public APICall(String location, String search, boolean remote) {
		this.url = "https://findwork.dev/api/jobs/?location=" + location + "&search=" + search + "&remote=" + remote;
	}

	public void getData() {

		String api = this.url;
		String data_filter ="";
		String line = "";

		try {
			HttpURLConnection openConnection = (HttpURLConnection) new URL(api).openConnection();
			openConnection.setRequestMethod("GET");
			openConnection.setRequestProperty("Authorization", "Token 838d2cb24efd24d35d81f7cc6846cd6ec54cd4dd");
			openConnection.setRequestProperty("Accept", "application/json");

			InputStream in = openConnection.getInputStream();
			try {
				InputStreamReader inR = new InputStreamReader(in);
				BufferedReader buf = new BufferedReader(inR);
				while ((line = buf.readLine()) != null) {
					data_filter += line;
					System.out.println(line);
				}
				
			}finally {
				in.close();
			}

			JSONObject obj = (JSONObject) JSONValue.parseWithException(data_filter);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

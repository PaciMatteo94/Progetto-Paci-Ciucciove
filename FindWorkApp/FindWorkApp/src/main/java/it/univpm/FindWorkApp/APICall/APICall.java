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
import org.json.simple.parser.ParseException;

public class APICall {
	private String url;
	private static APICall instance=null;
	private APICall() {}
	public static APICall getInstance() {
		if(instance ==null) {
			instance = new APICall();
		}
		return instance;
	}

	

	public void setAPICall(String location) {
		this.url = "https://findwork.dev/api/jobs/?location=" + location + "&search=java" ;
	}
	public void setAPICall(String location,  boolean remote) {
		this.url = "https://findwork.dev/api/jobs/?location=" + location + "&search=java" +"&remote=" + remote;

	}
	public void setAPICall(String location, String employment_type) {
		this.url = "https://findwork.dev/api/jobs/?location=" + location + "&search=java" +"&employment_type=" + employment_type;
	}
	public void setAPICall(String location, String employment_type, boolean remote) {
		this.url = "https://findwork.dev/api/jobs/?location=" + location + "&search=java" +"&remote=" + remote+"&employment_type=" + employment_type;
	}
	

	public JSONArray getData() {
		JSONObject obj = null;

		String api = this.url;
		String data_filter ="";
		String line = "";

		try {
			HttpURLConnection openConnection = (HttpURLConnection) new URL(api).openConnection();
			openConnection.setRequestMethod("GET");
			openConnection.addRequestProperty("User-Agent", "default");
			openConnection.setRequestProperty("Authorization", "Token 838d2cb24efd24d35d81f7cc6846cd6ec54cd4dd ");
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

			 obj = (JSONObject) JSONValue.parseWithException(data_filter);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (JSONArray) obj.get("results");

	}
	
}

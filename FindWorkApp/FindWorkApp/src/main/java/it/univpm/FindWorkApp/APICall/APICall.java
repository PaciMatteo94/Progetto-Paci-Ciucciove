package it.univpm.FindWorkApp.APICall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import it.univpm.FindWorkApp.model.City;
import it.univpm.FindWorkApp.model.WorkInformation;

public class APICall {
	private String location;
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
		this.location = location;
		this.url = "https://findwork.dev/api/jobs/?location=" + location + "&search=java" ;
	}
	public void setAPICall(String location,  boolean remote) {
		this.location = location;
		this.url = "https://findwork.dev/api/jobs/?location=" + location + "&search=java" +"&remote=" + remote;

	}
	public void setAPICall(String location, String employment_type) {
		this.location = location;
		this.url = "https://findwork.dev/api/jobs/?location=" + location + "&search=java" +"&employment_type=" + employment_type;
	}
	public void setAPICall(String location, String employment_type, boolean remote) {
		this.location = location;
		this.url = "https://findwork.dev/api/jobs/?location=" + location + "&search=java" +"&remote=" + remote+"&employment_type=" + employment_type;
	}
	

	public City getData() {
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
		return workParser(obj,location);

	}

	private static City workParser(JSONObject obj, String location) {
	    WorkInformation work;
		City city = new City(location);
		long count = (Long) obj.get("count");
		int capacity = (int) count;
		ArrayList<JSONObject> arrayJson = new ArrayList<JSONObject>(capacity);
		ArrayList<WorkInformation> workArray = new ArrayList<WorkInformation>(capacity);
		ArrayList<String> keywords = null;
		JSONArray stringArray;
		JSONArray array = (JSONArray) obj.get("results");
		for (int i = 0; i < capacity; i++) {
			arrayJson.add((JSONObject) array.get(i));
		}
		for (JSONObject json : arrayJson) {
			work = new WorkInformation();
			stringArray = (JSONArray) json.get("keywords");
			keywords = new ArrayList<String>(stringArray.size());
			for (int i = 0; i < stringArray.size(); i++) {
				keywords.add((String) stringArray.get(i));
			}
			work.setKeywords(keywords);
			work.setRole((String) json.get("role"));
			work.setCompanyName((String) json.get("company_name"));
			work.setEmployementType((String) json.get("employment_type"));
			work.setRemote((Boolean) json.get("remote"));
			work.setDataPosted((String) json.get("date_posted"));
			work.setText((String)json.get("text"));
			workArray.add(work);
		}
		city.setWork(workArray);
		return city;
	}
}

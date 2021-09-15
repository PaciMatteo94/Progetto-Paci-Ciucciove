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

import it.univpm.FindWorkApp.Model.City;
import it.univpm.FindWorkApp.Model.WorkInformation;

/**
 * <p>
 * La classe <b>APICall</b> implementa l'interfaccia <b>APICallService</b>.
 * 
 * @author Paci Matteo
 * @author Ciucciovè Leonardo
 *
 */
public class APICall implements APICallService {
	/**
	 * La classe è implementata come singleton, quindi verrà creata una singola
	 * istanza di tale classe che poi verrà usata dagli altri metodi durante
	 * l'esecuzione del programma.
	 */
	private String location;
	private String url;
	private static APICall instance = null;

	private APICall() {
	}

	public static APICall getInstance() {
		if (instance == null) {
			instance = new APICall();
		}
		return instance;
	}

	/**
	 * <p>
	 * Il metodo <b>setAPICall</b> salva la location che gli viene passata e imposta
	 * l'url con cui chiamare l'API.
	 * 
	 * @param location        nome della città in cui fare la ricerca
	 * @param remote          parametro che indica il filtro che riguarda la
	 *                        tipologia di lavoro in remoto o non
	 * 
	 */

	public void setAPICall(String location, Boolean remote) {
		this.location = location;
		this.url = "https://findwork.dev/api/jobs/?location=" + location + "&search=java" + "&remote=" + remote;

	}
	/**
	 * <p>
	 * Il metodo <b>setAPICall</b> salva la location che gli viene passata e imposta
	 * l'url con cui chiamare l'API.
	 * 
	 * @param location        nome della città in cui fare la ricerca
	 * @param remote          parametro che indica il filtro che riguarda la
	 *                        tipologia di lavoro in remoto o non
	 * @param employment_type paremetro che indica il filtro che riguarda la
	 *                        tipologia di lavoro full time o part time
	 * 
	 */

	public void setAPICall(String location, String employment_type, Boolean remote) {
		this.location = location;
		this.url = "https://findwork.dev/api/jobs/?location=" + location + "&search=java" + "&remote=" + remote
				+ "&employment_type=" + employment_type;
	}

	/**
	 * <p>
	 * Implementazione del metodo dell'interfaccia <b>APICallService</b>.
	 * 
	 * <p>
	 * Il metodo setta i parametri per la chiamata all'API inserendo la tipologia di
	 * richiesta, l'autorizzazione e il tipo di dato che accettiamo. Dopo aver fatto
	 * la chiamata all'API e salvato i dati in un oggetto JSON, invocherà il metodo
	 * privato <b>workParser</b>.
	 * 
	 */
	public City getData() {
		JSONObject obj = null;

		String api = this.url;
		String data_filter = "";
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

			} finally {
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
		return workParser(obj, location);

	}

	/**
	 * <p>
	 * Il metodo privato <b>workParser</b> ha l'obbiettivo di convertire l'oggetto
	 * JSON che l'API restituisce in un oggetto di tipo <b>City</b> che contiene
	 * tutte le informazioni di nostro interesse.
	 * 
	 * @param obj      oggetto che l'API restituisce
	 * @param location nome della città sui cui abbiamo fatto la ricerca
	 * @return <code>City</code> oggetto di tipo <b>City</b> dove sono salvate tutte
	 *         le informazioni della ricerca.
	 */

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
			work.setEmploymentType((String) json.get("employment_type"));
			work.setRemote((Boolean) json.get("remote"));
			work.setDataPosted((String) json.get("date_posted"));
			work.setText((String) json.get("text"));
			workArray.add(work);
		}
		city.setCount((Long) obj.get("count"));
		city.setWork(workArray);

		return city;
	}
}

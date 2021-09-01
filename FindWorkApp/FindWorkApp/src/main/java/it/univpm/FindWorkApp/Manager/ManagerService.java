package it.univpm.FindWorkApp.Manager;

import org.json.simple.JSONObject;

public interface ManagerService {
	public JSONObject getCities(String[] location);
	public JSONObject getCities(String[] location, String employment_type);
	public JSONObject getCities(String[] location,boolean remote);
	public JSONObject getCities(String[] location,boolean remote, String employment_type);
	public JSONObject getStats();
	public JSONObject getStats(String[] location);
	public JSONObject getStats(String date);
	public JSONObject getStats(boolean remote);
	public JSONObject getStats(String[] location,String date);
	public JSONObject getStats(String[] location,boolean remote);
	public JSONObject getStats(boolean remote, String date);
	public JSONObject getStats(String[] location, boolean remote, String date );
	

}

package it.univpm.FindWorkApp.model;

public class CitiesJO {
private String[] location;
private String htime;
//private String search;
private Boolean remote;


public CitiesJO (String[] location,String htime, Boolean remote) {
	this.location=location;
	//this.search=search;
	this.remote=remote;
	this.htime=htime;
}


public String[] getLocation() {
	return location;
}
public void setLocation(String[] location) {
	this.location = location;
}
//public String getSearch() {
	//return search;
//}
//public void setSearch(String search) {
	//this.search = search;
//}
public Boolean isRemote() {
	return remote;
}
public void setRemote(Boolean remote) {
	this.remote = remote;
}
public String isHtime() {
	return htime;
}
public void setHtime(String htime) {
	this.htime = htime;
}


}


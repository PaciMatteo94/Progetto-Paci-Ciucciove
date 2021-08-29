package it.univpm.FindWorkApp.model;
//Probabilmente provvisoria
public class Pref {
private String city[];

public Pref(String city[]) {
	this.city=city;
	
}

public String[] getCity() {
	return city;
}

public void setCity(String[] city) {
	this.city = city;
    }
}
package it.univpm.FindWorkApp.Model;

//Possibilità modello
public class StatsProgrammingLenguage {

static String[] commonLanguages = {"python","php","spring","typescript","sql"};
double percPyton;
double percPhp;
double percSpring;
double percTypescript;
double percSql;

public static String[] getCommonLanguages() {
	return commonLanguages;
}
public double getPercPython() {
	return percPyton;
}
public void setPercPython(double percPyton) {
	this.percPyton = percPyton;
}
public double getPercPhp() {
	return percPhp;
}
public void setPercPhp(double percPhp) {
	this.percPhp = percPhp;
}
public double getPercSpring() {
	return percSpring;
}
public void setPercSpring(double percSpring) {
	this.percSpring = percSpring;
}
public double getPercTypescript() {
	return percTypescript;
}
public void setPercTypescript(double percTypescript) {
	this.percTypescript = percTypescript;
}
public double getPercSql() {
	return percSql;
}
public void setPercSql(double percSql) {
	this.percSql = percSql;
}
}

package it.univpm.FindWorkApp.Model;

/**
 * <p>
 * <b>Classe</b> che rapresenta l'oggetto <i>statistiche dei linguaggi</i> che ha come parametri 
 * il <i>i linguaggi più comuni</i>, 
 * la percentuale del <i>pyton</i>
 * la percentuale del<i>php</i>
 * la percentuale di<i>spring</i>
 * la percentuale del<i>typescript</i>
 * la percentuale dell'<i>sql</i>
 * 
 * <p>
 * @author Paci Matteo
 * @author Ciucciovè Leonardo
 *
 */

public class StatsProgrammingLenguage {

static String[] commonLanguages = {"python","php","spring","typescript","sql"};
double percPython;
double percPhp;
double percSpring;
double percTypescript;
double percSql;

/**
 * ritorna un array di stringhe dei linguaggi di programmazione (statico)
 * 
 * @return uno <code>String[]</code> con i nomi predefiniti già settati 
 */

public static String[] getCommonLanguages() {
	return commonLanguages;
}

/**
 * ritorna la percentuale del pyton
 * 
 * @return un <code>double</code> con tale numero in '%'
 */

public double getPercPython() {
	return percPython;
}

/**
 * permette di scegliere la percentuale del pyton
 * 
 * @param name indica tale percentuale
 */

public void setPercPython(double percPython) {
	this.percPython = percPython;
}

/**
 * ritorna la percentuale del php
 * 
 * @return un <code>double</code> con tale numero in '%'
 */

public double getPercPhp() {
	return percPhp;
}

/**
 * permette di scegliere la percentuale del php
 * 
 * @param name indica tale percentuale
 */

public void setPercPhp(double percPhp) {
	this.percPhp = percPhp;
}

/**
 * ritorna la percentuale dello spring
 * 
 * @return un <code>double</code> con tale numero in '%'
 */

public double getPercSpring() {
	return percSpring;
}

/**
 * permette di scegliere la percentuale dello spring
 * 
 * @param name indica tale percentuale
 */

public void setPercSpring(double percSpring) {
	this.percSpring = percSpring;
}

/**
 * ritorna la percentuale del typescript
 * 
 * @return un <code>double</code> con tale numero in '%'
 */

public double getPercTypescript() {
	return percTypescript;
}

/**
 * permette di scegliere la percentuale del typescript
 * 
 * @param name indica tale percentuale
 */

public void setPercTypescript(double percTypescript) {
	this.percTypescript = percTypescript;
}

/**
 * ritorna la percentuale dell'sql
 * 
 * @return un <code>double</code> con tale numero in '%'
 */

public double getPercSql() {
	return percSql;
}

/**
 * permette di scegliere la percentuale dell'sql
 * 
 * @param percSql indica tale percentuale
 */

public void setPercSql(double percSql) {
	this.percSql = percSql;
}
}

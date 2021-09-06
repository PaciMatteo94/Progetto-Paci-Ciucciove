package it.univpm.FindWorkApp.Stats;

import org.apache.commons.math3.util.Precision;
import it.univpm.FindWorkApp.Model.City;
import it.univpm.FindWorkApp.Model.WorkInformation;

public class stats {
	
	public static City statsCalculate(City city) {
		int cnt=0,cnt2=0;
		for (WorkInformation w : city.getWork()) {
		if(w.getEmployementType()!=null) {
        	 if(w.getEmployementType().equals("full time")) {
        		cnt++; 
        	 }
        	 else if( w.getEmployementType().equals("contract")) {
        		cnt2++; 
        	 }
         }
		}	
         city.setFullTimeAmount(cnt);
         city.setPartTimeAmount(cnt2);

         double pFTA = (double) cnt/city.getWork().size()*100;
         double pPTA = (double) cnt2/city.getWork().size()*100;
         double percFTA = Precision.round(pFTA,2);
         double percPTA = Precision.round(pPTA,2);
         city.setFullTimePerc(percFTA);
         city.setPartTimePerc(percPTA);
       return city;
	}


}
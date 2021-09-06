package it.univpm.FindWorkApp.Stats;

import java.util.ArrayList;

import it.univpm.FindWorkApp.Model.City;
import it.univpm.FindWorkApp.Model.WorkInformation;

public class stats {
	
	public static void statsCalculate(City city) {
		int cnt=0,cnt2=0;
		ArrayList<WorkInformation> works = city.getWork();
		for (WorkInformation w : works) {
			String prova = w.getEmployementType();
        	 if(prova == "full time" ) {
        		cnt++; 
        	 }
        	 if(prova==("contract")) {
        		cnt2++; 
        	 }
         }
     		
         city.setFullTimeAmount(cnt);
         city.setPartTimeAmount(cnt2);
         
         double percFTA = (double)cnt/city.getWork().size()*100;
         double percPTA = (double)cnt2/city.getWork().size()*100;
         city.setFullTimePerc(percFTA);
         city.setPartTimePerc(percPTA);
	}


}
package it.univpm.FindWork.Stats;

import java.util.ArrayList;

import it.univpm.FindWorkApp.model.City;
import it.univpm.FindWorkApp.model.WorkInformation;

public class stats {
	
	public static void statsCalculate(City city) {
		int cnt=0,cnt2=0;
		ArrayList<WorkInformation> works=city.getWork();
		for (WorkInformation w : works) {
        	 if(w.getEmployementType() == "full time") {
        		cnt++; 
        	 }
        	 if(w.getEmployementType() == "part time" || w.getEmployementType() == "contract") {
        		cnt2++; 
        	 }
         }
     		
         city.setFullTimeAmount(cnt);
         city.setPartTimeAmount(cnt2);
         
         double percFTA = (cnt/city.getWork().size())*100;
         double percPTA = (cnt2/city.getWork().size())*100;
         city.setFullTimePerc(percFTA);
         city.setPartTimePerc(percPTA);
	}


}
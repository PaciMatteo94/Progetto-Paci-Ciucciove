package it.univpm.FindWorkApp;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.univpm.FindWorkApp.APICall.*;

@SpringBootApplication
public class FindWorkApplication {

	public static void main(String[] args) {
		SpringApplication.run(FindWorkApplication.class, args);
		APICall prova = new APICall("London","react",true);
		prova.getData();
	}

}

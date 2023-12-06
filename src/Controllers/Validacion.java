package Controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validacion {
	
	private static Validacion instance;
	
	public static Validacion getInstance() {
		if(instance == null) {
			instance = new Validacion();
		}
		return instance;
	}
	
	public int esUnNumero(String entrada) {
		
		if(entrada==null) { return -1;}
		try {
			Double numero = Double.parseDouble(entrada);
			
			if(numero <= 0) { return -3;}
			
			return 1;
			}
		
		catch(NumberFormatException e ){
			return  -2;
		}
	}
	public int esString(String entrada) {
		
		if(entrada==null) { return -1;}
		return 1;
		
	}
	public int esUnCorreo (String entrada) {
		
		if(entrada==null) { return -1;}
		
		String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(entrada);

        if(matcher.matches()) {return 1;}
        else {return -2;}
	}
	
	public int esUnDNI(String entrada) {
		if(entrada == null) {return -1;}
		
		 String regex = "^(\\d{7,8}|[A-Z]{2}\\d{7,8}|[A-Z]{2}\\d{6}[A-Z])$";

	        Pattern pattern = Pattern.compile(regex);

	        Matcher matcher = pattern.matcher(entrada);

	        if(matcher.matches()) {return 1;}
	        else {return -2;}
	}
	

}

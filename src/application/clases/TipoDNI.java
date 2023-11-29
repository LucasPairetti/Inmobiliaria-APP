package application.clases;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum TipoDNI {
	DNI,Libreta_Civica,Libreta_Enrolamiento,Pasaporte;
	
	public List<String> getTiposDNI(){
		 return  Arrays.stream(TipoDNI.values())
	                .map(Enum::toString)
	                .map(s -> s.replace("_", " "))
	                .collect(Collectors.toList());
	 }
}

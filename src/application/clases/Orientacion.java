package application.clases;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Orientacion {
	
	Norte, Sur, Este, Oeste, Noreste, Noroeste, Sureste, Suroeste;
		
		public List<String> geOrientacion(){
			 return  Arrays.stream(Orientacion.values())
		                .map(Enum::toString)
		                .map(s -> s.replace("_", " "))
		                .collect(Collectors.toList());
		 
	}
}

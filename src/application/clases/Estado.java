package application.clases;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Estado {
	Disponible,Reservado,Vendido;
	public static List<String> getEstado(){
		 return  Arrays.stream(Estado.values())
	                .map(Enum::toString)
	                .map(s -> s.replace("_", " "))
	                .collect(Collectors.toList());
	 }
}

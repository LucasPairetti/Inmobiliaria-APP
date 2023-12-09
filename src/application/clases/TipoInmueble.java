package application.clases;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum TipoInmueble {
	L,C,D,T,Q,G,O;
	public static List<String> getTipoInmueble(){
		 return  Arrays.stream(TipoInmueble.values())
	                .map(Enum::toString)
	                .map(s -> s.replace("_", " "))
	                .collect(Collectors.toList());
	 }
}

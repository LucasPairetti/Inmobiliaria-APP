package application.clases;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum TipoInmueble {
	L,C,D,T,Q,G;
	public List<String> getTipoInmueble(){
		 return  Arrays.stream(TipoInmueble.values())
	                .map(Enum::toString)
	                .map(s -> s.replace("_", " "))
	                .collect(Collectors.toList());
	 }
}

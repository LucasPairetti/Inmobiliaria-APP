package application.clases;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Provincia {
	Buenos_Aires,Catamarca, Chaco, Chubut, Córdoba, Corrientes, Entre_Ríos, Formosa, Jujuy, La_Pampa, La_Rioja, Mendoza,
	Misiones, Neuquén, Río_Negro, Salta, San_Juan, San_Luis, Santa_Cruz, Santa_Fe, Santiago_del_Estero, Tierra_del_Fuego,
	Antártida, Tucumán;
	public List<String> getProvincias(){
		 return  Arrays.stream(Provincia.values())
	                .map(Enum::toString)
	                .map(s -> s.replace("_", " "))
	                .collect(Collectors.toList());
	 }
	public boolean
}
 

package application.clases;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

import application.dao.InmuebleDAO;

public enum Localidad {
	Santa_Fe,Santo_Tome,Sauce_Viejo,Rincon,Colastine_Norte,Colastine_Sur;
	
	public List<String> getLocalidad(){
		 InmuebleDAO inmuebledao = InmuebleDAO.getInmuebleDAO()
		 List<Inmueble> inmuebles = inmuebledao.getAllInmuebles();
		 List<String> localidades = inmuebles.stream()
	                .map(Inmueble::getLocalidad)
	                .distinct()
	                .collect(Collectors.toList());
	        EnumSet<Localidad> conjuntoDeLocalidades = Arrays.stream(Localidad.values())
	                .filter(localidad -> !localidades.contains(localidad.name().replace("_", " ")))
	                .collect(Collectors.toCollection(() -> EnumSet.noneOf(Localidad.class)));

	        conjuntoDeLocalidades.forEach(localidad -> localidades.add(localidad.name().replace("_"," ")));
	        return localidades;
	}
}

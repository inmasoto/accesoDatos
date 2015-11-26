package persistencia;

import java.util.List;


import utilidades.Departamento;
import utilidades.ExcepcionDAO;

 public interface DepartamentoDAOInterface {
	 
	 /* Le pasamos una lista de departamentos y lo vamos a guardar en un fichero */
	public void escribirDatos(List<Departamento> listaDepartamento) throws ExcepcionDAO;
	 /* Lee el contenido de un fichero y lo vuelca en una lista, nos devuelve dicha lista */
	public List<Departamento> leerDatos()throws ExcepcionDAO;
	 /* Lee el contenido de un fichero y lo vuelca en una lista, comprueba si el nuevo dato coincide sus claves
	  * en ese caso actualiza la lista y vuelve a guardar esa lista en el fichero, de manera que los 
	  * datos estaran actualizados */
	public void actualizarDatos(Departamento d) throws ExcepcionDAO;
	
	/* Lee el contenido de un fichero y lo vuelca en una lista, comprueba si el nuevo dato coincide el numero
	 * con alguno de la lista, si es asi borra ese elemento de la lista y vuelve a guardar la lista
	 * en el fichero*/
	
	public void borrarDatos(int n) throws ExcepcionDAO;
	
	/* Borramos todos los departamentos*/
	
	public void borrarTodosLosDatos() throws ExcepcionDAO;
	
}

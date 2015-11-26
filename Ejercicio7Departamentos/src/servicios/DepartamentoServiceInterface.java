package servicios;

import java.util.List;

import utilidades.Departamento;
import utilidades.ExcepcionDAO;

public interface DepartamentoServiceInterface {
	/*
	 * Le pasamos una lista de departamentos y lo vamos a guardar en un fichero
	 */
	public void salvar(List<Departamento> listaDepartamento) throws ExcepcionDAO;

	/*
	 * Lee el contenido de un fichero y lo vuelca en una lista, nos devuelve
	 * dicha lista
	 */
	public List<Departamento> cargar() throws ExcepcionDAO;

	/*
	 * Lee el contenido de un fichero y lo vuelca en una lista, comprueba si el
	 * nuevo dato coincide sus claves en ese caso actualiza la lista y vuelve a
	 * guardar esa lista en el fichero, de manera que los datos estaran
	 * actualizados
	 */
	public void actualizar(Departamento d) throws ExcepcionDAO;

	/*
	 * Lee el contenido de un fichero y lo vuelca en una lista, comprueba si el
	 * nuevo dato coincide el numero con alguno de la lista, si es asi borra ese
	 * elemento de la lista y vuelve a guardar la lista en el fichero
	 */

	public void borrar(int numero) throws ExcepcionDAO;
	
	/*Borramos todos los departamentos */
	
	public void borrarTodo()throws ExcepcionDAO;

}

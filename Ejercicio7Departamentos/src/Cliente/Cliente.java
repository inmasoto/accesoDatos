/**
 * 
 */
package Cliente;

import java.util.ArrayList;
import java.util.List;

import persistencia.DepartamentoFicheroBinarioDAO;
import persistencia.DepartamentoXMLStream;
import servicios.DepartamentoService;
import servicios.DepartamentoServiceInterface;
import utilidades.Departamento;
import utilidades.ExcepcionDAO;

/**
 * @author Inmaculada Soto
 *
 */
public class Cliente {

	/**
	 * @param args
	 * @throws Exception
	 */

	private DepartamentoServiceInterface departamentoService, departamentoService2;

	Cliente() {
		String nombreFichero = "departamentos.dat";

		departamentoService = new DepartamentoService(new DepartamentoXMLStream(nombreFichero));
		departamentoService2 = new DepartamentoService(new DepartamentoFicheroBinarioDAO(nombreFichero));
	}

	public void comprobarEscritura() throws ExcepcionDAO {
		List<Departamento> lista = new ArrayList<Departamento>();
		
		lista=departamentoService2.cargar();
		// Cargamos los elementos en la lista
		// lista=departamentoService.cargarDepartamento(nombreFichero);
		// Guardamos los elementos en el XML
		departamentoService.salvar(lista);

	}

	public void comprobarLectura() throws ExcepcionDAO {
		// Creamos una lista

		List<Departamento> listaXMLStream = new ArrayList<Departamento>();

		// Leemos los elementos del XML
		listaXMLStream = departamentoService.cargar();

		// Recorremos la lista para visualizar los elementos.

		System.out.println("Los departamentos son: ");
		for (int i = 0; i < listaXMLStream.size(); i++) {
			System.out.println(listaXMLStream.get(i).getNombre());
			System.out.println(listaXMLStream.get(i).getNumero());
			System.out.println(listaXMLStream.get(i).getLocalidad());
		}

	}

	public void comprobarActualizacion() throws ExcepcionDAO {

		/*
		 * Intentamos actualizar un departamento para ello tenemos que
		 * actualizar el .dat por eso tenemos dos servicios diferentes uno que
		 * se encarga de los datos que contiene y otro de pasarlo a xstream
		 */

		List<Departamento> lista = new ArrayList<Departamento>();
		List<Departamento> listaXMLStream = new ArrayList<Departamento>();
		departamentoService2.actualizar(new Departamento("Contabilidad", 1, "Rota"));

		// Recuperamos en una lista el .dat modificado
		lista = departamentoService2.cargar();

		// Convertimos el .dat modificado con XStream en XML
		departamentoService.salvar(lista);

		// Recuperamos el XStream para comprobar que es correcto
		listaXMLStream = departamentoService.cargar();

		// Recorremos la lista nuevamente para visualizar los elementos ya
		// modificados.

		System.out.println("Los departamentos modificados son: ");
		for (int i = 0; i < listaXMLStream.size(); i++) {
			System.out.println(listaXMLStream.get(i).getNombre());
			System.out.println(listaXMLStream.get(i).getNumero());
			System.out.println(listaXMLStream.get(i).getLocalidad());
		}

	}

	public void comprobarBorrado() throws ExcepcionDAO {

		List<Departamento> lista = new ArrayList<Departamento>();
		List<Departamento> listaXMLStream = new ArrayList<Departamento>();
		// borramos el primer departamento que tenemos en el .dat
		departamentoService2.borrar(1);
		// Cargamos el .dat una vez borrado un dato
		lista = departamentoService2.cargar();
		// Volvemos a convertir una vez borrado en un xml mediante XStream
		departamentoService.salvar(lista);

		// Recuperamos el XStream para comprobar que es correcto
		listaXMLStream = departamentoService.cargar();

		// Recorremos la lista nuevamente para visualizar los elementos ya
		// modificados.

		System.out.println("Los departamentos una vez borrado uno son: ");
		for (int i = 0; i < listaXMLStream.size(); i++) {
			System.out.println(listaXMLStream.get(i).getNombre());
			System.out.println(listaXMLStream.get(i).getNumero());
			System.out.println(listaXMLStream.get(i).getLocalidad());
		}

	}

	public static void main(String[] args) throws Exception {

		Cliente c = new Cliente();

		try {
			c.comprobarEscritura();
			c.comprobarLectura();
			c.comprobarActualizacion();
			c.comprobarBorrado();

		} catch (ExcepcionDAO e) {
			System.err.println(e.getLocalizedMessage());

		}
	}

}

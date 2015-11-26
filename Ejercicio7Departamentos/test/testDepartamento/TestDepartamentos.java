package testDepartamento;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import persistencia.DepartamentoFicheroBinarioDAO;
import persistencia.DepartamentoXMLStream;
import servicios.DepartamentoService;
import servicios.DepartamentoServiceInterface;
import utilidades.Departamento;
import utilidades.ExcepcionDAO;

public class TestDepartamentos {

	// DepartamentoServiceInterface departamentoService=
	private static DepartamentoServiceInterface departamentoService1 = null;
	private static DepartamentoServiceInterface departamentoService2 =null;

	// Se ejecuta una sola vez al principio
	@BeforeClass
	public static void inicializandoTestsDeClase() {
		System.out.println("Empezamos los test");
		departamentoService1 = new DepartamentoService(new DepartamentoFicheroBinarioDAO("test.dat"));
		departamentoService2 = new DepartamentoService(new DepartamentoXMLStream("test.dat"));
		try {
			departamentoService1.borrarTodo();
		} catch (ExcepcionDAO e) {
			System.out.println(e.getMessage());
		}
	}

	// Es llamado siempre antes de iniciar un test
	@Before
	public void antesTest() throws Exception {
		System.out.println("Antes de cada test");

	}

	@Test
	public void testRecuperarXML() throws ExcepcionDAO {

		/*
		 * En este metodo creamos una lista de prueba para cargarla y asi poder
		 * comprobar que carga correctamente, a continuacion llamamos al metodo
		 * cargar y la guardamos en otra lista, de las cuales vamos a realizar
		 * las pruebas Todo va bien si la lista nueva es distinta de nula, eso
		 * comprueba que se cargo bien dado que se ha podido recuperar los
		 * elementos. por ultimo comprobamos que la lista de prueba sea igual
		 * que la nueva,asi vemos que los elementos de dentro de la lista son
		 * correctos y no se copio otros elementos.
		 */
		System.out.println("Empieza testRecuperarDepartamento");

		Departamento dep = new Departamento("Informatica", 1, "Jerez");
		Departamento dep2 = new Departamento("Recursos humanos", 2, "PuertoReal");
		Departamento dep3 = new Departamento("Finanzas", 3, "Chiclana");
		List<Departamento> listaDepartamento = new ArrayList<Departamento>();
		listaDepartamento.add(dep);
		listaDepartamento.add(dep2);
		listaDepartamento.add(dep3);

		// Comprobamos el metodo salvar.
		departamentoService1.salvar(listaDepartamento);
		departamentoService2.salvar(listaDepartamento);

		// En la nueva lista metemos el resultado que nos devuelve el cargar
		// (Devuelve una lista).
		List<Departamento> NuevaLista = departamentoService2.cargar();

		// Comprobamos que esa lista no este vacia (Se ha guardado y cargado
		// correctamente si no es nula)
		Assert.assertNotNull(NuevaLista);

		// Comprobamos que el contenido es el mismo.

		for (int i = 0; i < NuevaLista.size(); i++) {
			Assert.assertEquals(listaDepartamento.get(i).getNombre(), NuevaLista.get(i).getNombre());
			Assert.assertEquals(listaDepartamento.get(i).getNumero(), NuevaLista.get(i).getNumero());
			Assert.assertEquals(listaDepartamento.get(i).getLocalidad(), NuevaLista.get(i).getLocalidad());
		}

	}
	
	
@Test
	
	public void testActualizarXML() throws ExcepcionDAO {
		
		/* En este metodo creamos una lista de prueba para cargarla y asi poder comprobar que carga correctamente,
		 * a continuacion llamamos al metodo actualizar, comprobamos que la lista que hemos guardado sea su nombre y localidad diferente que 
		 * el nuevo departamento que hemos actualizado, si es asi es que se actualizo correctamente.
		 */
		
		System.out.println("Empieza testActualizarDepartamento");
		
		Departamento dep = new Departamento("Informatica", 1, "Jerez");
		Departamento dep2 = new Departamento("Recursos humanos", 2, "PuertoReal");
		Departamento dep3 = new Departamento("Finanzas", 3, "Chiclana");
		List<Departamento> listaDep = new ArrayList<Departamento>();
		listaDep.add(dep);
		listaDep.add(dep2);
		listaDep.add(dep3);
		
		departamentoService1.salvar(listaDep);
		//Creamos un nuevo departamento
		Departamento d=new Departamento("Contabilidad",1,"Rota");
		//Actualizamos el XML
		departamentoService1.actualizar(d);

		List<Departamento> listaFichero=new ArrayList<Departamento>();
		List<Departamento> listaDepartamento=new ArrayList<Departamento>();
		
		listaFichero=departamentoService1.cargar();
		
		departamentoService2.salvar(listaFichero);
		listaDepartamento=departamentoService2.cargar();
		
		//Comprobamos que el contenido es diferente cuando el numero coincide.Asi comprobamos que ha actualizado bien
		
		for(int i=0;i<listaDepartamento.size();i++){
			if(listaDep.get(i).getNumero()!=listaDepartamento.get(i).getNumero()){

				Assert.assertEquals(listaDep.get(i).getNombre(), listaDepartamento.get(i).getNombre());
				Assert.assertEquals(listaDep.get(i).getLocalidad(), listaDepartamento.get(i).getLocalidad());
			}
		}
		
		
		


	}
	
@Test
public void testBorrarXML() throws ExcepcionDAO {
	
	/* En este metodo creamos una lista de prueba para cargarla y asi poder comprobar que carga correctamente,
	 * a continuacion llamamos al metodo actualizar, comprobamos que la lista que hemos guardado sea su nombre y localidad diferente que 
	 * el nuevo departamento que hemos actualizado, si es asi es que se actualizo correctamente.
	 */
	System.out.println("Empieza testBorrarDepartamento");
	
	
	Departamento dep = new Departamento("Informatica",1,"Jerez");
	Departamento dep2 = new Departamento("Recursos humanos",2,"PuertoReal");
	Departamento dep3 = new Departamento("Finanzas",3,"Chiclana");
	List<Departamento> listaDepartamento = new ArrayList<Departamento>();
	listaDepartamento.add(dep);
	listaDepartamento.add(dep2);
	listaDepartamento.add(dep3);
	
	//Guardamos la lista
	departamentoService1.salvar(listaDepartamento);
	
	//borramos un elemento el XML
		departamentoService1.borrar(1);
	
	
	List<Departamento> listaFichero=new ArrayList<Departamento>();
	
	
	listaFichero=departamentoService1.cargar();
	
	departamentoService2.salvar(listaFichero);

	
	
	//En la nueva lista metemos el resultado que nos devuelve el cargar (Devuelve una lista).
	List<Departamento> NuevaLista = departamentoService2.cargar();
	System.out.println(NuevaLista.size());
	
	//Comprobamos que esa lista no este vacia (Se ha guardado y cargado correctamente si no es nula)
	Assert.assertNotNull(NuevaLista);
	
	//Comprobamos que el contenido no es el mismo.
	
	Assert.assertNotEquals(NuevaLista.size(),listaDepartamento.size());

}

	
	@After
	public void despuesDeCadaTest() {
		System.out.println("Después del test");

	}

	// Se ejectura una vez al final
	@AfterClass
	public static void finalizandoTests() {
		System.out.println("Se ejecuta al final una sola vez");
	}

}

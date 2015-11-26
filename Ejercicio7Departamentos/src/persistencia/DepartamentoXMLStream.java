package persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;

import utilidades.Departamento;
import utilidades.ExcepcionDAO;
import utilidades.ListaDepartamento;

public class DepartamentoXMLStream implements DepartamentoDAOInterface {

	private String ficheroSalida = null;

	public DepartamentoXMLStream(String nombreFichero) {
		super();
	}

	@Override
	public void escribirDatos(List<Departamento> listaDepartamento) throws ExcepcionDAO {
		/*
		 * En este metodo nos encargamos de escribir los datos desde un
		 * fichero.dat a uno .xml usando XStream.
		 */
		// Creamos la lista que tiene todos los departamentos.
		ficheroSalida = "Departamento.xml";
		ListaDepartamento listaDep = new ListaDepartamento();
		
		listaDep.setLista(listaDepartamento);
	
		try {
			// Creamos el fichero xml utilizando XStream
			XStream xstream = new XStream();
			xstream.alias("ListaDepartamento", ListaDepartamento.class);
			xstream.alias("datosdepartamento", Departamento.class);
			xstream.addImplicitCollection(ListaDepartamento.class, "lista");
			xstream.toXML(listaDep, new FileOutputStream(ficheroSalida));
			System.out.println("Creado fichero xml");

		} catch (Exception e) {
			throw new ExcepcionDAO(e.getMessage(), e);
		}

	}

	@Override
	public List<Departamento> leerDatos() throws ExcepcionDAO {
		/*
		 * En este metodo nos encargamos de leer los datos de un xml y lo
		 * pasamos a una lista usando XStream. Despues recorremos la lista para
		 * visualizar los datos.
		 */
		List<Departamento> listaDepart = new ArrayList<Departamento>();
		try {
			XStream xstream = new XStream();
			xstream.alias("ListaDepartamento", ListaDepartamento.class);
			xstream.alias("datosdepartamento", Departamento.class);
			xstream.addImplicitCollection(ListaDepartamento.class, "lista");
			// Creamos la lista en la que metemos todo el xml.
			ListaDepartamento listaDep = (ListaDepartamento) xstream
					.fromXML(new FileInputStream(new File(ficheroSalida)));

			System.out.println("el numero de departamentos " + listaDep.getLista().size());

			// obtenemos la lista.
			listaDepart = listaDep.getLista();
			// Recorremos la lista
			/*
			 * for(int i=0;i<listaDepart.size();i++){
			 * System.out.println(listaDepart.get(i).getNombre());
			 * System.out.println(listaDepart.get(i).getNumero());
			 * System.out.println(listaDepart.get(i).getLocalidad()); }
			 */
		} catch (Exception e) {
			throw new ExcepcionDAO(e.getMessage(), e);
		}
		return listaDepart;
	}

	@Override
	public void actualizarDatos(Departamento d) throws ExcepcionDAO {

		List<Departamento> listaParaActualizar = leerDatos();

		for (int i = 0; i < listaParaActualizar.size(); i++) {
			if (listaParaActualizar.get(i).getNumero() == d.getNumero()) {
				listaParaActualizar.get(i).setNombre(d.getNombre());
				listaParaActualizar.get(i).setLocalidad(d.getLocalidad());
			}
		}

		try {
			// Creamos el fichero xml utilizando XStream
			XStream xstream = new XStream();
			xstream.alias("ListaDepartamento", ListaDepartamento.class);
			xstream.alias("datosdepartamento", Departamento.class);
			xstream.addImplicitCollection(ListaDepartamento.class, "lista");
			xstream.toXML(listaParaActualizar, new FileOutputStream(ficheroSalida));
			System.out.println("Creado fichero xml");

		} catch (Exception e) {
			throw new ExcepcionDAO(e.getMessage(), e);
		}

	}

	@Override
	public void borrarDatos(int n) throws ExcepcionDAO {
		// Documentar el proque y quitar lo de abajo y cambiar el nombre
		

		List<Departamento> listaParaBorrar = new ArrayList<Departamento>();
		listaParaBorrar = leerDatos();

		for (int i = 0; i < listaParaBorrar.size(); i++) {
			System.out.println(listaParaBorrar.get(i).getNombre());
		}
		for (int i = 0; i < listaParaBorrar.size(); i++) {
			if (listaParaBorrar.get(i).getNumero() == n) {
				listaParaBorrar.remove(i);
			}
		}

		try {
			// Creamos el fichero xml utilizando XStream
			XStream xstream = new XStream();
			xstream.alias("ListaDepartamento", ListaDepartamento.class);
			xstream.alias("datosdepartamento", Departamento.class);
			xstream.addImplicitCollection(ListaDepartamento.class, "lista");
			xstream.toXML(listaParaBorrar, new FileOutputStream(ficheroSalida));
			System.out.println("Creado fichero xml");

		} catch (Exception e) {
			throw new ExcepcionDAO(e.getMessage(), e);
		}

	}

	@Override
	public void borrarTodosLosDatos() throws ExcepcionDAO {
		// TODO Auto-generated method stub
		
	}

}

package persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import utilidades.Departamento;
import utilidades.ExcepcionDAO;

public class DepartamentoFicheroBinarioDAO implements DepartamentoDAOInterface {
	String nombreFichero = null;

	public DepartamentoFicheroBinarioDAO(String nombreFichero) {
		super();
		this.nombreFichero = nombreFichero;
	}

	public void escribirDatos(List<Departamento> listaDepartamento) throws ExcepcionDAO {

		// creamos un fichero donde vamos a grabar.
		File fichero = new File(nombreFichero);
		ObjectOutputStream objectOutputStream;
		try {
			// Creamos el objeto que guarda en el fichero

			objectOutputStream = new ObjectOutputStream(new FileOutputStream(fichero));

			// REcorremos la lista que le pasabamos al metodo por referencia y
			// la vamos escribiendo en el fichero
			for (Departamento departamento : listaDepartamento) {
				objectOutputStream.writeObject(departamento);

			}
			objectOutputStream.close();
		} catch (IOException ex) {
			throw new ExcepcionDAO(ex.getMessage(), ex);
		}

	}

	@Override
	public List<Departamento> leerDatos() throws ExcepcionDAO {

		// Creamos una lista donde vamos a cargar el fichero
		List<Departamento> lista = new ArrayList<Departamento>();
		ObjectInputStream objectInputStream;
		try {
			// Abrimos el fichero a leer
			objectInputStream = new ObjectInputStream(new FileInputStream(nombreFichero));

			Object auxiliar = objectInputStream.readObject();
			try{
				while (true) {
					// vamos metiendo los elementos en la lista
					
						Departamento aux = (Departamento) auxiliar;
						lista.add(aux);
						auxiliar = objectInputStream.readObject();
					
					
				}

			}catch(Exception e){}

			objectInputStream.close();

		} catch (Exception e) {
			throw new ExcepcionDAO(e.getMessage(), e);
		}
		// Devolvemos la lista
		return lista;
	}

	@Override
	public void actualizarDatos(Departamento d) throws ExcepcionDAO {

		// Creamos la lista en la cual reutilizando la funcion de leer
		// recuperamos el contenido del .dat
		List<Departamento> listaParaActualizar = leerDatos();

		// Buscamos si coinciden las clave primaria (la que teniamos con la que
		// le pasamos) y actualizamos

		for (int i = 0; i < listaParaActualizar.size(); i++) {
			if (listaParaActualizar.get(i).getNumero() == d.getNumero()) {
				listaParaActualizar.get(i).setNombre(d.getNombre());
				listaParaActualizar.get(i).setLocalidad(d.getLocalidad());
			}
		}

		// Reutilizamos la funcion de escribir para que los cambios se lleven a
		// cabo.
		escribirDatos(listaParaActualizar);

	}

	@Override
	public void borrarDatos(int n) throws ExcepcionDAO {
		// Creamos la lista en la cual reutilizando la funcion de leer
		// recuperamos el contenido del .dat
		List<Departamento> listaParaBorrar = leerDatos();

		// Buscamos si coincide la clave primaria con el numero y borramos

		for (int i = 0; i < listaParaBorrar.size(); i++) {
			if (listaParaBorrar.get(i).getNumero() == n) {
				listaParaBorrar.remove(i);
			}
		}

		// Reutilizamos la funcion de escribir para que los cambios se lleven a
		// cabo.
		escribirDatos(listaParaBorrar);

	}

	public void borrarTodosLosDatos() throws ExcepcionDAO {
		// Creamos un fichero
		File ficheroDatos = new File(nombreFichero);
		// comprobamos que tiene permisos de escritura si es asi lo borramos
		// todo
		try (RandomAccessFile almacenDatos = new RandomAccessFile(ficheroDatos, "rw")) {
			almacenDatos.setLength(0);

		} catch (IOException e) {
			throw new ExcepcionDAO(e.getMessage(), e);
		}

	}

}

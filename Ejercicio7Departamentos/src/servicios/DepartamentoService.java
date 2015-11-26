package servicios;

import java.util.List;

import persistencia.DepartamentoDAOInterface;
import utilidades.Departamento;
import utilidades.ExcepcionDAO;

public class DepartamentoService implements DepartamentoServiceInterface {
	private DepartamentoDAOInterface daoInterface;

	

	/**
	 * @param nombreFichero
	 */
	public DepartamentoService(DepartamentoDAOInterface departamentoDAOInterface) {
		daoInterface = departamentoDAOInterface;

	}

	@Override
	public void salvar(List<Departamento> listaDepartamento) throws ExcepcionDAO {

		daoInterface.escribirDatos(listaDepartamento);
	}

	@Override
	public List<Departamento> cargar() throws ExcepcionDAO {
		return daoInterface.leerDatos();
	}

	@Override
	public void actualizar(Departamento d) throws ExcepcionDAO {
		daoInterface.actualizarDatos(d);

	}

	@Override
	public void borrar(int numero) throws ExcepcionDAO {
		daoInterface.borrarDatos(numero);

	}

	@Override
	public void borrarTodo() throws ExcepcionDAO {
		daoInterface.borrarTodosLosDatos();
		
	}

}

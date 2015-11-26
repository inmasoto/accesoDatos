package utilidades;

import java.util.ArrayList;
import java.util.List;

public class ListaDepartamento {
	 private List<Departamento> lista;
	
	public ListaDepartamento(){
		lista =  new ArrayList<Departamento>();
	}
	
	public void add(Departamento d){
		
		lista.add(d);
	}

	public List<Departamento> getLista() {
		return lista;
	}

	public void setLista(List<Departamento> lista) {
		this.lista = lista;
	}
	
	
	
	

}

package utilidades;

import java.io.Serializable;

public class Departamento implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7662927408083425324L;
	private String nombre;
	private int numero;
	private String localidad;
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the numero
	 */
	public int getNumero() {
		return numero;
	}
	/**
	 * @param numero the numero to set
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}
	/**
	 * @return the localidad
	 */
	public String getLocalidad() {
		return localidad;
	}
	/**
	 * @param localidad the localidad to set
	 */
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	
	
	/**
	 * @param nombre
	 * @param numero
	 * @param localidad
	 */
	public Departamento(String nombre, int numero, String localidad) {
		this.nombre = nombre;
		this.numero = numero;
		this.localidad = localidad;
	}
	@Override
	public String toString() {
		return "Departamento [nombre=" + nombre + ", numero=" + numero + ", localidad=" + localidad + "]";
	}
	
	
	
	
}

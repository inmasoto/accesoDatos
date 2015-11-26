package utilidades;

public class ExcepcionDAO extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5673918501603519234L;
	
	
	public ExcepcionDAO(){
		
		super();
	}
	public ExcepcionDAO(String mensaje){
		super(mensaje);
		
	}
	public ExcepcionDAO(String mensaje,Throwable cause){
		
		super(mensaje,cause);
	}
	
public ExcepcionDAO(Throwable cause){
		super(cause);
		
	}
}

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class baseDatos {

	public static void main(String[] args)  {
		
		//Cargar el driver
		
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			
			//Establecemos la conexion
			
			Connection conexion = DriverManager.getConnection("jdbc:odbc:odbcMySql");
			
			Statement sentencia = conexion.createStatement();
			ResultSet  resul=sentencia.executeQuery("SELECT * FROM departamento");
			
			while(resul.next()){
				System.out.println(resul.getInt(1)+" "+resul.getString(2)+" "+resul.getString(3));
				
			}
			resul.close();
			sentencia.close();
			conexion.close();
			
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		

	}

}

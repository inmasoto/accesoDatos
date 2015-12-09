import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class baseDatos {

	public static void main(String[] args)  {
		
		//Cargar el driver
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			//Establecemos la conexion
			
			Connection conexion = DriverManager.getConnection("jdbc:mysql://172.16.100.5:3306/inma","inma","1234");
			
			Statement sentencia = conexion.createStatement();
			ResultSet  resul=sentencia.executeQuery("SELECT * FROM departamentos");
			
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

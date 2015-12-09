package baseDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class persona {

	public static void main(String[] args) {  
		   try {  
		     Class.forName("org.apache.derby.jdbc.EmbeddedDriver");  
		   } catch (ClassNotFoundException e1) {  
		 e1.printStackTrace();  
		   } 
		  
		   try {  
		Connection conn=DriverManager.getConnection("jdbc:derby://localhost:1527/dracof", "dracof", "dracof");  
		     
		 Statement st = conn.createStatement();  
		 ResultSet rs = st.executeQuery( "SELECT * FROM PERSONA" );  
		     
		 while (rs.next()) {  
		   System.out.println( "Nombre: " + rs.getString("NOMBRE") );  
		   System.out.println( "Apellido: " + rs.getString("APELLIDO") );  
		    System.out.println( "Edad: " + rs.getString("EDAD") );  
		   System.out.println( "_________________________________________");  
		      }     
		   } catch (SQLException e) {  
		 e.printStackTrace();  
		   }  
		}  

}

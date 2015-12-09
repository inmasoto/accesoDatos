import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) {
	
			try {
				Class.forName("com.mysql.jdbc.Driver");
				
				//Establecemos la conexion con la BD
				Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo", "root", "1111");
				
				
			  String nombre="pepe",oficio="limpiador";
			  int numeroEmpl=9,numDep=1,salario=1000,dir=1,comision=200;
				     
				
				     
				 Statement st = conexion.createStatement();  
				 ResultSet rs = st.executeQuery( "SELECT count(*) FROM empleado WHERE numEmpl='"+numeroEmpl+"'");
				 
			String sql="INSERT INTO empleado values('"+numeroEmpl+"','"+nombre+"','"+oficio+"','"+dir+"','"+salario+"','"+comision+"','"+numDep+"')";
				
				 
				rs.first();
				
				
				if(rs.getInt(1)>0){
					 System.out.println("no se puede insertar");
					 
				
				 }
				else{
					rs=st.executeQuery("SELECT count(*) FROM departamento WHERE numDep='"+numDep+"'");
					rs.first();
					if(rs.getInt(1)<=0){
						 System.out.println("no se puede insertar");
						 
					
					 }
					else{
						if(salario<0){
							System.out.println("no se puede insertar");
						}else{
							rs=st.executeQuery("SELECT count(*) FROM empleado WHERE numEmpl='"+dir+"'");
							rs.first();
							if(rs.getInt(1)<=0){
								 System.out.println("no se puede insertar");
								 
							 }else{
								 st.execute(sql);
								 System.out.println("ha sido insertada con exito");
							 }
						}
					}
				
				
				}
				
				
				rs.close();
				st.close();
				conexion.close();
				     
				 
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		

	}

}

package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
	private  String nameDB="segurosGroup";
	private  String host="jdbc:mysql://localhost:3306/"+nameDB+"?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private  String user="root";
	private  String pass="";
	private  Connection conexion=null;
	
	public Connect() {
		
	}
	
	public Connection getConexion() {
		return conexion;
	}
	
	public void conectar() {
		try {
			if(conexion==null || conexion.isClosed()) {

				Class.forName("com.mysql.jdbc.Driver");
				conexion=DriverManager.getConnection(host,user,pass);
			}
		}
		catch(Exception e){
				e.printStackTrace();
		}

	}
	
	public void desconectar(){
		try {
			if(conexion==null || conexion.isClosed()) {
				conexion.close();
			}
		}
		catch(Exception e){
				e.printStackTrace();
			}

	}

}

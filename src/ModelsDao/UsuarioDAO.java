package ModelsDao;

import Models.Usuario;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import Config.Connect;

public class UsuarioDAO {
	
	private Connect cn;
	public UsuarioDAO() {
		cn=new Connect();
	}
	
    public Usuario login(String usuario, String pass){
    	Usuario u=null;
    	Connection conexion;
    	try {
    		cn.conectar();
    		conexion=cn.getConexion();
    		if(conexion!=null) {
    			String query="SELECT usuario,password,tipo FROM usuarios "
    					+ "WHERE estado=1 and usuario='"+usuario+"' and password='"+pass+"'";
    			Statement st=conexion.createStatement();
    			ResultSet result=st.executeQuery(query);
    			if(result.next()) {
    				u=new Usuario();
    				u.setName(usuario);
    				u.setPassword(pass);
    				u.setType(result.getInt("tipo"));
    			}
    		}
    		
    	}
    	catch(Exception e) {
    		System.out.print(e.getMessage());
    	}
    	finally {
    		
    	}
    	return u;
    }
	

}

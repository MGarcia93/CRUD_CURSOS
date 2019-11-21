package ModelsDao;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;

import Config.Connect;
import Models.Localidad;

public class LocalidadDAO {
	private Connect cn;

	public LocalidadDAO() {
		super();
		// TODO Auto-generated constructor stub
		cn=new Connect();
	}
	
	
	public ArrayList<Localidad> FindLocalidades(String where) {
		ArrayList<Localidad> lista=new ArrayList<Localidad>();
		Localidad localidad=null;
		Connection conexion;
		try {
			cn.conectar();
			conexion = cn.getConexion();
			if (conexion != null) {
				String query="SELECT id,nombre FROM localidad";
				query+=" WHERE "+where;
				Statement st = conexion.createStatement();
				ResultSet  result=st.executeQuery(query);
				while(result.next()) {
					localidad=new Localidad();
					localidad.setId_localidad(result.getInt("id"));
					localidad.setNombre(result.getString("nombre"));
					lista.add(localidad);
				}
				
			}
		}
		catch(Exception e) {
			System.out.print(e.getMessage());
		}
		finally {
			cn.desconectar();
		}
		return lista;
		
	}
	
	public Localidad FindLocalidad(int id) {
		Localidad localidad=null;
		Connection conexion;
		try {
			cn.conectar();
			conexion = cn.getConexion();
			if (conexion != null) {
				String query="SELECT id,nombre FROM localidad WHERE id="+id;
				Statement st = conexion.createStatement();
				ResultSet  result=st.executeQuery(query);
				if(result.next()) {
					localidad=new Localidad();
					localidad.setId_localidad(result.getInt("id"));
					localidad.setNombre(result.getString("nombre"));
					
				}
				
			}
		}
		catch(Exception e) {
			System.out.print(e.getMessage());
		}
		finally {
			cn.desconectar();
		}
		return localidad;
	}

}

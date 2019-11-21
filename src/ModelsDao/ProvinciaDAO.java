package ModelsDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Config.Connect;
import Models.Provincia;

public class ProvinciaDAO {
	private Connect cn;

	public ProvinciaDAO() {
		super();
		// TODO Auto-generated constructor stub
		cn=new Connect();
	}
	
	
	public ArrayList<Provincia> FindProvincias() {
		ArrayList<Provincia> lista=new ArrayList<Provincia>();
		Provincia provincia=null;
		Connection conexion;
		try {
			cn.conectar();
			conexion = cn.getConexion();
			if (conexion != null) {
				String query="SELECT id,nombre FROM provincia";
				Statement st = conexion.createStatement();
				ResultSet  result=st.executeQuery(query);
				while(result.next()) {
					provincia=new Provincia();
					provincia.setId_provincia(result.getInt("id"));
					provincia.setNombre(result.getString("nombre"));
					lista.add(provincia);
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
	
	public Provincia FindProvincia(int id) {
		Provincia provincia=null;
		Connection conexion;
		try {
			cn.conectar();
			conexion = cn.getConexion();
			if (conexion != null) {
				String query="SELECT id,nombre FROM provincia WHERE id="+id;
				Statement st = conexion.createStatement();
				ResultSet  result=st.executeQuery(query);
				if(result.next()) {
					provincia=new Provincia();
					provincia.setId_provincia(result.getInt("id"));
					provincia.setNombre(result.getString("nombre"));
					
				}
				
			}
		}
		catch(Exception e) {
			System.out.print(e.getMessage());
		}
		finally {
			cn.desconectar();
		}
		return provincia;
	}
}

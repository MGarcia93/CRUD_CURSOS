package ModelsDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Config.Connect;
import Models.Materia;

public class MateriaDAO {
	

	public Materia FindMateriaForId(int id) {
		Materia materia=null;
		Connection conexion;
		Connect cn=new Connect();
		try {
			cn.conectar();
			conexion = cn.getConexion();
			if (conexion != null) {
				String query="SELECT nombre FROM materias WHERE id_materia="+id;
				Statement st= conexion.createStatement();
				ResultSet result = st.executeQuery(query);
				if(result.next()) {
					materia=new Materia();
					materia.setIdMateria(id);
					materia.setDescripcion(result.getString("nombre"));
					
				}
			}
		}
		catch(Exception e) {
			System.out.print(e.getMessage());
		}
		finally {
			cn.desconectar();
		}
		return materia;
	}
	
	public ArrayList<Materia> FindMaterias() {
		Materia materia=null;
		ArrayList<Materia> lista=new ArrayList<Materia>();
		Connection conexion;
		Connect cn=new Connect();
		try {
			cn.conectar();
			conexion = cn.getConexion();
			if (conexion != null) {
				String query="SELECT id_materia,nombre FROM materias";
				Statement st= conexion.createStatement();
				ResultSet result = st.executeQuery(query);
				while(result.next()) {
					materia=new Materia();
					materia.setIdMateria(result.getInt("id_materia"));
					materia.setDescripcion(result.getString("nombre"));
					lista.add(materia);
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
	
	

}

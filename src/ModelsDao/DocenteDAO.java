package ModelsDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Models.Docente;
import Models.Localidad;
import Models.Persona;
import Models.Provincia;

public class DocenteDAO extends PersonaDAO{

	public DocenteDAO() {
		super("docentes");
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<Docente> FindDocenteCurso(String where){
		ArrayList<Docente> lista=new ArrayList<Docente>();
		Docente docente;
		Connection conexion;
		try {
			cn.conectar();
			conexion = cn.getConexion();
			if (cn != null) {
				String query ="SELECT distinct d.legajo, d.nombre, d.apellido ";
				query+=" FROM Docentes AS d";
				query+=" INNER JOIN Cursos AS c ON c.id_docente=d.legajo";
				query+=" WHERE d.estado=1 ";
				if(!where.equals("")) {
					query+=" AND " +where;
				}
				
				Statement st=conexion.createStatement();
				ResultSet result=st.executeQuery(query);
				while(result.next()) {
					docente=new Docente();
					docente.setLegajo(result.getInt("legajo"));
					docente.setNombre(result.getString("nombre"));
					docente.setApellido(result.getString("apellido"));
					docente.setCursos(new CursoDAO().FindCurso("id_docente="+docente.getLegajo(), true));
					lista.add(docente);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.desconectar();
		}

		return lista;
		
	}

}

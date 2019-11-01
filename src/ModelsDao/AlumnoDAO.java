package ModelsDao;
import java.sql.Connection;
import java.util.ArrayList;

import Models.Alumno;
import Models.Persona;
public class AlumnoDAO extends PersonaDAO{

	/**
	 * 
	 */
	public AlumnoDAO() {
		super("alumnos");
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<Alumno> findAlumnoCurso(int curso){
		ArrayList<Alumno> lista= new ArrayList<Alumno>();
		Alumno alumno;
		Connection conexion;
		try {
			cn.conectar();
			conexion = cn.getConexion();
			if(conexion!=null) {
				String inner=" INNER JOIN alumnosCurso AS ac ON p.legajo=ac.id_alumno ";
				String where=" WHERE ac.id_curso="+curso;
				ArrayList<Persona> result=this.FindPersonas(where, inner);
				for(Persona p:result){
					alumno=new Alumno();
					alumno.SetPersona(p);
					lista.add(alumno);
					
				}
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally{
			cn.desconectar();
		}
		
		return lista;
	}

}


package ModelsDao;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Models.Alumno;
import Models.Notas;
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
				String inner=" INNER JOIN alumnos_Curso AS ac ON p.legajo=ac.id_alumno ";
				String where="  ac.id_curso="+curso;
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
	
	
	public ArrayList<Persona> findAlumnoDocente(int docente,String curso){
		ArrayList<Persona> lista=new ArrayList<Persona>();
		Alumno alumno;
		Connection conexion;
		try {
			cn.conectar();
			conexion = cn.getConexion();
			if(conexion!=null) {
				String inner=" INNER JOIN alumnos_Curso AS ac ON p.legajo=ac.id_alumno ";
				inner+="INNER JOIN cursos as c ON ac.id_curso=c.id_curso ";
				String where="  c.id_docente="+docente;
				if (!curso.equals("")) {
					where+=" AND c.id_curso="+curso;
				}
				lista=this.FindPersonas(where, inner);
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally{
			cn.desconectar();
		}
		
		return lista;
	}
	
	public ArrayList<Alumno> findAlumnoCursoNotas(int curso){
		ArrayList<Alumno> lista= new ArrayList<Alumno>();
		Alumno alumno;
		Connection conexion;
		try {
			cn.conectar();
			conexion = cn.getConexion();
			if(conexion!=null) {
				String query="SELECT a.legajo, a.nombre,a.apellido,ac.parcial_1,ac.parcial_2, ac.recuperatorio_1, ac.recuperatorio_2, ac.estado FROM alumnos as a ";
				query+=" INNER JOIN alumnos_Curso AS ac ON a.legajo=ac.id_alumno ";
				query+=" WHERE a.estado=1 AND ac.id_curso="+curso;
				Statement st = conexion.createStatement();
				ResultSet result=st.executeQuery(query);
				while(result.next()){
					alumno=new Alumno();
					alumno.setLegajo(result.getInt("legajo"));
					alumno.setApellido(result.getString("apellido"));
					alumno.setNombre(result.getString("nombre"));
					Notas notas = new Notas();
					notas.setPrimerParcial(result.getInt("parcial_1"));
					notas.setSegundoParcial(result.getInt("parcial_2"));
					notas.setPrimerRecuperatorio(result.getInt("recuperatorio_1"));
					notas.setSegundoRecuperatorio(result.getInt("recuperatorio_2"));
					notas.setEstado(result.getInt("estado"));
					alumno.setNotas(notas);
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
	
	
	
	
	
	public int UpdateNotas(Notas notas) {
		int fila=0;
		Connection conexion;
		try {
			cn.conectar();
			conexion = cn.getConexion();
			if (conexion != null) {
				String query="UPDATE alumnos_curso SET parcial_1=?,  parcial_2=?, recuperatorio_1=?, recuperatorio_2=?, estado=?"
						+ " WHERE id_curso=? AND id_alumno=?";
				CallableStatement st=conexion.prepareCall(query);
				st.setInt(1,notas.getPrimerParcial());
				st.setInt(2,notas.getSegundoParcial());
				st.setInt(3, notas.getPrimerRecuperatorio());
				st.setInt(4, notas.getSegundoRecuperatorio());
				st.setInt(5, notas.getEstado());
				st.setInt(6, notas.getIdCurso());
				st.setInt(7, notas.getLegajo());
				fila=st.executeUpdate();
			}
		}
		catch(Exception e) {
			System.out.print(e.getMessage());
		}
		finally {
			cn.desconectar();
		}
		return fila;
	}
	

}


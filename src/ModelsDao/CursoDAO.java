package ModelsDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import  java.sql.CallableStatement;

import Config.Connect;
import Models.Curso;
import Models.Docente;

public class CursoDAO {
	
	private Connect cn;
	public CursoDAO() {
		cn=new Connect();
	}
	
	
	
	public int insertCurso( Curso datos) {
		int fila=0;
		Connection conexion;
		try {
			cn.conectar();
			conexion = cn.getConexion();
			if (conexion != null) {
				String query="INSERT INTO cursos(id_materia,semestre,año,id_docente) VALUES(?,?,?,?)";
				CallableStatement preg=conexion.prepareCall(query);
				preg.setInt(1, datos.getMateria().getIdMateria());
				preg.setString(2, datos.getSemestre());
				preg.setString(3,Integer.toString(datos.getAnio()));
				preg.setInt(4, datos.getDocente().getLegajo());
				fila=preg.executeUpdate();
				
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
	
	
	public int updateCurso(Curso datos) {
		int fila=0;
		Connection conexion;
		try {
			cn.conectar();
			conexion = cn.getConexion();
			if (conexion != null) {
				String query="UPDATE cursos SET id_materia=?, semestre=?, año=?, id_docente=? WHERE id_curso=?";
				CallableStatement preg=conexion.prepareCall(query);
				preg.setInt(1, datos.getMateria().getIdMateria());
				preg.setString(2, datos.getSemestre());
				preg.setString(3, Integer.toString(datos.getAnio()));
				preg.setInt(4, datos.getDocente().getLegajo());
				preg.setInt(5,datos.getId());
				fila=preg.executeUpdate();
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
	
	public int deleteCurso(int curso) {
		int fila=0;
		Connection conexion;
		try {
			cn.conectar();
			conexion = cn.getConexion();
			if (conexion != null) {
				String query="UPDATE cursos SET estado=0 WHERE id_curso="+curso;
				Statement  st=conexion.createStatement();
				fila=st.executeUpdate(query );
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
	
	public ArrayList<Curso> FindCurso(String where,boolean alumnos) {
		Curso curso=null;
		ArrayList<Curso> lista=new ArrayList<Curso>();
		Connection conexion;
		try {
			cn.conectar();
			conexion = cn.getConexion();
			if (conexion != null) {
				String query="SELECT id_curso,id_materia, semestre, año, id_docente FROM cursos";
				query+=" WHERE estado=1";
				if(!where.equals("")){
					query+=" and "+ where;
				}
				Statement st =conexion.createStatement();
				ResultSet result=st.executeQuery(query);
				while(result.next()){
					curso=new Curso();
					curso.setId(result.getInt("id_curso"));
					MateriaDAO mDAO= new MateriaDAO();
					curso.setMateria(mDAO.FindMateriaForId(result.getInt("id_materia")));
					curso.setSemestre(result.getString("semestre"));
					curso.setAnio(Integer.parseInt(result.getString("año")));
					PersonaDAO pDAO = new PersonaDAO("docentes");
					Docente docente=new Docente();
					docente.SetPersona(pDAO.FindPersona("legajo="+result.getInt("id_docente"), ""));
					curso.setDocente(docente);
					if(alumnos){
						AlumnoDAO aDAO=new AlumnoDAO();
						curso.setAlumnos(aDAO.findAlumnoCursoNotas(curso.getId()));
					}
					lista.add(curso);
						
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
	public int LastIdCurso() {
		Connection conexion;
		int id=0;
		try {
			cn.conectar();
			conexion = cn.getConexion();
			if (conexion != null) {
				String query=" SELECT MAX(id_curso) as last FROM cursos";
				Statement st=conexion.createStatement();
				ResultSet result=st.executeQuery(query);
				if(result.next()) {
					id=result.getInt("last");
				}
			}
		}
		catch(Exception e) {
			System.out.print(e.getMessage());
		}
		finally {
			cn.desconectar();
		}
		return id;
	}
	
	public int insertAlumnoCurso(int curso, int legajo) {
		int fila = 0;
		Connection conexion;
		try {
			cn.conectar();
			conexion = cn.getConexion();
			if (conexion != null) {
				String query="INSERT INTO alumnos_curso(id_alumno,id_curso) VALUES("+legajo+","+curso+")";
				Statement  st=conexion.createStatement();
				 fila= st.executeUpdate(query);
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
	
	public int deleteAlumnoCurso(int curso, int legajo) {
		int fila = 0;
		Connection conexion;
		try {
			cn.conectar();
			conexion = cn.getConexion();
			if (conexion != null) {
				String query="DELETE FROM alumnos_curso WHERE id_alumno="+legajo+" AND id_curso="+curso;
				Statement  st=conexion.createStatement();
				 fila= st.executeUpdate(query);
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

	public boolean existCurso(int curso) {
		boolean exist = false;
		Connection conexion;
		try {
			cn.conectar();
			conexion = cn.getConexion();
			if (conexion != null) {
				String query="SELECT id_curso FROM cursos WHERE id_curso="+curso;
				Statement  st=conexion.createStatement();
				 ResultSet result = st.executeQuery(query);
				 if(result.next()) {
					 exist=true;
				 }
			}	
		}
		catch(Exception e) {
			System.out.print(e.getMessage());
		}
		finally {
			cn.desconectar();
		}
		return exist;
	}

}

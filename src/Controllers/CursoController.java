package Controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.servlet.http.HttpServletRequest;
import com.google.gson.Gson;

import Models.Alumno;
import Models.Curso;
import Models.Docente;
import Models.Materia;
import Models.Persona;
import ModelsDao.CursoDAO;
import ModelsDao.PersonaDAO;
public class CursoController {
	CursoDAO cDao;
	
	public CursoController() {
		super();
		cDao=new CursoDAO();
		// TODO Auto-generated constructor stub
	}

	public String InsertCurso(int anio, String semestre, int materia, int docente) {
		String Rta="error";
		Curso datos=new Curso();
		Materia m=new Materia();
		Docente d=new Docente();
		datos.setAnio(anio);
		datos.setSemestre(semestre);
		m.setIdMateria(materia);
		datos.setMateria(m);
		d.setLegajo(docente);
		datos.setDocente(d);
		if(cDao.insertCurso(datos)==1) {
			Rta=Integer.toString(cDao.LastIdCurso());
		}
		return Rta;
	}
	
	public String UpdateCurso(int id,int anio, String semestre, int materia, int docente) {
		String Rta="error";
		Curso datos=new Curso();
		Materia m=new Materia();
		Docente d=new Docente();
		datos.setId(id);
		datos.setAnio(anio);
		datos.setSemestre(semestre);
		m.setIdMateria(materia);
		datos.setMateria(m);
		d.setLegajo(docente);
		datos.setDocente(d);
		if(cDao.updateCurso(datos)==1) {
			Rta=Integer.toString(datos.getId());
		}
		return Rta;
	}
	
	public String DeleteCurso(int id) {
		String Rta="error";
		if(cDao.deleteCurso(id)==1) {
			Rta=Integer.toString(id);
		}
		return Rta;
	}
	
	public String AgregarAlumnoCurso(HttpServletRequest request) {
		String Rta="error";
		if(cDao.insertAlumnoCurso(Integer.parseInt(request.getParameter("curso")), Integer.parseInt(request.getParameter("legajo")))!=0){
			Rta=request.getParameter("legajo");
		}
		return Rta;
	}
	public String SacarAlumnoCurso(HttpServletRequest request) {
		String Rta="error";
		if(cDao.deleteAlumnoCurso(Integer.parseInt(request.getParameter("curso")), Integer.parseInt(request.getParameter("legajo")))!=0){
			Rta=request.getParameter("legajo");
		}
		return Rta;
	}
	
	
	public String ListarAlumnoDisponibles(String Curso) {
		String where="  p.legajo not in(SELECT id_alumno  FROM ALUMNOS_CURSO WHERE id_curso="+Curso+")";
		PersonaDAO pDao=new PersonaDAO("alumnos");
		ArrayList<Persona> lista=pDao.FindPersonas(where, "");
		return  new Gson().toJson(lista);
	}
	
	public String listarCursoAlumno(String legajo) {
		String w=" id_curso in (SELECT id_curso FROM alumnos_Curso WHERE id_alumno= " +legajo+")";
		ArrayList<Curso> cursos=cDao.FindCurso(w, false);
		return  new Gson().toJson(cursos);
	}
	
	public String listarCursoDocente(String legajo) {
		ArrayList<Curso> misCursos=cDao.FindCurso(" id_docente="+legajo, false);
		return new Gson().toJson(misCursos);
	}
	
	


}

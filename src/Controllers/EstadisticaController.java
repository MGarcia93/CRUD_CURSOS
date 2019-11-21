package Controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.servlet.http.HttpServletRequest;

import Models.Curso;
import Models.Docente;
import Models.EstadisticaDocente;
import ModelsDao.CursoDAO;
import ModelsDao.DocenteDAO;

public class EstadisticaController {
	
	CursoDAO cDao;
	DocenteDAO dDao;
	
	public EstadisticaController() {
		super();
		// TODO Auto-generated constructor stub
		cDao=new CursoDAO();
		dDao=new DocenteDAO();
	}

	public ArrayList<Curso> PromedioCurso(HttpServletRequest request){
		ArrayList<Curso> lista=new ArrayList<Curso>();
		String where="";
		try {
			if(request.getParameter("desde")!=null && !request.getParameter("desde").equals("")) {
				where +="año >"+request.getParameter("desde");
			}
			if(request.getParameter("hasta")!=null && !request.getParameter("hasta").equals("")) {
				where +=(where.equals("")?" ":" AND ")+ "año <"+request.getParameter("hasta");
			}
			if(request.getParameter("materia")!=null && !request.getParameter("materia").equals("")) {
				where+= (where.equals("")?" ":" AND ")+ " id_materia=" +request.getParameter("materia");
			}
			if(request.getParameter("docente")!=null && !request.getParameter("docente").equals("")) {
				where+= (where.equals(" ")?"":" AND ")+ " id_docente=" +request.getParameter("docente");
			}
			lista= cDao.FindCurso(where,true);
			lista =this.OrdenarCursos(lista, "promedio");
			
		}
		catch(Exception e) {
			System.out.print(e.getMessage());
		}
		 return lista;
	}
		
	public ArrayList<Integer> aniosExisteCurso(){
		ArrayList<Curso> lista=new ArrayList<Curso>();
		lista= cDao.FindCurso("",true);
		ArrayList<Integer> anios=new ArrayList<Integer>();
		for(Curso c: lista) {
			if(!anios.contains(c.getAnio())) {
				anios.add(c.getAnio());
			}
		}
		Collections.sort(anios);
		return anios;	
	}
	
	public ArrayList<Curso> OrdenarCursos(ArrayList<Curso> cursos,String order){
		switch(order) {
		case "promedio":
			cursos.sort(Comparator.comparing(Curso::getPromedio).reversed());
			break;
		case "aprobado":
			cursos.sort(Comparator.comparing(Curso::getPorcentajeAprobados).reversed());
			break;
		}
		return cursos;
	}
	
	
	public ArrayList<EstadisticaDocente> EstaditicaDocente(HttpServletRequest request){
		ArrayList<EstadisticaDocente> lista=new ArrayList<EstadisticaDocente>();
		String where="";
		try {
			if(request.getParameter("desde")!=null && !request.getParameter("desde").equals("")) {
				where +="año >"+request.getParameter("desde");
			}
			if(request.getParameter("hasta")!=null && !request.getParameter("hasta").equals("")) {
				where +=(where.equals("")?" ":" AND ")+ "año <"+request.getParameter("hasta");
			}
			if(request.getParameter("materia")!=null && !request.getParameter("materia").equals("")) {
				where+= (where.equals("")?" ":" AND ")+ " id_materia=" +request.getParameter("materia");
			}
			lista=this.ContverEstadisicaDocente( dDao.FindDocenteCurso(where));
			lista =this.OrdenarDocente(lista, "promedio");
			
		}
		catch(Exception e) {
			System.out.print(e.getMessage());
		}
		 return lista;
	}
	
	
	public ArrayList<EstadisticaDocente> OrdenarDocente(ArrayList<EstadisticaDocente> docentes,String order){
		switch(order) {
		case "promedio":
			docentes.sort(Comparator.comparing(EstadisticaDocente::getPromedioNota).reversed());
			break;
		case "aprobado":
			docentes.sort(Comparator.comparing(EstadisticaDocente::getPorncentaAprobado).reversed());
			break;
		}
		return docentes;
	}
	
	public ArrayList<EstadisticaDocente> ContverEstadisicaDocente( ArrayList<Docente> listaDocente){
		ArrayList<EstadisticaDocente> lista = new ArrayList<EstadisticaDocente>();
		EstadisticaDocente ed;
		for(Docente d: listaDocente) {
			ed= new EstadisticaDocente();
			ed.SetPersona(d.getPersona());
			ed.setCursos(d.getCursos());
			lista.add(ed);
		}
		return lista;
	}
}

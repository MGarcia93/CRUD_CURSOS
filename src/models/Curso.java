package Models;

import java.util.ArrayList;

public class Curso {
	
	private int id;
	private String semestre;
	private int a�o;
	private Materia materia;
	private Docente docente;
	private ArrayList<Alumno> alumnos;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSemestre() {
		return semestre;
	}
	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}
	public int getA�o() {
		return a�o;
	}
	public void setA�o(int a�o) {
		this.a�o = a�o;
	}
	public Materia getMateria() {
		return materia;
	}
	public void setMateria(Materia materia) {
		this.materia = materia;
	}
	public Docente getDocente() {
		return docente;
	}
	public void setDocente(Docente docente) {
		this.docente = docente;
	}
	
	public ArrayList<Alumno> getAlumnos() {
		return alumnos;
	}
	public void setAlumnos(ArrayList<Alumno> alumnos) {
		this.alumnos = alumnos;
	}
	public Curso() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}

package Models;

import java.util.ArrayList;

public class Curso {
	
	private int id;
	private String semestre;
	private int anio;
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
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
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
	public Curso(int materia,String semestre, int anio, int docente) {
		super();
		this.semestre = semestre;
		this.anio = anio;
		this.materia=new Materia();
				this.materia.setIdMateria(materia);
		this.docente=new Docente();
		this.docente.setLegajo(docente);
	}
	
	
	public int getPromedio() {
		int cantAlumnos=0;
		int sumaPromedios=0;
		float promedio=0;
		for(Alumno a : this.getAlumnos()) {
			sumaPromedios+=a.getNotas().getPromedio();
			cantAlumnos++;
		}
		if(sumaPromedios!=0) {
			promedio=(float)sumaPromedios/cantAlumnos;
		}
		return  Math.round(promedio);
		
	}

	
	public int getPorcentajeAprobados() {
		int aprobados=0;
		int total=0;
		float porcentaje=0;
		for(Alumno a : this.getAlumnos()) {
			if(a.getNotas().aprobo()==true) {
				aprobados++;
			}
			total++;
		}
		if(aprobados!=0) {
			porcentaje=(float)aprobados/total;
		}
		return  Math.round(porcentaje*100);
	}
	
	public int getCantdidadAlumno() {
		return this.alumnos.size();
	}
	public Curso() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}

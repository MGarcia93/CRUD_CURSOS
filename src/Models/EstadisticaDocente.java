package Models;

public class EstadisticaDocente extends Docente{
	

	public int getPromedioAlumno() {
		int sumaAlumnos=0;
		for(Curso c: this.getCursos()) {
			sumaAlumnos+=c.getCantdidadAlumno();
		}
		float promedioAlumno=(float)sumaAlumnos/this.getCantidadCursos();
		return Math.round(promedioAlumno);
	}

	public int getCantidadCursos() {
		return this.getCursos().size();
	}
	public int getPromedioNota() {
		int sumaPromedios=0;
		for(Curso c: this.getCursos()) {
			sumaPromedios+=c.getPromedio();
		}
		float promedio= (float)sumaPromedios/this.getCantidadCursos();
		return Math.round(promedio);
	}

	public int getPorncentaAprobado() {
		int sumaprobados=0;
		int totalAlumnos=0;
		for(Curso c: this.getCursos()) {
			sumaprobados+=c.getCantdidadAlumno()*c.getPorcentajeAprobados()/100;
			totalAlumnos+=c.getCantdidadAlumno();
		}
		float porcentaje=(float)sumaprobados/totalAlumnos;
		return Math.round(porcentaje*100);
	}

	public EstadisticaDocente() {
		super();
		// TODO Auto-generated constructor stub
		
	}
	
}

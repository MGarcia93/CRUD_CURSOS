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
	public float getPromedioNota() {
		float sumaPromedios=0;
		for(Curso c: this.getCursos()) {
			sumaPromedios+=c.getPromedio();
		}
		float promedio= (float)sumaPromedios/this.getCantidadCursos();
		return promedio;
	}

	public int getPorncentaAprobado() {
		float sumaprobados=0;
		float totalAlumnos=0;
		for(Curso c: this.getCursos()) {
			sumaprobados+=c.GetTotalRegular()*c.getPorcentajeAprobados()/100;
			totalAlumnos+=c.GetTotalRegular();
		}
		float porcentaje=(float)sumaprobados/totalAlumnos*100;
		return Math.round(porcentaje);
	}

	public EstadisticaDocente() {
		super();
		// TODO Auto-generated constructor stub
		
	}
	
}

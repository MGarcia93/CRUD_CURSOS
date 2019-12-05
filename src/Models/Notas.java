package Models;

public class Notas {
	private int primerParcial;
	private int segundoParcial;
	private int primerRecuperatorio;
	private int segundoRecuperatorio;
	private int legajo;
	private int idCurso;
	private int estado;
	public Notas(int primerParcial, int segundoParcial, int primerRecuperatorio, int segundoRecureatorio, int legajo,
			int idCurso, int estado) {
		super();
		this.primerParcial = primerParcial;
		this.segundoParcial = segundoParcial;
		this.primerRecuperatorio = primerRecuperatorio;
		this.segundoRecuperatorio = segundoRecureatorio;
		this.legajo = legajo;
		this.idCurso = idCurso;
		this.estado = estado;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public int getLegajo() {
		return legajo;
	}
	public void setLegajo(int legajo) {
		this.legajo = legajo;
	}
	public int getIdCurso() {
		return idCurso;
	}
	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}
	public int getPrimerParcial() {
		return primerParcial;
	}
	public void setPrimerParcial(int primerParcial) {
		this.primerParcial = primerParcial;
	}
	public int getSegundoParcial() {
		return segundoParcial;
	}
	public void setSegundoParcial(int segundoParcial) {
		this.segundoParcial = segundoParcial;
	}
	public int getPrimerRecuperatorio() {
		return primerRecuperatorio;
	}
	public void setPrimerRecuperatorio(int primerRecuperatorio) {
		this.primerRecuperatorio = primerRecuperatorio;
	}
	public int getSegundoRecuperatorio() {
		return segundoRecuperatorio;
	}
	public void setSegundoRecuperatorio(int segundoRecureatorio) {
		this.segundoRecuperatorio = segundoRecureatorio;
	}
	public Notas() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int  getPromedio() {
		int nota=0;
		int cantNota=2;
		if(this.primerRecuperatorio!=0){
			nota+=this.primerRecuperatorio;
		}
		else {
			nota+=this.primerParcial;
		}
		if(this.segundoRecuperatorio!=0) {
			nota+=this.segundoRecuperatorio;
		}
		else {
			nota+=this.segundoParcial;
		}
		float promedio=(float)nota/cantNota;
		return Math.round(promedio);
	}
	
	public boolean aprobo() {
		boolean aprobo=false;
		if(estado!=1 && this.getPromedio()>=6) {
			aprobo=true;
		}
		return aprobo;
	}
	
	
}

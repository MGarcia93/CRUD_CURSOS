package Models;

public class Materia {
	private int idMateria;
	private String descripcion;
	public Materia(int idMateria, String descripcion) {
		super();
		this.idMateria = idMateria;
		this.descripcion = descripcion;
	}
	public Materia() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getIdMateria() {
		return idMateria;
	}
	public void setIdMateria(int idMateria) {
		this.idMateria = idMateria;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descipcion) {
		this.descripcion = descipcion;
	}

}

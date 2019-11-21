package Models;

public class Localidad {
	private int id_localidad;
	private String Nombre;
	private String cp;
	/**
	 * @param id_localidad
	 * @param nombre
	 */
	public Localidad(int id_localidad, String nombre) {
		super();
		this.id_localidad = id_localidad;
		Nombre = nombre;
	}
	/**
	 * 
	 */
	public Localidad() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId_localidad() {
		return id_localidad;
	}
	public void setId_localidad(int id_localidad) {
		this.id_localidad = id_localidad;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	
}

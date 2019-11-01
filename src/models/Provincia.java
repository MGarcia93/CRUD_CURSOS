package Models;

import java.util.ArrayList;

public class Provincia {
	private int id_provincia;
	private String nombre;
	private ArrayList<Localidad> localidades;
	public int getId_provincia() {
		return id_provincia;
	}
	public void setId_provincia(int id_provincia) {
		this.id_provincia = id_provincia;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public ArrayList<Localidad> getLocalidades() {
		return localidades;
	}
	public void setLocalidades(ArrayList<Localidad> localidades) {
		this.localidades = localidades;
	}
	/**
	 * 
	 */
	public Provincia() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id_provincia
	 * @param nombre
	 */
	public Provincia(int id_provincia, String nombre) {
		super();
		this.id_provincia = id_provincia;
		this.nombre = nombre;
	}
	

}

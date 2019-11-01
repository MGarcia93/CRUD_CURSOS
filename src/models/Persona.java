package Models;

public class Persona {
	private int legajo;
	private String nombre;
	private String apellido;
	private String fechaNac;
	private String direccion;
	private Localidad localidad;
	private Provincia provincia;
	private String email;
	private String telefono;
	
	public int getLegajo() {
		return legajo;
	}

	public void setLegajo(int legajo) {
		this.legajo = legajo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Persona getPersona() {
		Persona persona=new Persona();
		persona.setLegajo(this.legajo);
		persona.setNombre(this.nombre);
		persona.setApellido(this.apellido);
		persona.setFechaNac(this.fechaNac);
		persona.setDireccion(this.direccion);
		persona.setLocalidad(this.localidad);
		persona.setProvincia(this.provincia);
		persona.setEmail(this.email);
		persona.setTelefono(this.telefono);
		return persona;
	}
	
	public void SetPersona(Persona persona) {

		this.legajo = persona.legajo;
		this.nombre = persona.nombre;
		this.apellido = persona.apellido;
		this.fechaNac = persona.fechaNac;
		this.direccion = persona.direccion;
		this.localidad = persona.localidad;
		this.provincia = persona.provincia;
		this.email = persona.email;
		this.telefono = persona.telefono;
	}
	
	/**
	 * 
	 */
	public Persona() {
		super();
	}
	
	
}

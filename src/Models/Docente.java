package Models;

import java.util.ArrayList;

public class Docente extends Persona {

	private ArrayList<Curso> cursos;
	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Docente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(ArrayList<Curso> cursos) {
		this.cursos = cursos;
	}

}

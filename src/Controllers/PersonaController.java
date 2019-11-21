package Controllers;

import java.lang.reflect.Type;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Models.Localidad;
import Models.Notas;
import Models.Persona;
import ModelsDao.AlumnoDAO;
import ModelsDao.PersonaDAO;

public class PersonaController {

	private String tabla;
	private String Rta;
	PersonaDAO pDAO;

	public PersonaController(String tabla) {
		super();
		this.tabla = tabla;
		pDAO = new PersonaDAO(tabla);
		this.Rta = "error";
	}

	public String Insert(HttpServletRequest request) {

		int realizo = pDAO.Insert(getDatosPost(request));

		if (realizo != 0) {
			int laster = pDAO.LastInsert();
			Rta = Integer.toString(laster);
		}
		return Rta;
	}

	public String Update(HttpServletRequest request) {
		Persona persona = getDatosPost(request);
		persona.setLegajo(Integer.parseInt(request.getParameter("legajo")));
		int realizo = pDAO.UpdatePersona(persona);
		if (realizo != 0) {
			Rta = Integer.toString(persona.getLegajo());
		}
		return Rta;
	}

	public String Delete(HttpServletRequest request) {
		int realizo = pDAO.deletePersona(Integer.parseInt(request.getParameter("legajo")));
		if (realizo != 0) {
			Rta = request.getParameter("legajo");
		}
		return Rta;
	}

	public String Listar(HttpServletRequest request) {

		ArrayList<Persona> lista = pDAO.FindPersonas("", "");
		String json = new Gson().toJson(lista);
		Rta = json;
		return Rta;
	}

	public String CalificarAlumno(HttpServletRequest request) {
		String data = request.getParameter("data");
		Type listType = new TypeToken<ArrayList<Notas>>() {
		}.getType();
		ArrayList<Notas> notas = new Gson().fromJson(data, listType);
		AlumnoDAO aDao = new AlumnoDAO();
		for (Notas nota : notas) {
			String error = "";
			if (aDao.UpdateNotas(nota) == 0) {
				if (!error.equals("")) {
					error += ", ";

				}
				error += "legajo: " + nota.getLegajo();
			}
			if (error.equals("")) {
				Rta = "ok";
			} else {
				Rta = "Error en actualizar: " + error;
			}
		}
		return Rta;
	}

	private Persona getDatosPost(HttpServletRequest request) {
		Persona p = new Persona();
		p.setNombre(request.getParameter("nombre"));
		p.setApellido(request.getParameter("apellido"));
		p.setEmail(request.getParameter("email"));
		p.setFechaNac(request.getParameter("fechaNac"));
		p.setTelefono(request.getParameter("telefono"));
		p.setDireccion((request.getParameter("direccion")));
		Localidad localidad = new Localidad();
		localidad.setId_localidad(Integer.parseInt(request.getParameter("localidad")));
		p.setLocalidad(localidad);
		return p;

	}
}

package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Controllers.PersonaController;
import Models.Alumno;
import Models.Localidad;
import Models.Notas;
import Models.Persona;
import ModelsDao.AlumnoDAO;
import ModelsDao.PersonaDAO;
import java.lang.reflect.Type;
/**
 * Servlet implementation class PersonaController
 */
@WebServlet("/PersonaServlet")
@MultipartConfig
public class PersonaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String Rta="error";
		String tipo=request.getParameter("tipo");
		PersonaController controller= new PersonaController(tipo);
		String action=request.getParameter("action");
		switch(action) {
		case "insert":
				Rta=controller.Insert(request);

			break;
		case "update":
			Rta=controller.Update(request);		
			break;
		case "delete":
			Rta=controller.Delete(request);
			
			break;
		case "listar":
				Rta=controller.Listar(request);   
			    response.setContentType("application/json");
			    
			break;
			
		case "calificar":
			Rta=controller.CalificarAlumno(request);
			break;
		}
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(Rta);
	}
	
		

}

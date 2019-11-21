package Servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controllers.CursoController;


/**
 * Servlet implementation class CursoController
 */
@WebServlet("/CursoServlet")
public class CursoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CursoServlet() {
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
		CursoController controller = new CursoController();
		String action=request.getParameter("action");
		String Rta="error";
		switch (action) {
		case "insert":
				Rta= controller.InsertCurso(Integer.parseInt(request.getParameter("anio").toString()), request.getParameter("semestre"), Integer.parseInt(request.getParameter("materia")), Integer.parseInt(request.getParameter("docente")));
			break;
		case "update":
			Rta=controller.UpdateCurso(Integer.parseInt(request.getParameter("id")),Integer.parseInt(request.getParameter("anio").toString()), request.getParameter("semestre"), Integer.parseInt(request.getParameter("materia")), Integer.parseInt(request.getParameter("docente")));
			break;
		case "delete":
			Rta=controller.DeleteCurso(Integer.parseInt(request.getParameter("id")));
			
			break;
		case "agregarAlumno":
			Rta=controller.AgregarAlumnoCurso(request);
			break;
		case "sacarAlumno":
			Rta=controller.SacarAlumnoCurso(request);
			break;
		case "listarAlumnoDisponibles":
			 	Rta=controller.ListarAlumnoDisponibles(request.getParameter("curso"));
			break;
		case "listarCursoAlumno":
				Rta=controller.listarCursoAlumno(request.getParameter("legajo"));
				
			break;
		case "listarCursoDocente":
			Rta=controller.listarCursoDocente(request.getParameter("legajo"));
			
			break;
			
		}
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(Rta);
	}

}

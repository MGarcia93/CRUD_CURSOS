package Controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Models.Docente;
import Models.Usuario;
import ModelsDao.DocenteDAO;
import ModelsDao.UsuarioDAO;

/**
 * Servlet implementation class UsuarioControlle
 */
@WebServlet("/UsuarioControlle")
@MultipartConfig
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioController() {
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
		String action=request.getParameter("action");
		switch(action) {
		case "login":
			String user=request.getParameter("txtUsuario");
			String pass=request.getParameter("txtPassword");
			UsuarioDAO dao=new UsuarioDAO();
			Usuario usuario= dao.login(user, pass);
			if(usuario==null) {
				response.getWriter().print("error");
			}
			else {
				if(usuario.getTipo()==0) {
					request.getSession().setAttribute("User", "Admin");
					request.getSession().setAttribute("Tipo", "0");
				}
				else {
					String  where="email='"+usuario.getNombre()+"'";
					DocenteDAO docenteDAO=new DocenteDAO();
					Docente docente=new Docente();
					docente.SetPersona(docenteDAO.FindPersona(where, ""));
					request.getSession().setAttribute("User", docente.getNombre()+" " +docente.getApellido());
					request.getSession().setAttribute("Tipo", "0");
				}
				response.getWriter().print("ok");
				
			}

			break;
		case "logout":
			request.getSession().invalidate();
			response.getWriter().print("ok");
			break;
		}
		response.getWriter().flush();
		
		
		//doGet(request, response);
	}

}

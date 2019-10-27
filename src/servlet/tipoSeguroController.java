package servlet;
import models.TipoSeguro;



import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TipoSeguroDao;

/**
 * Servlet implementation class tipoSeguroController
 */
@WebServlet("/tipoSeguroController")
public class tipoSeguroController extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public tipoSeguroController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String s= "cambio";
		response.getWriter().append(s).append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("mno");
		String s=request.getParameter("action");
		if(true) {
			TipoSeguro tp=new TipoSeguro();
			TipoSeguroDao tpDao=new TipoSeguroDao();
			tp=tpDao.findById(Integer.parseInt(request.getParameter("filtro")));
			request.setAttribute("tp", tp);
			response.getWriter().append("tipo seguro filtro");
		}
		else {
			response.getWriter().append("mno" +request.getParameter("action"));
		}
		//doGet(request, response);
	}

}

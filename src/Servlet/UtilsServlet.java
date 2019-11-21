package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

import Models.Localidad;
import Models.Provincia;
import ModelsDao.LocalidadDAO;
import ModelsDao.ProvinciaDAO;
/**
 * Servlet implementation class UtilsServlet
 */
@WebServlet("/UtilsServlet")
public class UtilsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UtilsServlet() {
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
		String action=request.getParameter("action");
		switch(action) {
		case "listarProvincias":
			ProvinciaDAO pDAO=new ProvinciaDAO();
			ArrayList<Provincia> lista=pDAO.FindProvincias();
			Rta=new Gson().toJson(lista);
			break;
		case "listaLocalidad":
			String provincia=request.getParameter("provincia");
			LocalidadDAO lDAO=new LocalidadDAO();
			ArrayList<Localidad> localidades= lDAO.FindLocalidades("provincia_id="+provincia);
			Rta=new Gson().toJson(localidades);
			break;
		}
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(Rta);
		
	}

}

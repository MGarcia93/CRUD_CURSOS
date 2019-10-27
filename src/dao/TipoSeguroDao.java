package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import config.Connect;
import models.TipoSeguro;

public class TipoSeguroDao {
	
	private Connect cn; 
	
	public  TipoSeguroDao() {
		cn =new Connect();
	}
	

	public TipoSeguro findById(int id) {
		TipoSeguro tp = new TipoSeguro();
		cn.conectar();
		Connection conexion=cn.getConexion();
		try {
			Statement st = conexion.createStatement();
			ResultSet result = st.executeQuery("SELECT * FROM TipoSeguros WHERE idTipo="+Integer.toString(id));
			if(result.next()) {
				tp.setDescripcion(result.getString("descripcion"));
				tp.setId(result.getInt("idTipo"));
			}
			else
			{
				tp=null;
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return tp;
	}
	
	public ArrayList<TipoSeguro> findAll() {
		TipoSeguro tp;
		ArrayList<TipoSeguro> Lista=new ArrayList<TipoSeguro>();
		cn.conectar();
		Connection conexion=cn.getConexion();
		try {
			Statement st = conexion.createStatement();
			ResultSet result = st.executeQuery("SELECT * FROM TipoSeguros");
			while(result.next()) {
				tp=new TipoSeguro();
				tp.setDescripcion(result.getString("descripcion"));
				tp.setId(result.getInt("idTipo"));
				Lista.add(tp);
			}

		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return Lista;
	}
	

}

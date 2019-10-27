package dao;
import config.Connect;
import models.Seguro;
import models.TipoSeguro;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import java.sql.Statement;

import java.util.ArrayList;

public class SeguroDao {
	private Connect cn; 
	
	public  SeguroDao() {
		cn =new Connect();
	}
	
	public int insert(Seguro seguro) {
		  int agrego=0;
		  Connection conexion;
		  try
		  {
			  
			 cn.conectar();
			 conexion= cn.getConexion();
			 if(cn!=null) {
				 String query = "insert into seguros(descripcion,idTipo,costoContratacion,costoAsegurado) values(?,?,?,?)";
				 CallableStatement preg=conexion.prepareCall(query);
				 preg.setString(1, seguro.getDescripcion());
				 preg.setInt(2, seguro.getTipoSeguro().getId());
				 preg.setDouble(3, seguro.getCostoContratacion());
				 preg.setDouble(4, seguro.getCostoAsegurado());
				 agrego=(!preg.execute())?1:0;
				 
			 }
			 else {
				 agrego=-1;
			 }
			 
		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
		  }
		  finally {
			  cn.desconectar();
			}
		
		  return agrego;
	}
	
	public ArrayList<Seguro> FindByTipoSeguro(int idTipo){
		ArrayList<Seguro> lista=new ArrayList<Seguro>();
		Seguro seguro= new Seguro();
		Connection conexion;
		try {
			TipoSeguro tp=new TipoSeguro();
			TipoSeguroDao tpDao=new TipoSeguroDao();
			tp=tpDao.findById(idTipo);
			cn.conectar();
			conexion= cn.getConexion();
			Statement st=conexion.createStatement();
			ResultSet result= st.executeQuery("SELECT * FROM seguros WHERE idTipo=" + Integer.toString(idTipo));
			while(result.next()) {
				seguro=new Seguro();
				seguro.setId(result.getInt("idSeguro"));
				seguro.setDescripcion(result.getString("descripcion"));
				seguro.setTipoSeguro(tp);
				seguro.setCostoAsegurado(result.getDouble("costoAsegurado"));
				seguro.setCostoContratacion(result.getDouble("costoContratacion"));
				lista.add(seguro);
			}
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		  finally {
			  cn.desconectar();
			}
		return lista;
	}
	
	public ArrayList<Seguro> FindAll(){
		ArrayList<Seguro> lista=new ArrayList<Seguro>();
		Seguro seguro= new Seguro();
		Connection conexion;
		try {
			cn.conectar();
			conexion= cn.getConexion();
			Statement st=conexion.createStatement();
			ResultSet result= st.executeQuery("SELECT * FROM seguros");
			while(result.next()) {
				seguro=new Seguro();
				seguro.setId(result.getInt("idSeguro"));
				seguro.setDescripcion(result.getString("descripcion"));
				TipoSeguro tp=new TipoSeguro();
				TipoSeguroDao tpDao=new TipoSeguroDao();
				tp=tpDao.findById(result.getInt("idTipo"));
				seguro.setTipoSeguro(tp);
				seguro.setCostoAsegurado(result.getDouble("costoAsegurado"));
				seguro.setCostoContratacion(result.getDouble("costoContratacion"));
				lista.add(seguro);
			}
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		  finally {
			  cn.desconectar();
			}
		return lista;
	}
	
	public int FindNextID() {
		Connection conexion;
		int max=0;
		try {
			cn.conectar();
			conexion= cn.getConexion();
			Statement st=conexion.createStatement();
			ResultSet result= st.executeQuery("SELECT MAX(idSeguro) as ultimo FROM seguros");
			if(result.next()) {
				max=result.getInt("ultimo")+1;
			}
			else {
				max=1;
			}
			cn.desconectar();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		  finally {
			  cn.desconectar();
			}
		return max;
		
	}

}

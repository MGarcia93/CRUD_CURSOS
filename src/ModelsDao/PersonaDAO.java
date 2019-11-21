package ModelsDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import java.sql.Statement;
import java.util.ArrayList;

import Config.Connect;
import Models.Localidad;
import Models.Persona;
import Models.Provincia;

public class PersonaDAO {

	private String tabla;
	protected Connect cn;

	public PersonaDAO(String tabla) {
		this.tabla = tabla;
		cn = new Connect();
	}

	public int Insert(Persona persona) {
		int fila = 0;
		Connection conexion;
		try {
			cn.conectar();
			conexion = cn.getConexion();
			if (conexion != null) {
				String query ="INSERT INTO "+this.tabla+" (nombre, apellido, FechaNac, direccion, id_localidad, email, telefono) VALUES (?, ?, ?, ?, ?, ?, ?)";
				CallableStatement preg=conexion.prepareCall(query);
				preg.setString(1,persona.getNombre());
				preg.setString(2, persona.getApellido());
				preg.setString(3,persona.getFechaNac());
				preg.setString(4,persona.getDireccion());
				preg.setInt(5, persona.getLocalidad().getId_localidad());
				preg.setString(6, persona.getEmail());
				preg.setString(7, persona.getTelefono());
				fila = (!preg.execute()) ? 1 : 0;

			} else {
				fila = -1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.desconectar();
		}

		return fila;
	}
	
	
	public Persona FindPersona(String where,String inner){
		Persona persona=null;
		Connection conexion;
		try {
			cn.conectar();
			conexion = cn.getConexion();
			if (cn != null) {
				String query ="SELECT p.legajo, p.nombre, p.apellido, p.FechaNac, p.direccion, p.id_localidad,l.nombre as localidad, l.provincia_id,prov.nombre as provincia, p.email, p.telefono ";
				query+="FROM " + this.tabla + " AS p ";
				query+="INNER JOIN localidad as l on p.id_localidad=l.id ";
				query+="INNER JOIN localidad as prov on l.provincia_id= prov.id ";
				query+=inner;
				if(!where.equals("")) {
					query+=" WHERE "+where;
				}
				
				Statement st=conexion.createStatement();
				ResultSet result=st.executeQuery(query);
				if(result.next()) {
					persona=new Persona();
					persona.setLegajo(result.getInt("legajo"));
					persona.setNombre(result.getString("nombre"));
					persona.setApellido(result.getString("apellido"));
					persona.setFechaNac(result.getString("fechaNac"));
					persona.setDireccion(result.getString("direccion"));
					Localidad localidad= new Localidad(result.getInt("id_localidad"),result.getString("localidad"));
					persona.setLocalidad(localidad);
					Provincia provincia=new Provincia(result.getInt("provincia_id"),result.getString("provincia"));
					persona.setProvincia(provincia);
					persona.setEmail(result.getString("email"));
					persona.setTelefono(result.getString("telefono"));
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.desconectar();
		}

		return persona;
		
	}

	public int UpdatePersona(Persona datos) {
		int fila=0;
		Connection conexion;
		try {
			cn.conectar();
			conexion = cn.getConexion();
			if (conexion != null) {
				String query="UPDATE "+this.tabla+" set nombre=?, apellido=?, FechaNac=?, direccion=?, "
						+ "id_localidad=?, email=?, telefono=? "
						+ " WHERE legajo=?";
				CallableStatement preg=conexion.prepareCall(query);
				preg.setString(1, datos.getNombre());
				preg.setString(2, datos.getApellido());
				preg.setString(3, datos.getFechaNac());
				preg.setString(4, datos.getDireccion());
				preg.setInt(5, datos.getLocalidad().getId_localidad());
				preg.setString(6, datos.getEmail());
				preg.setString(7, datos.getTelefono());
				preg.setInt(8, datos.getLegajo());
				fila=preg.executeUpdate();
				
			}
		}
		catch(Exception e) {
			System.out.print(e.getMessage());
		}
		finally {
			cn.desconectar();
		}
		return fila;
	}
	
	public int deletePersona(int legajo) {
		int fila=0;
		Connection conexion;
		try {
			cn.conectar();
			conexion = cn.getConexion();
			if (conexion != null) {
				String query="UPDATE "+ this.tabla+" SET estado=0 WHERE legajo="+legajo;
				Statement st = conexion.createStatement();
				fila= st.executeUpdate(query);
			}
		}
		catch(Exception e) {
			System.out.print(e.getMessage());
		}
		finally {
			cn.desconectar();
		}
		return fila;
	}
 
	
	public ArrayList<Persona> FindPersonas(String where,String inner){
		ArrayList<Persona> lista=new ArrayList<Persona>();
		Persona persona;
		Connection conexion;
		try {
			cn.conectar();
			conexion = cn.getConexion();
			if (cn != null) {
				String query ="SELECT p.legajo, p.nombre, p.apellido, p.FechaNac, p.direccion, p.id_localidad,l.nombre as localidad, l.provincia_id,prov.nombre as provincia, p.email, p.telefono ";
				query+="FROM " + this.tabla + " AS p";
				query+=" INNER JOIN localidad as l on p.id_localidad=l.id ";
				query+=" INNER JOIN localidad as prov on l.provincia_id= prov.id ";
				query+=inner;
				query+=" WHERE p.estado=1 ";
				if(!where.equals("")) {
					query+=" AND " +where;
				}
				
				Statement st=conexion.createStatement();
				ResultSet result=st.executeQuery(query);
				while(result.next()) {
					persona=new Persona();
					persona.setLegajo(result.getInt("legajo"));
					persona.setNombre(result.getString("nombre"));
					persona.setApellido(result.getString("apellido"));
					persona.setFechaNac(result.getString("fechaNac"));
					persona.setDireccion(result.getString("direccion"));
					Localidad localidad= new Localidad(result.getInt("id_localidad"),result.getString("localidad"));
					persona.setLocalidad(localidad);
					Provincia provincia=new Provincia(result.getInt("provincia_id"),result.getString("provincia"));
					persona.setProvincia(provincia);
					persona.setEmail(result.getString("email"));
					persona.setTelefono(result.getString("telefono"));
					lista.add(persona);
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.desconectar();
		}

		return lista;
		
	}
	
	public int LastInsert() {
		Connection conexion;
		int legajo=0;
		try {
			cn.conectar();
			conexion = cn.getConexion();
			if (conexion != null) {
				String query="SELECT MAX(legajo) as laster FROM "+ this.tabla;
				Statement st=conexion.createStatement();
				ResultSet result=st.executeQuery(query);
				if(result.next()) {
					legajo=result.getInt("laster");
				}
			}
		}
		catch(Exception e) {
			System.out.print(e.getMessage());
		}
		finally {
			cn.desconectar();
		}
		return legajo;
	}
}

package models;
import config.Connect;

import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.Statement;

import java.util.ArrayList;

public class Seguro {
	private int id;
	private String descripcion;
	private TipoSeguro tipoSeguro;
	private double costoContratacion;
	private double costoAsegurado;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public TipoSeguro getTipoSeguro() {
		return tipoSeguro;
	}
	public void setTipoSeguro(TipoSeguro tipoSeguro) {
		this.tipoSeguro = tipoSeguro;
	}
	public double getCostoContratacion() {
		return costoContratacion;
	}
	public void setCostoContratacion(double costoContratacion) {
		this.costoContratacion = costoContratacion;
	}
	public double getCostoAsegurado() {
		return costoAsegurado;
	}
	public void setCostoAsegurado(double costoAsegurado) {
		this.costoAsegurado = costoAsegurado;
	}
	
	
	
	
	
	
	
	
	
}

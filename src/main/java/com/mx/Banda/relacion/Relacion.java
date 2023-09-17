package com.mx.Banda.relacion;

public class Relacion {
	String nombre_banda;
	String nombre_esc;
	public Relacion() {
	}
	public Relacion(String nombre_banda, String nombre_esc) {
		this.nombre_banda = nombre_banda;
		this.nombre_esc = nombre_esc;
	}
	public String getNombre_banda() {
		return nombre_banda;
	}
	public void setNombre_banda(String nombre_banda) {
		this.nombre_banda = nombre_banda;
	}
	public String getNombre_esc() {
		return nombre_esc;
	}
	public void setNombre_esc(String nombre_esc) {
		this.nombre_esc = nombre_esc;
	}
	@Override
	public String toString() {
		return "Relacion [nombre_banda=" + nombre_banda + ", nombre_esc=" + nombre_esc + "]";
	}
	
	
}

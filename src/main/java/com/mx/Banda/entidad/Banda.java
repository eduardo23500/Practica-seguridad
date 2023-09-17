package com.mx.Banda.entidad;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="banda")
public class Banda {
@Id
@Column(name="nombre")
String nombre;
@Column(name="genero")
String genero;
@Column(name="numero_int")
int numero_int;
@Column(name="fecha")
Date fecha;
@Column(name="num_albumnes")
int num_albumnes;
@Column(name="pais")
String pais;

@ManyToMany(mappedBy="bandas")
List<Escenario> escenarios =new ArrayList<Escenario>();

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}

public String getGenero() {
	return genero;
}

public void setGenero(String genero) {
	this.genero = genero;
}

public int getNumero_int() {
	return numero_int;
}

public void setNumero_int(int num_int) {
	this.numero_int = num_int;
}

public Date getFecha() {
	return fecha;
}

public void setFecha(Date fecha) {
	this.fecha = fecha;
}

public int getNum_albumnes() {
	return num_albumnes;
}

public void setNum_albumnes(int num_albumnes) {
	this.num_albumnes = num_albumnes;
}

public String getPais() {
	return pais;
}

public void setPais(String pais) {
	this.pais = pais;
}

public List<Escenario> getEscenarios() {
	return escenarios;
}

public void setEscenarios(List<Escenario> escenarios) {
	this.escenarios = escenarios;
}

public Banda(String nombre, String genero, int numero_int, Date fecha, int num_albumnes, String pais,
		List<Escenario> escenarios) {
	this.nombre = nombre;
	this.genero = genero;
	this.numero_int = numero_int;
	this.fecha = fecha;
	this.num_albumnes = num_albumnes;
	this.pais = pais;
	this.escenarios = escenarios;
}

public Banda() {
}

@Override
public String toString() {
	return "Banda [nombre=" + nombre + ", genero=" + genero + ", numero_int=" + numero_int + ", fecha=" + fecha
			+ ", num_albumnes=" + num_albumnes + ", pais=" + pais + ", escenarios=" + escenarios + "]";
}


}

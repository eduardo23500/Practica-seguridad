package com.mx.Banda.entidad;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="escenario")
public class Escenario {
@Id
@Column(name="nombre")
String nombre;
@Column(name="pais")
String pais;
@Column(name="ciudad")
String ciudad;
@Column(name="calle")
String calle;
@Column(name="num_ext")
int num_ext;
@Column(name="capacidad")
int capacidad;
@Column(name="iluminacion")
int iluminacion;

@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL )
@JoinTable(name="escenario_banda", 
joinColumns = @JoinColumn(name="nombre_esc", referencedColumnName ="nombre"),
inverseJoinColumns = @JoinColumn(name="nombre_banda",referencedColumnName = "nombre")
		)
@JsonIgnore
List <Banda> bandas = new ArrayList<Banda>();

public Escenario() {
}

public Escenario(String nombre, String pais, String ciudad, String calle, int num_ext, int capacidad, int iluminacion,
		List<Banda> bandas) {
	this.nombre = nombre;
	this.pais = pais;
	this.ciudad = ciudad;
	this.calle = calle;
	this.num_ext = num_ext;
	this.capacidad = capacidad;
	this.iluminacion = iluminacion;
	this.bandas = bandas;
}

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}

public String getPais() {
	return pais;
}

public void setPais(String pais) {
	this.pais = pais;
}

public String getCiudad() {
	return ciudad;
}

public void setCiudad(String ciudad) {
	this.ciudad = ciudad;
}

public String getCalle() {
	return calle;
}

public void setCalle(String calle) {
	this.calle = calle;
}

public int getNum_ext() {
	return num_ext;
}

public void setNum_ext(int num_ext) {
	this.num_ext = num_ext;
}

public int getCapacidad() {
	return capacidad;
}

public void setCapacidad(int capacidad) {
	this.capacidad = capacidad;
}

public int getIluminacion() {
	return iluminacion;
}

public void setIluminacion(int iluminacion) {
	this.iluminacion = iluminacion;
}

public List<Banda> getBandas() {
	return bandas;
}

public void setBandas(List<Banda> bandas) {
	this.bandas = bandas;
}

@Override
public String toString() {
	return "Escenario [nombre=" + nombre + ", pais=" + pais + ", ciudad=" + ciudad + ", calle=" + calle + ", num_ext="
			+ num_ext + ", capacidad=" + capacidad + ", iluminacion=" + iluminacion + ", bandas=" + bandas + "]";
}


}


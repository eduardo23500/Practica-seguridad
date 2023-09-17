package com.mx.Banda.service;

import com.mx.Banda.entidad.Banda;
import com.mx.Banda.respuesta.Respuesta;

public interface MetodosBanda {

	public Respuesta guardar(Banda banda);
	public Respuesta editar(Banda banda);
	public Respuesta eliminar(Banda banda);
	public Respuesta buscar(String nombre);
	public Respuesta mostrar();
	public Respuesta mostrarEscenarios(String nombre);
	public Respuesta mostrarEscenariosPaisOrigen();
	public Respuesta mostrarEscenarioGenero(String genero);
	public Respuesta mostrarTotalAsistentes();
	public Respuesta mostrarBandaGrande();
	public Respuesta mostrarBandaPais(String pais);
	public Respuesta mostrarEscenarioFavorito(String nombre);
	public Respuesta mostrarBandaExitosa();
	public Respuesta buscarBandaEscenario(String nombre);
	
}

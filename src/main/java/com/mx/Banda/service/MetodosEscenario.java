package com.mx.Banda.service;

import com.mx.Banda.entidad.Escenario;
import com.mx.Banda.relacion.Relacion;
import com.mx.Banda.respuesta.Respuesta;

public interface MetodosEscenario {
	public Respuesta guardar(Escenario escenario);
	public Respuesta editar(Escenario escenario);
	public Respuesta eliminar(Escenario escenario);
	public Respuesta buscar(String nombre);
	public Respuesta mostrar();
	public Respuesta relacionar(Relacion relacion);
	public Respuesta mostrarMayorCapacidad();
	public Respuesta mostrarEscenariosIluminacion();
}

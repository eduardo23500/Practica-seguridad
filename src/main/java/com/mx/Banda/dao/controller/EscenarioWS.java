package com.mx.Banda.dao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.Banda.entidad.Escenario;
import com.mx.Banda.relacion.Relacion;
import com.mx.Banda.respuesta.Respuesta;
import com.mx.Banda.service.ImpEscenario;

@RestController
@RequestMapping("Escenarios")
@CrossOrigin
public class EscenarioWS {
	@Autowired
	ImpEscenario impEscenario;
	
	@PostMapping("guardar")
	public Respuesta guardar(@RequestBody Escenario escenario) {
		return impEscenario.guardar(escenario);
	}
	@PostMapping("editar")
	public Respuesta editar(@RequestBody Escenario escenario) {
		return impEscenario.editar(escenario);
	}
	@PostMapping("eliminar")
	public Respuesta eliminar(@RequestBody Escenario escenario) {
		return impEscenario.eliminar(escenario);
	}
	@PostMapping("buscar")
	public Respuesta buscar(@RequestBody String nombre) {
		return impEscenario.buscar(nombre);
	}
	@GetMapping("mostrar")
	public Respuesta mostrar() {
		return impEscenario.mostrar();
	}
	@PostMapping("relacionar")
	public Respuesta relacionar(@RequestBody Relacion relacion) {
		return impEscenario.relacionar(relacion);
	}
	@GetMapping("mostrarMayorCapacidad")
	public Respuesta mostrarMayorCapacidad() {
		return impEscenario.mostrarMayorCapacidad();
	}
	@GetMapping("mostrarEscenariosIluminacion")
	public Respuesta mostrarEscenariosIluminacion() {
		return impEscenario.mostrarEscenariosIluminacion();
	}
	
	
	
}

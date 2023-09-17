package com.mx.Banda.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.Banda.dao.BandaDao;
import com.mx.Banda.dao.EscenarioDao;
import com.mx.Banda.entidad.Banda;
import com.mx.Banda.entidad.Escenario;
import com.mx.Banda.relacion.Relacion;
import com.mx.Banda.respuesta.Respuesta;
@Service
public class ImpEscenario implements MetodosEscenario{
	@Autowired
	EscenarioDao escenarioDao;
	@Autowired
	BandaDao bandaDao;
	@Override
	public Respuesta guardar(Escenario escenario) {
		if(escenarioDao.findAll().isEmpty()) {
			escenarioDao.save(escenario);
			return new Respuesta("Escenario guardado exitosamente",escenario,true);
		}else {
			if(escenarioDao.existsById(escenario.getNombre())) {
				return new Respuesta("El escenario ya fue registrado ",null,false);
			}else {
				escenarioDao.save(escenario);
				return new Respuesta("Escenario guardado exitosamente",escenario,true);
			}
		}
	}

	@Override
	public Respuesta editar(Escenario escenario) {
		Escenario aux = new Escenario();
		if(escenarioDao.existsById(escenario.getNombre())) {
			aux= escenarioDao.findById(escenario.getNombre()).orElse(null);
			escenario.setBandas(aux.getBandas());
			escenarioDao.save(escenario);
			return new Respuesta("El escenario fue editado exitosamente",escenario,true);
		}else {
			return new Respuesta("No se encontro el escenario",null,false);
		}
	}

	@Override
	public Respuesta eliminar(Escenario escenario) {
		escenario = escenarioDao.findById(escenario.getNombre()).orElse(null);
		if(escenario != null) {
			if(!escenario.getBandas().isEmpty()) {
				escenario.setBandas(new ArrayList());
				escenarioDao.delete(escenario);
				return new Respuesta("El escenario fue eliminado",escenario,true);
			}else {
				escenarioDao.delete(escenario);
				return new Respuesta("El escenario fue eliminado",escenario,true);
			}
		}else
		{
			return new Respuesta("No hay escenario con ese nombre",null,false);
		}
	
	}

	@Override
	public Respuesta buscar(String nombre) {
		Escenario escenario = escenarioDao.findById(nombre).orElse(null);
		if(escenario !=null) {
			return new Respuesta("El escenario es :",escenario,true);
		}else {
			return new Respuesta("No existe ese escenario",null,false);
		}
	}

	@Override
	public Respuesta mostrar() {
		if(escenarioDao.findAll().isEmpty()) {
			return new Respuesta("No hay escenarios registrados",null,false);	
		}else {
			return new Respuesta("Los escenarios son :",escenarioDao.findAll(),true);	
		}
	}

	@Override
	public Respuesta relacionar(Relacion relacion) {
		Banda banda = bandaDao.findById(relacion.getNombre_banda()).orElse(null);
		Escenario escenario = escenarioDao.findById(relacion.getNombre_esc()).orElse(null);
		if(banda == null && escenario ==null) {
			return new Respuesta("La banda y el escenario no existen",null,false);
		}else if(banda !=null && escenario==null) {
			return new Respuesta("El escenario no existe",null,false);
		}else if(banda==null && escenario !=null){
			return new Respuesta("La banda no existe",null,false);
		}else {
			escenario.getBandas().add(banda);
			escenarioDao.save(escenario);
			return new Respuesta("El escenario fue asociado correctamente",escenario,true);
		}
	}

	@Override
	public Respuesta mostrarMayorCapacidad() {
		Escenario esc = new Escenario();
		int aux=0;
		for(Escenario e: escenarioDao.findAll())
		if(aux<e.getCapacidad()) {
			aux=e.getCapacidad();
		}
		for(Escenario e: escenarioDao.findAll())
			if(aux==e.getCapacidad()) {
				esc=e;
			}
		return new Respuesta("El escenario con mayor capacidad es: ",esc,true);
	}

	@Override
	public Respuesta mostrarEscenariosIluminacion() {
		List<Escenario> escenarios = new ArrayList<Escenario>();
		
		for(Escenario e: escenarioDao.findAll()) {
			if(e.getIluminacion()==1) {
				escenarios.add(e);
			}
		}
		if(!escenarios.isEmpty()){
			return new Respuesta("Los escenarios que tienen iluminacion son ",escenarios,true);
		}else {
			return new Respuesta("No hay escenarios con iluminacion",null,false);
		}
	}

}

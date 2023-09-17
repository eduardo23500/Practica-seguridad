package com.mx.Banda.service;

import java.sql.Date;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.datatype.jsr310.ser.MonthDaySerializer;
import com.mx.Banda.dao.BandaDao;
import com.mx.Banda.entidad.Banda;
import com.mx.Banda.entidad.Escenario;
import com.mx.Banda.respuesta.Respuesta;
@Service
public class ImpBanda implements MetodosBanda{
@Autowired
BandaDao bandaDao;
	@Override
	public Respuesta guardar(Banda banda) {
		if(bandaDao.findAll().isEmpty()) {
			bandaDao.save(banda);
			return new Respuesta("La banda ha sido guardada",banda,true);
		}else if(bandaDao.existsById(banda.getNombre())){
			return new Respuesta("La banda ya existe",null,false);
		}else {
			bandaDao.save(banda);
			return new Respuesta("La banda ha sido guardada",banda,true);
		}
	}

	@Override
	public Respuesta editar(Banda banda) {
		
		if(bandaDao.existsById(banda.getNombre())) {
			Banda aux = bandaDao.findById(banda.getNombre()).orElse(null);
			banda.setEscenarios(aux.getEscenarios());
			bandaDao.save(banda);
			return new Respuesta("Banda editada exitosamente",banda,true);
		}else {
			return new Respuesta("No hay bandas con ese nombre",null,false);
		}
	}
	@Override
	public Respuesta eliminar(Banda banda) {
		if(bandaDao.existsById(banda.getNombre())) {
			banda = bandaDao.findById(banda.getNombre()).orElse(null);
			banda.setEscenarios(new ArrayList());
			bandaDao.delete(banda);
			return new Respuesta("Banda eliminada exitosamente",banda,true);
		}else {
			return new Respuesta("No hay bandas con ese nombre",null,false);
		}
	}

	@Override
	public Respuesta buscar(String nombre) {
		Banda banda = bandaDao.findById(nombre).orElse(null);
		if(banda != null) {
			return new Respuesta("La banda es : ",banda,true);
		}else {
			return new Respuesta("No existe esa banda",null,false);
		}
	}

	@Override
	public Respuesta mostrar() {
		if(bandaDao.findAll().isEmpty()) {
			return new Respuesta("No hay banda registradas",null,false);
		}else
			return new Respuesta("Las bandas son :",bandaDao.findAll(),true);
	}

	@Override
	public Respuesta mostrarEscenarios(String nombre) {
		Banda banda = bandaDao.findById(nombre).orElse(null);
		if(banda !=null) {
			if(!banda.getEscenarios().isEmpty()) {
				return new Respuesta("Los escenarios donde ha tocado esa banda",banda.getEscenarios(),true);
			}else {
				return new Respuesta("La banda no ha tocado en ningun escenario",null,false);
			}
				
		}else {
			return new Respuesta("La banda no existe",null,false);
		}
		
	}

	@Override
	public Respuesta mostrarEscenariosPaisOrigen() {
		boolean flag=false;
		List<Escenario> escenarios = new ArrayList<Escenario>();
		for(Banda b: bandaDao.findAll()) {
			for(Escenario bb: b.getEscenarios()) {
				if(b.getPais().equals(bb.getPais())) {
					escenarios.add(bb);
					flag = true;
				}
			}
		}
		if(flag==true) {
			return new Respuesta("Los escenarios donde toco un banda del mismo pais son :",escenarios,true);
		}else {
			return new Respuesta("No hay escenarios donde haya tocado una banda local ",null,false);	
		}
		
	}

	@Override
	public Respuesta mostrarTotalAsistentes() {
		int total=0;
		HashMap<String,Integer> hash = new HashMap<String,Integer>();
		for(Banda b: bandaDao.findAll()) {
			for(Escenario bb: b.getEscenarios()) {
				total=bb.getCapacidad()+total;
				hash.put(b.getNombre(),total);
			}
			total=0;
		}
		return new Respuesta("Los asistentes por banda son :",hash,true);
	}

	@Override
	public Respuesta mostrarBandaGrande() {
		 Banda banda = new Banda();
		int aux=0;
		for(Banda b: bandaDao.findAll()) {
			if(aux<b.getNumero_int()) {
				aux=b.getNumero_int();
			}
		}
		for(Banda b: bandaDao.findAll()) {
			if(aux==b.getNumero_int()) {
				 banda=b;
			}
		}
		return new Respuesta("La banda con mas integrantes es :",banda,true);
	}

	@Override
	public Respuesta mostrarBandaPais(String pais) {
		List<Banda> bandas = new ArrayList<Banda>();
		for(Banda b: bandaDao.findAll()) {
			if(b.getPais().equals(pais)) {
				bandas.add(b);
			}
		}
		if(!bandas.isEmpty()) {
			return new Respuesta("Las bandas de ese pais son :",bandas,true);
		}else {
			return new Respuesta("No hay bandas de ese pais",null,false);
		}
	}

	@Override
	public Respuesta mostrarEscenarioGenero(String genero) {
		HashMap<String,Integer> hash = new HashMap<String,Integer>();
		int contador=0;
		for(Banda b: bandaDao.findAll()) {
			if(b.getGenero().equals(genero)) {
				for(Escenario bb: b.getEscenarios()) {
					hash.put(bb.getNombre(), 0);
					
				}
			}
		}
		for(Banda b: bandaDao.findAll()) {
			if(b.getGenero().equals(genero)) {
				for(Escenario bb: b.getEscenarios()) {
					if(hash.containsKey(bb.getNombre())) {
						contador = hash.get(bb.getNombre())+1;
						hash.put(bb.getNombre(), contador);
					}
					contador=0;
				}
			}
		}
		return new Respuesta("nose",hash,true);
	}

	@Override
	public Respuesta mostrarBandaExitosa() {
		HashMap<Integer,Banda> hash = new HashMap<Integer,Banda>();
		
		int resultado=0,aux=0;
		Date fechaA= new Date(resultado);
		for(Banda b: bandaDao.findAll()) {
			
			resultado = (fechaA.toLocalDate().now().getYear() - b.getFecha().toLocalDate().getYear())/b.getEscenarios().size();    
			hash.put(resultado, b);
		}
		for(Integer i: hash.keySet()) {
			if(aux>i || aux == 0) {
				aux=i;
			}
		}
		String cadena="Las banda exitosa de acuerdo a su relacion de ("+aux+") es :";
		return new Respuesta(cadena,hash.get(aux),true);
	}

	@Override
	public Respuesta mostrarEscenarioFavorito(String nombre) {
		int aux=0;
		int total=0;
		String key="";
		HashMap<String,Integer> hash = new HashMap<String,Integer>();
		for(Banda b: bandaDao.findAll()) {
				for(Escenario bb: b.getEscenarios()) {
					if(b.getNombre().equals(nombre)) {
					hash.put(bb.getNombre(), 0);
				}
			}
		}
		for(Banda b: bandaDao.findAll()) {
			if(b.getNombre().equals(nombre)) {
				for(Escenario e: b.getEscenarios()) {
					if(hash.containsKey(e.getNombre())) {
						total = hash.get(e.getNombre())+1;
						hash.put(e.getNombre(), total);
					}
				}
			}
		}
		for(Integer i: hash.values()) {
			if(aux<i) {
				aux=i;
			}
		}
		for(Integer i: hash.values()) {
			for(String s: hash.keySet())
			if(aux==i) {
				aux=i;
				key=s;
			}
		}
		String cadena = "El escenario favorito es : ("+key+ " )con una cantidad de ";
		return new Respuesta(cadena,hash.get(key),true);
	}

	@Override
	public Respuesta buscarBandaEscenario(String nombre) {
		boolean flag = false;
		List<Banda> bandas = new ArrayList<Banda>();
		for(Banda b: bandaDao.findAll()) {
			for(Escenario e: b.getEscenarios()) {
				if(e.getNombre().equals(nombre)) {
					bandas.add(b);
					flag = true;
				}
			}
		}
		if(flag == true) {
			return new Respuesta("Las bandas de esa escenaio son :",bandas,true);
		}else {
			return new Respuesta("Las banda no tiene escenarios",null,false);
		}
	}

}

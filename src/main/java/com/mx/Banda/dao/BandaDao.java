package com.mx.Banda.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.Banda.entidad.Banda;

public interface BandaDao extends JpaRepository<Banda, String> {

}

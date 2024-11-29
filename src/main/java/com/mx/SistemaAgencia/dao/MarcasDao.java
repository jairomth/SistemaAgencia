package com.mx.SistemaAgencia.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fasterxml.jackson.annotation.OptBoolean;
import com.mx.SistemaAgencia.model.Marcas;

public interface MarcasDao extends JpaRepository<Marcas, Long>{
	
	public Optional<Marcas> findByNombre(String nombre);
}

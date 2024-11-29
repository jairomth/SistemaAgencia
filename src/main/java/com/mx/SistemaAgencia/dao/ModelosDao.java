package com.mx.SistemaAgencia.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.SistemaAgencia.model.Modelos;

public interface ModelosDao extends JpaRepository<Modelos, Long>{
	
	public Optional<Modelos> findByNombre(String nombre);
}

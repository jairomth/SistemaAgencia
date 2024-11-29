package com.mx.SistemaAgencia.service;

import java.util.List;

import org.aspectj.apache.bcel.util.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.SistemaAgencia.dao.MarcasDao;
import com.mx.SistemaAgencia.model.Marcas;

@Service
public class MarcasServImp {
	@Autowired
	MarcasDao marcaDao;

	@Transactional(readOnly = true)
	public List<Marcas> listar() {
		return marcaDao.findAll();
	}

	// Validar que el id y nombre no se repita, contrario guardar
	@Transactional
	public String guardar(Marcas marca) {

		if (marcaDao.existsById(marca.getId())) {
			return "El ID ya existe en la base de datos";
		}

		if (marcaDao.findByNombre(marca.getNombre()).isPresent()) {
			return "El nombre ya existe en la base de datos";
		}

		marcaDao.save(marca);
		return "Se guardó con éxito";
	}

	@Transactional(readOnly = true)
	public Marcas buscar(Long id) {
		return marcaDao.findById(id).orElse(null);
	}

	// Validar que el id exista, caso contrario editar
	@Transactional
	public boolean editar(Marcas marca) {
		if (marcaDao.existsById(marca.getId())) {
			marcaDao.save(marca);
			return true;
		}
		return false;
	}

	// Validar que el id exista, caso contrario eliminar
	@Transactional
	public boolean eliminar(Long id) {
		if (marcaDao.existsById(id)) {

			marcaDao.deleteById(id);
			return true;
		}
		return false;
	}

}

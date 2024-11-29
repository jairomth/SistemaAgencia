package com.mx.SistemaAgencia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.SistemaAgencia.dao.MarcasDao;
import com.mx.SistemaAgencia.dao.ModelosDao;
import com.mx.SistemaAgencia.model.Modelos;

@Service
public class ModelosServImp {
	// Inyección de dependencia
	@Autowired
	ModelosDao modeloDao;

	@Autowired
	MarcasDao marcaDao;

	@Transactional(readOnly = true)
	public List<Modelos> listar() {
		return modeloDao.findAll();

	}

	// Validar id, nombre y que el id de la marca existe
	@Transactional
	public String guardar(Modelos modelo) {

		// Siempre validar primero las llaves foraneas.

		if (!marcaDao.existsById(modelo.getMarca().getId())) {
			return "El ID de la marca no existe.";
		}

		// Verificar si el ID del modelo ya existe
		if (modeloDao.existsById(modelo.getId())) {
			return "Este ID ya existe.";
		}

		// Verificar si el nombre del modelo ya existe
		if (modeloDao.findByNombre(modelo.getNombre()).isPresent()) {
			return "Este nombre ya existe.";
		}

		// Guardar el modelo
		modeloDao.save(modelo);
		return "Se guardó con éxito.";

	}

	@Transactional(readOnly = true)
	public Modelos buscar(Long id) {
		return modeloDao.findById(id).orElse(null);
	}

	@Transactional
	public String editar(Modelos modelo) {
		if (!marcaDao.existsById(modelo.getMarca().getId())) {
			return "La idMarca no existe.";
		}

		if (!modeloDao.existsById(modelo.getId())) {
			return "El idModelo no existe.";
		}

		modeloDao.save(modelo);
		return "Modelo editado con éxito. Ya puede consultarlo en la base de datos";
	}

	@Transactional
	public boolean eliminar(Modelos modelo) {
		boolean bandera = false;
		if (modeloDao.existsById(modelo.getId())) {
			modeloDao.deleteById(modelo.getId());
			return bandera;
		}

		bandera = true;
		return bandera;
	}
}

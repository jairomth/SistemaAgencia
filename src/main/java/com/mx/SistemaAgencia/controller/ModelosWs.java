package com.mx.SistemaAgencia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.SistemaAgencia.model.Modelos;
import com.mx.SistemaAgencia.service.ModelosServImp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "ModelosWs")
@CrossOrigin
public class ModelosWs {

	@Autowired
	ModelosServImp modeloImp;

	// http://localhost:9000/ModelosWs/listar
	@GetMapping("listar")
	public List<Modelos> listar() {
		return modeloImp.listar();
	}

	// http://localhost:9000/ModelosWs/guardar
	@PostMapping("guardar")
	public ResponseEntity<String> guardar(@RequestBody Modelos modelo) {
		String mensaje = modeloImp.guardar(modelo);

		switch (mensaje) {
		case "El ID de la marca no existe.":
			return new ResponseEntity<>("El ID de la marca no existe.", HttpStatus.BAD_REQUEST);

		case "Este ID ya existe.":
		case "Este nombre ya existe.":
			return new ResponseEntity<>(mensaje, HttpStatus.CONFLICT);

		case "Se guardó con éxito.":
			return new ResponseEntity<>(mensaje, HttpStatus.CREATED);

		default:
			return new ResponseEntity<>("Ocurrió un error inesperado.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// http://localhost:9000/ModelosWs/buscar
	@PostMapping("buscar")
	public Modelos buscar(@RequestBody Modelos modelo) {
		return modeloImp.buscar(modelo.getId());
	}

	// http://localhost:9000/ModelosWs/editar
	@PostMapping("editar")
	public ResponseEntity<String> editar(@RequestBody Modelos modelo) {
		String mensaje = modeloImp.editar(modelo);

		switch (mensaje) {
		case "La idMarca no existe.":
			return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
		case "El idModelo no existe.":
			return new ResponseEntity<>(mensaje, HttpStatus.CONFLICT);
		case "Modelo editado con éxito. Ya puede consultarlo en la base de datos":
			return new ResponseEntity<>(mensaje, HttpStatus.OK);
		default:
			return new ResponseEntity<>("Ocurrió un error inesperado.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// http://localhost:9000/ModelosWs/eliminar
	@PostMapping("eliminar")
	public ResponseEntity<String> eliminar(@RequestBody Modelos modelo) {
		boolean mensaje = modeloImp.eliminar(modelo);
		if (!mensaje) {
			return new ResponseEntity<>("Eliminado de forma exitosa.", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("El idModelo no existe.", HttpStatus.CONFLICT);
		}

	}
}
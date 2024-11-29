package com.mx.SistemaAgencia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.SistemaAgencia.model.Marcas;
import com.mx.SistemaAgencia.service.MarcasServImp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(path = "MarcasWs")
@CrossOrigin
public class MarcasWs {

	@Autowired
	MarcasServImp marcaImp;

	// http://localhost:9000/MarcasWs/listar
	@GetMapping("listar")
	public List<Marcas> listar() {
		return marcaImp.listar();
	}

	// http://localhost:9000/MarcasWs/guardar
	@PostMapping("guardar")
	public ResponseEntity<?> guardar(@RequestBody Marcas marca) {
		String mensaje = marcaImp.guardar(marca);
		if (mensaje.equals("El ID ya existe en la base de datos")
				|| mensaje.equals("El nombre ya existe en la base de datos")) {
			return new ResponseEntity<String>(mensaje, HttpStatus.CONFLICT);
		} else {

			return new ResponseEntity<>(marca, HttpStatus.CREATED);
		}

	}

	// http://localhost:9000/MarcasWs/buscar
	@PostMapping("buscar")
	public Marcas buscar(@RequestBody Marcas marca) {
		return marcaImp.buscar(marca.getId());
	}

	// El responsentity sirve para retornar algo del lado del servidor, si uno
	// quiere que en un método se retornen varias cosas se le pone "?"

	// http://localhost:9000/MarcasWs/editar
	@PostMapping("editar")
	public ResponseEntity<?> editar(@RequestBody Marcas marca) {
		boolean response = marcaImp.editar(marca);

		if (response) {

			return new ResponseEntity<>(marca, HttpStatus.OK);
		} else {

			return new ResponseEntity<String>("El ID no existe.", HttpStatus.NOT_FOUND);
		}
	}

	// http://localhost:9000/MarcasWs/eliminar
	@PostMapping("eliminar")
	public ResponseEntity<String> eliminar(@RequestBody Marcas marca) {
		boolean response = marcaImp.eliminar(marca.getId());

		if (response) {
			return new ResponseEntity<String>("Eliminado con éxito", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("El ID no existe.", HttpStatus.NOT_FOUND);

		}
	}

}

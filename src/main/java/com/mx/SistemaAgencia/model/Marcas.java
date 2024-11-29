package com.mx.SistemaAgencia.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/*CREATE TABLE MARCAS_28(
ID NUMBER PRIMARY KEY,
NOMBRE VARCHAR2(100) NOT NULL,
ORIGEN VARCHAR2(60) NOT NULL,
ESLOGAN VARCHAR2(100)
);*/

@Entity
@Table(name = "MARCAS_28")
public class Marcas {
	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "ORIGEN")
	private String origen;

	@Column(name = "ESLOGAN")
	private String slogan;

	// Cardinalidad
	@OneToMany(mappedBy = "marca", cascade = CascadeType.ALL)
	List<Modelos> lista = new ArrayList<>();

	public Marcas() {
	}

//Al agregar esto hay que quitar la variable lista porque sino se cicla
	public Marcas(Long id, String nombre, String origen, String slogan) {
		this.id = id;
		this.nombre = nombre;
		this.origen = origen;
		this.slogan = slogan;
	}

//El constructor con todos los parametros y el toString se utilizan cuando se van a mostrar algo en consola.
	@Override
	public String toString() {
		return "Marcas [id=" + id + ", nombre=" + nombre + ", origen=" + origen + ", slogan=" + slogan + "]\n";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}
}

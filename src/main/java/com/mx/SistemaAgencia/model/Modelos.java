package com.mx.SistemaAgencia.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/*CREATE TABLE MODELOS_28(
ID NUMBER PRIMARY KEY,
NOMBRE VARCHAR2(100) NOT NULL,
TIPO VARCHAR2(70) NOT NULL,
PRECIO NUMBER NOT NULL,
FECHA_LANZ DATE NOT NULL,
ID_MARCA NUMBER NOT NULL,
FOREIGN KEY(ID_MARCA) REFERENCES MARCAS_AGENCIA(ID)
);*/

//Siempre hay que comenzar por la tabla fuerte

@Entity
@Table(name = "MODELOS_28")
public class Modelos {

	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "TIPO")
	private String tipo;

	@Column(name = "Precio")
	private Float precio;

	@Column(name = "FECHA_LANZ")
	private Date fechaLanz;

	// Cardinalidad
	// Muchos modelos pertenecen a una marca

	// fetch: indicamos como debe ser cargada la entidad
	// FetchType.EAGER con este le indicamos que la relación debe ser cargado al
	// momento
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_MARCA")
	Marcas marca; // Esta variable de tipo objeto debe coincidir con el mappedBy (mappedBy =
					// "marca")
	//Aquí en los contructores y en el encapsulamiento sí agregamos marca.
	
	//Una forma fácil para recordar sería: SIEMPRE SE OMITE LA LISTA

	public Modelos() {
	}

	public Modelos(Long id, String nombre, String tipo, Float precio, Date fechaLanz, Marcas marca) {
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.precio = precio;
		this.fechaLanz = fechaLanz;
		this.marca = marca;
	}

	@Override
	public String toString() {
		return "Modelos [id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + ", precio=" + precio + ", fechaLanz="
				+ fechaLanz + ", marca=" + marca + "]";
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public Date getFechaLanz() {
		return fechaLanz;
	}

	public void setFechaLanz(Date fechaLanz) {
		this.fechaLanz = fechaLanz;
	}

	public Marcas getMarca() {
		return marca;
	}

	public void setMarca(Marcas marca) {
		this.marca = marca;
	}

}

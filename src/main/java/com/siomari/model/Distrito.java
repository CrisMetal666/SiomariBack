package com.siomari.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author crismetal
 * 
 * 
 *
 */
@Table(name = "distrito", catalog = "siomari_db")
@Entity
public class Distrito {

	@Id
	private int id;

	@Column(name = "nombre", length = 100, nullable = false)
	private String nombre;
	
	public Distrito() {
		
	}

	public Distrito(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}

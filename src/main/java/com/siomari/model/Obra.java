package com.siomari.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author crismetal
 *
 */
@Table(name = "obra", catalog = "siomari_db")
@Entity
public class Obra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "nombre", length = 100, nullable = false)
	private String nombre;
	
	@JsonIgnore
	@OneToMany(mappedBy = "obraId", fetch = FetchType.LAZY)
	private List<CanalObra> lstCanalObra;

	public Obra() {
	}

	public Obra(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
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

	public List<CanalObra> getLstCanalObra() {
		return lstCanalObra;
	}

	public void setLstCanalObra(List<CanalObra> lstCanalObra) {
		this.lstCanalObra = lstCanalObra;
	}
	
	
}

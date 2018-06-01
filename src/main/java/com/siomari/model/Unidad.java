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

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * 
 * @author crismetal
 *
 */
@Table(name = "unidad", catalog = "siomari_db")
@Entity
public class Unidad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "nombre", length = 100, nullable = false)
	private String nombre;
	
	@Column(name = "canal_servidor")
	private Integer canalServidor;
	
	@LazyCollection(LazyCollectionOption.TRUE)
	@OneToMany(mappedBy = "unidadId", fetch = FetchType.LAZY)
	private List<Zona> lstZona;
	
	public Unidad() {
		
	}
	
	public Unidad(int id) {
		this.id = id;
	}

	public Unidad(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public Unidad(int id, String nombre, List<Zona> lstZona) {
		this.id = id;
		this.nombre = nombre;
		this.lstZona = lstZona;
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

	public List<Zona> getLstZona() {
		return lstZona;
	}

	public void setLstZona(List<Zona> lstZona) {
		this.lstZona = lstZona;
	}

	public Integer getCanalServidor() {
		return canalServidor;
	}

	public void setCanalServidor(Integer canalServidor) {
		this.canalServidor = canalServidor;
	}
	
	
	
}

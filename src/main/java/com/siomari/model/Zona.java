package com.siomari.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * @author crismetal
 *
 */
@Table(name = "zona", catalog = "siomari_db")
@Entity
public class Zona {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "nombre", length = 100, nullable = false)
	private String nombre;
	
	@Column(name = "canal_servidor")
	private Integer canalServidor;
	
	@ManyToOne
	@JoinColumn(name = "unidad_id", nullable = false)
	private Unidad unidadId;
	
	@OneToMany(mappedBy = "zonaId", fetch = FetchType.LAZY)
	private List<Seccion> lstSeccion;
	
	public Zona() {
	
	}
	
	public Zona(int id) {
		this.id = id;
	}
	
	public Zona(int id, int canalServidor) {
		this.canalServidor = canalServidor;
		this.id = id;
	}
	
	public Zona(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public Zona(int id, String nombre, Unidad unidadId) {
		this.id = id;
		this.nombre = nombre;
		this.unidadId = unidadId;
	}

	public Zona(int id, String nombre, Unidad unidadId, List<Seccion> lstSeccion) {
		this.id = id;
		this.nombre = nombre;
		this.unidadId = unidadId;
		this.lstSeccion = lstSeccion;
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

	public Integer getCanalServidor() {
		return canalServidor;
	}

	public void setCanalServidor(Integer canalServidor) {
		this.canalServidor = canalServidor;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Unidad getUnidadId() {
		return unidadId;
	}

	public void setUnidadId(Unidad unidadId) {
		this.unidadId = unidadId;
	}

	public List<Seccion> getLstSeccion() {
		return lstSeccion;
	}

	public void setLstSeccion(List<Seccion> lstSeccion) {
		this.lstSeccion = lstSeccion;
	}
	
	
}

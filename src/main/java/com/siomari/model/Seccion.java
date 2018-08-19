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
@Table(name = "seccion", catalog = "siomari_db")
@Entity
public class Seccion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "nombre", length = 100, nullable = false)
	private String nombre;

	@Column(name = "canal_servidor")
	private Integer canalServidor;

	@ManyToOne
	@JoinColumn(name = "zona_id", nullable = false)
	private Zona zonaId;

	@OneToMany(mappedBy = "seccionId", fetch = FetchType.LAZY)
	private List<SeccionCanal> lstSeccionCanal;

	public Seccion() {
	}

	public Seccion(int id) {
		this.id = id;
	}

	public Seccion(int id, Integer canalServidor) {
		this.id = id;
		this.canalServidor = canalServidor;
	}

	public Seccion(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public Seccion(int id, String nombre, Zona zonaId) {
		this.id = id;
		this.nombre = nombre;
		this.zonaId = zonaId;
	}

	public Seccion(int id, String nombre, Zona zonaId, List<SeccionCanal> lstSeccionCanal) {
		this.id = id;
		this.nombre = nombre;
		this.zonaId = zonaId;
		this.lstSeccionCanal = lstSeccionCanal;
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

	public Zona getZonaId() {
		return zonaId;
	}

	public void setZonaId(Zona zonaId) {
		this.zonaId = zonaId;
	}

	public List<SeccionCanal> getLstSeccionCanal() {
		return lstSeccionCanal;
	}

	public void setLstSeccionCanal(List<SeccionCanal> lstSeccionCanal) {
		this.lstSeccionCanal = lstSeccionCanal;
	}

	public Integer getCanalServidor() {
		return canalServidor;
	}

	public void setCanalServidor(Integer canalServidor) {
		this.canalServidor = canalServidor;
	}

}

package com.siomari.model;

import java.util.List;

import javax.persistence.CascadeType;
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
@Table(name = "canal", catalog = "siomari_db")
@Entity
public class Canal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "codigo", length = 100, nullable = false, unique = true)
	private String codigo;
	
	@Column(name = "nombre", length = 100, nullable = false)
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name = "seccion_id", nullable = false)
	private Seccion seccionId;
	
	@OneToMany(mappedBy = "canalId", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Obra> lstObra;

	public Canal() {
	}
	
	public Canal(int id) {
		this.id = id;
	}
	
	public Canal(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public Canal(int id, String codigo, String nombre, Seccion seccionId) {
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
		this.seccionId = seccionId;
	}

	public Canal(int id, String codigo, String nombre, Seccion seccionId, List<Obra> lstObra) {
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
		this.seccionId = seccionId;
		this.lstObra = lstObra;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Seccion getSeccionId() {
		return seccionId;
	}

	public void setSeccionId(Seccion seccionId) {
		this.seccionId = seccionId;
	}

	public List<Obra> getLstObra() {
		return lstObra;
	}

	public void setLstObra(List<Obra> lstObra) {
		this.lstObra = lstObra;
	}
	
	
	
}

package com.siomari.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author crismetal
 *
 */
@Table(name = "canal_obra", catalog = "siomari_db")
@Entity
public class CanalObra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "descripcion", columnDefinition = "text")
	private String descripcion;
	
	@Column(name = "x")
	private Double x;
	
	@Column(name = "y")
	private Double y;
	
	@Column(name = "altitud")
	private Double altitud;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "canal_id", nullable = false)
	private Canal canalId;
	
	@ManyToOne
	@JoinColumn(name = "obra_id", nullable = false)
	private Obra obraId;
	
	public CanalObra() {
	}

	public CanalObra(int id, String descripcion, Double x, Double y, Double altitud, Obra obraId) {
		this.id = id;
		this.descripcion = descripcion;
		this.x = x;
		this.y = y;
		this.altitud = altitud;
		this.obraId = obraId;
	}



	public CanalObra(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Canal getCanalId() {
		return canalId;
	}

	public void setCanalId(Canal canalId) {
		this.canalId = canalId;
	}

	public Obra getObraId() {
		return obraId;
	}

	public void setObraId(Obra obraId) {
		this.obraId = obraId;
	}

	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}

	public Double getAltitud() {
		return altitud;
	}

	public void setAltitud(Double altitud) {
		this.altitud = altitud;
	}




	
	
	
}

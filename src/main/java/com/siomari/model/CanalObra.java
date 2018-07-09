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

	@Column(name = "descripcion", columnDefinition = "text", nullable = false)
	private String descripcion;

	@Column(name = "ultima_intervension", columnDefinition = "text", nullable = true)
	private String ultimaIntervension;

	@Column(name = "imagen", length = 50, nullable = true)
	private String imagen;

	@Column(name = "x", nullable = true)
	private Double x;

	@Column(name = "y", nullable = true)
	private Double y;

	@Column(name = "altitud", nullable = true)
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
	
	
	
	public CanalObra(int id, String descripcion, String ultimaIntervension, String imagen, Double x, Double y,
			Double altitud, int canalId, Obra obraId) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.ultimaIntervension = ultimaIntervension;
		this.imagen = imagen;
		this.x = x;
		this.y = y;
		this.altitud = altitud;
		this.canalId = new Canal(canalId);
		this.obraId = obraId;
	}

	public CanalObra(int id, Obra obraId) {
		this.id = id;
		this.obraId = obraId;
	}
	
	
	public CanalObra(String imagen) {
		this.imagen = imagen;
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

	public String getUltimaIntervension() {
		return ultimaIntervension;
	}

	public void setUltimaIntervension(String ultimaIntervension) {
		this.ultimaIntervension = ultimaIntervension;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	@Override
	public String toString() {
		return "CanalObra [id=" + id + ", descripcion=" + descripcion + ", ultimaIntervension=" + ultimaIntervension
				+ ", imagen=" + imagen + ", x=" + x + ", y=" + y + ", altitud=" + altitud + "]";
	}

	
}

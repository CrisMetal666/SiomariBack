package com.siomari.model;

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
@Table(name = "seccion_canal", catalog = "siomari_db")
@Entity
public class SeccionCanal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "seccion_id", nullable = false)
	private Seccion seccionId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "canal_id", nullable = false)
	private Canal canalId;
	
	public SeccionCanal() {
	}
	
	public SeccionCanal(int id) {
		this.id = id;
	}

	public SeccionCanal(int id, Seccion seccionId, Canal canalId) {
		this.id = id;
		this.seccionId = seccionId;
		this.canalId = canalId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Seccion getSeccionId() {
		return seccionId;
	}

	public void setSeccionId(Seccion seccionId) {
		this.seccionId = seccionId;
	}

	public Canal getCanalId() {
		return canalId;
	}

	public void setCanalId(Canal canalId) {
		this.canalId = canalId;
	}
	
	

}

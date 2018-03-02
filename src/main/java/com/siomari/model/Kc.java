package com.siomari.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Entity
@Table(name = "kc", catalog = "siomari_db")
public class Kc {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "kc", nullable = false)
	private float kc;
	
	@Column(name = "mes", nullable = false)
	private int mes;
	
	@ManyToOne
	@JoinColumn(name = "cultivo_id", nullable = false)
	private Cultivo cultivoId;
	
	public Kc() {
	}
	
	public Kc(int id) {
		this.id = id;
	}
	
	public Kc(int id, float kc, int mes) {
		this.id = id;
		this.kc = kc;
		this.mes = mes;
	}
	
	public Kc(int id, float kc, int mes, Cultivo cultivoId) {
		this.id = id;
		this.kc = kc;
		this.mes = mes;
		this.cultivoId = cultivoId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getKc() {
		return kc;
	}

	public void setKc(float kc) {
		this.kc = kc;
	}

	public Cultivo getCultivoId() {
		return cultivoId;
	}

	public void setCultivoId(Cultivo cultivoId) {
		this.cultivoId = cultivoId;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}
	
	
}

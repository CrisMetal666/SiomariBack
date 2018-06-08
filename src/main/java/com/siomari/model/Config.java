package com.siomari.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author crismetal
 *
 */
@Entity
@Table(name = "config", catalog = "siomari_db")
public class Config {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "lamina", nullable = false)
	private double lamina;
	
	@Column(name = "eficiencia", nullable = false)
	private double eficiencia;
	
	@Column(name = "caudal", nullable = false)
	private double caudal;
	
	@Column(name = "horas", nullable = false)
	private double horas;
	
	@Column(name = "costo", nullable = false)
	private double costo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getLamina() {
		return lamina;
	}

	public void setLamina(double lamina) {
		this.lamina = lamina;
	}

	public double getEficiencia() {
		return eficiencia;
	}

	public void setEficiencia(double eficiencia) {
		this.eficiencia = eficiencia;
	}

	public double getCaudal() {
		return caudal;
	}

	public void setCaudal(double caudal) {
		this.caudal = caudal;
	}

	public double getHoras() {
		return horas;
	}

	public void setHoras(double horas) {
		this.horas = horas;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}
	
	


}

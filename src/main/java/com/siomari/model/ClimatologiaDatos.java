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
@Table(name = "climatologia_datos", catalog = "siomari_db")
public class ClimatologiaDatos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "evaporacion", nullable = false)
	private float evaporacion;
	
	@Column(name = "precipitacion_efecto", nullable = false)
	private float precipitacionEfecto;
	
	@Column(name = "precipitacion", nullable = false)
	private float precipitacion;
	
	@Column(name = "q_precipitacion", nullable = false)
	private float qPrecipitacion;

	public ClimatologiaDatos() {
	}
	
	public ClimatologiaDatos(int id) {
		this.id = id;
	}
	
	public ClimatologiaDatos(int id, float evaporacion, float precipitacionEfecto, float precipitacion,
			float qPrecipitacion) {
		this.id = id;
		this.evaporacion = evaporacion;
		this.precipitacionEfecto = precipitacionEfecto;
		this.precipitacion = precipitacion;
		this.qPrecipitacion = qPrecipitacion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getEvaporacion() {
		return evaporacion;
	}

	public void setEvaporacion(float evaporacion) {
		this.evaporacion = evaporacion;
	}

	public float getPrecipitacionEfecto() {
		return precipitacionEfecto;
	}

	public void setPrecipitacionEfecto(float precipitacionEfecto) {
		this.precipitacionEfecto = precipitacionEfecto;
	}

	public float getPrecipitacion() {
		return precipitacion;
	}

	public void setPrecipitacion(float precipitacion) {
		this.precipitacion = precipitacion;
	}

	public float getqPrecipitacion() {
		return qPrecipitacion;
	}

	public void setqPrecipitacion(float qPrecipitacion) {
		this.qPrecipitacion = qPrecipitacion;
	}
	
	
	
}

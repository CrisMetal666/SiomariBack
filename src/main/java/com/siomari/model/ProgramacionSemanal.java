package com.siomari.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * 
 * @author crismetal
 *
 */
@Entity
@Table(name = "programacion_semanal", catalog = "siomari_db")
public class ProgramacionSemanal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "ln", nullable = false)
	private double lamina;

	@Column(name = "area", nullable = false)
	private double area;

	@JsonSerialize(using = ToStringSerializer.class)
	@Column(name = "fecha", nullable = false)
	private LocalDate fecha;

	@Column(name = "eficiencia", nullable = false)
	private double eficiencia;

	@Column(name = "canal_id", nullable = false)
	private int canalId;

	@Transient
	private double capacidadCanal;

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

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public int getCanalId() {
		return canalId;
	}

	public void setCanalId(int canalId) {
		this.canalId = canalId;
	}

	public double getCapacidadCanal() {
		return capacidadCanal;
	}

	public void setCapacidadCanal(double capacidadCanal) {
		this.capacidadCanal = capacidadCanal;
	}

	public double getEficiencia() {
		return eficiencia;
	}

	public void setEficiencia(double eficiencia) {
		this.eficiencia = eficiencia;
	}

}

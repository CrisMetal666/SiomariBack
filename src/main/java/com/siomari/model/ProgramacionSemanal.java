package com.siomari.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	@Column(name = "caudal", nullable = false)
	private double caudal;

	@Column(name = "cszu")
	private Integer cszu;

	@Column(name = "tipo")
	private Integer tipo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "canal_id", nullable = false)
	private Canal canalId;

	@Transient
	private double capacidadCanal;

	public ProgramacionSemanal() {
	}

	public ProgramacionSemanal(int id) {
		this.id = id;
	}

	public ProgramacionSemanal(int id, double lamina, double area, LocalDate fecha, double eficiencia, int canalId,
			double caudal) {
		this.id = id;
		this.lamina = lamina;
		this.area = area;
		this.fecha = fecha;
		this.eficiencia = eficiencia;
		this.canalId = new Canal(canalId);
		this.caudal = caudal;
	}

	public ProgramacionSemanal(int id, double lamina, double area, LocalDate fecha, double eficiencia, double caudal,
			Integer cszu, int canalId) {
		this.id = id;
		this.lamina = lamina;
		this.area = area;
		this.fecha = fecha;
		this.eficiencia = eficiencia;
		this.caudal = caudal;
		this.cszu = cszu;
		this.canalId = new Canal(canalId);
	}

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

	public Canal getCanalId() {
		return canalId;
	}

	public void setCanalId(Canal canalId) {
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

	public double getCaudal() {
		return caudal;
	}

	public void setCaudal(double caudal) {
		this.caudal = caudal;
	}

	public Integer getCszu() {
		return cszu;
	}

	public void setCszu(Integer cszu) {
		this.cszu = cszu;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "ProgramacionSemanal [id=" + id + ", lamina=" + lamina + ", area=" + area + ", fecha=" + fecha
				+ ", eficiencia=" + eficiencia + ", caudal=" + caudal + ", cszu=" + cszu + ", tipo=" + tipo
				+ ", canalId=" + canalId + ", capacidadCanal=" + capacidadCanal + "]";
	}

}

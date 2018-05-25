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

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * 
 * @author crismetal
 *
 */
@Entity
@Table(name = "manejo_agua", catalog = "siomari_db")
public class ManejoAgua {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@JsonSerialize(using = ToStringSerializer.class)
	@Column(name = "fecha", nullable = false)
	private LocalDate fecha;
	
	@Column(name = "q_solicitado", nullable = false)
	private double qSolicitado;
	
	@Column(name = "q_extraido", nullable = false)
	private double qExtraido;
	
	@Column(name = "q_servido", nullable = false)
	private double qServido;
	
	@Column(name = "area", nullable = false)
	private double area;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "canal_id", nullable = false)
	private Canal canalId;
	
	public ManejoAgua() {
	}

	public ManejoAgua(LocalDate fecha, double qExtraido, double qServido, double area) {
		this.fecha = fecha;
		this.qExtraido = qExtraido;
		this.qServido = qServido;
		this.area = area;
	}

	public ManejoAgua(double qExtraido, double qServido, double area) {
		this.qExtraido = qExtraido;
		this.qServido = qServido;
		this.area = area;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public double getqSolicitado() {
		return qSolicitado;
	}

	public void setqSolicitado(double qSolicitado) {
		this.qSolicitado = qSolicitado;
	}

	public double getqExtraido() {
		return qExtraido;
	}

	public void setqExtraido(double qExtraido) {
		this.qExtraido = qExtraido;
	}

	public double getqServido() {
		return qServido;
	}

	public void setqServido(double qServido) {
		this.qServido = qServido;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public Canal getCanalId() {
		return canalId;
	}

	public void setCanalId(Canal canalId) {
		this.canalId = canalId;
	}

	@Override
	public String toString() {
		return "ManejoAgua [id=" + id + ", fecha=" + fecha + ", qSolicitado=" + qSolicitado + ", qExtraido=" + qExtraido
				+ ", qServido=" + qServido + ", area=" + area + "]";
	}
	
	
}

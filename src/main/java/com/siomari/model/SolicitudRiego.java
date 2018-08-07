package com.siomari.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "solicitud_riego", catalog = "siomari_db")
@Entity
public class SolicitudRiego {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@JsonSerialize(using = ToStringSerializer.class)
	@Column(name = "fecha", nullable = false)
	private LocalDate fecha;

	@ManyToOne
	@JoinColumn(name = "predio_id", nullable = false)
	private Predio predioId;
	
	public SolicitudRiego() {
	}

	public SolicitudRiego(int id, LocalDate fecha, int predioId) {
		this.id = id;
		this.fecha = fecha;
		this.predioId = new Predio(predioId);
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

	public Predio getPredioId() {
		return predioId;
	}

	public void setPredioId(Predio predioId) {
		this.predioId = predioId;
	}

}

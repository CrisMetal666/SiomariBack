package com.siomari.model;

import java.time.LocalDateTime;

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
@Entity
@Table(name = "entrega", catalog = "siomari_db")
public class Entrega {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@JsonSerialize(using = ToStringSerializer.class)
	@Column(name = "entrega", nullable = false)
	private LocalDateTime entrega;
	
	@JsonSerialize(using = ToStringSerializer.class)
	@Column(name = "suspension", nullable = false)
	private LocalDateTime suspension;
	
	@ManyToOne
	@JoinColumn(name = "predio_id", nullable = false)
	private Predio predioId;
	
	public Entrega() {
	}

	public Entrega(LocalDateTime entrega, LocalDateTime suspension) {
		this.entrega = entrega;
		this.suspension = suspension;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getEntrega() {
		return entrega;
	}

	public void setEntrega(LocalDateTime entrega) {
		this.entrega = entrega;
	}

	public LocalDateTime getSuspension() {
		return suspension;
	}

	public void setSuspension(LocalDateTime suspension) {
		this.suspension = suspension;
	}

	public Predio getPredioId() {
		return predioId;
	}

	public void setPredioId(Predio predioId) {
		this.predioId = predioId;
	}
	
	

}

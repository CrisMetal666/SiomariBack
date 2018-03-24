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
@Table(name = "plan_siembra", catalog = "siomari_db")
public class PlanSiembra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "year")
	private int year;
	
	@Column(name = "mes")
	private short mes;
	
	@Column(name = "periodo")
	private short periodo;
	
	public PlanSiembra() {
	}
	
	public PlanSiembra(int id) {
		this.id = id;
	}
	
	public PlanSiembra(int year, short mes, short periodo) {
		this.year = year;
		this.mes = mes;
		this.periodo = periodo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public short getMes() {
		return mes;
	}

	public void setMes(short mes) {
		this.mes = mes;
	}

	public short getPeriodo() {
		return periodo;
	}

	public void setPeriodo(short periodo) {
		this.periodo = periodo;
	}
	
	
}

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
@Table(name = "plan_siembra_datos", catalog = "siomari_db")
public class PlanSiembraDatos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "periodo1", nullable = false)
	private float periodo1;
	
	@Column(name = "periodo2", nullable = false)
	private float periodo2;
	
	@Column(name = "periodo3", nullable = false)
	private float periodo3;
	
	@Column(name = "dias_riego", nullable = false)
	private int diasRiego;
	
	@Column(name = "horas_riego", nullable = false)
	private int horasRiego;

	public PlanSiembraDatos() {
	}
	
	public PlanSiembraDatos(int id) {
		this.id = id;
	}
	
	public PlanSiembraDatos(int id, float periodo1, float periodo2, float periodo3, int diasRiego, int horasRiego) {
		this.id = id;
		this.periodo1 = periodo1;
		this.periodo2 = periodo2;
		this.periodo3 = periodo3;
		this.diasRiego = diasRiego;
		this.horasRiego = horasRiego;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getPeriodo1() {
		return periodo1;
	}

	public void setPeriodo1(float periodo1) {
		this.periodo1 = periodo1;
	}

	public float getPeriodo2() {
		return periodo2;
	}

	public void setPeriodo2(float periodo2) {
		this.periodo2 = periodo2;
	}

	public float getPeriodo3() {
		return periodo3;
	}

	public void setPeriodo3(float periodo3) {
		this.periodo3 = periodo3;
	}

	public int getDiasRiego() {
		return diasRiego;
	}

	public void setDiasRiego(int diasRiego) {
		this.diasRiego = diasRiego;
	}

	public int getHorasRiego() {
		return horasRiego;
	}

	public void setHorasRiego(int horasRiego) {
		this.horasRiego = horasRiego;
	}
	
	
}

package com.siomari.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * @author crismetal
 *
 */
@Entity
@Table(name = "cultivo", catalog = "siomari_db")
public class Cultivo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "nombre", length = 50, nullable = false, unique = true)
	private String nombre;
	
	@OneToMany(mappedBy = "cultivoId", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Kc> lstKc;
	
	@OneToMany(mappedBy = "cultivoId", fetch = FetchType.LAZY)
	private List<PlanSiembra> lstPlanSiembra;
	
	public Cultivo() {
	}
	
	public Cultivo(int id) {
		this.id = id;
	}
	
	public Cultivo(int id, String nombre) {
		this.id = id;
	}

	public Cultivo(int id, String nombre, List<Kc> lstKc, List<PlanSiembra> lstPlanSiembra) {
		this.id = id;
		this.nombre = nombre;
		this.lstKc = lstKc;
		this.lstPlanSiembra = lstPlanSiembra;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Kc> getLstKc() {
		return lstKc;
	}

	public void setLstKc(List<Kc> lstKc) {
		this.lstKc = lstKc;
	}

	public List<PlanSiembra> getLstPlanSiembra() {
		return lstPlanSiembra;
	}

	public void setLstPlanSiembra(List<PlanSiembra> lstPlanSiembra) {
		this.lstPlanSiembra = lstPlanSiembra;
	}
	
	
}

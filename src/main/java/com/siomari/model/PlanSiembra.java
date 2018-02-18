package com.siomari.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	private int year;
	
	@ManyToOne
	@JoinColumn(name = "enero", nullable = false)
	private PlanSiembraDatos enero;
	
	@ManyToOne
	@JoinColumn(name = "febrero", nullable = false)
	private PlanSiembraDatos febrero;
	
	@ManyToOne
	@JoinColumn(name = "marzo", nullable = false)
	private PlanSiembraDatos marzo;
	
	@ManyToOne
	@JoinColumn(name = "abril", nullable = false)
	private PlanSiembraDatos abril;
	
	@ManyToOne
	@JoinColumn(name = "mayo", nullable = false)
	private PlanSiembraDatos mayo;
	
	@ManyToOne
	@JoinColumn(name = "junio", nullable = false)
	private PlanSiembraDatos junio;
	
	@ManyToOne
	@JoinColumn(name = "julio", nullable = false)
	private PlanSiembraDatos julio;
	
	@ManyToOne
	@JoinColumn(name = "agosto", nullable = false)
	private PlanSiembraDatos agosto;
	
	@ManyToOne
	@JoinColumn(name = "septiembre", nullable = false)
	private PlanSiembraDatos septiembre;
	
	@ManyToOne
	@JoinColumn(name = "octubre", nullable = false)
	private PlanSiembraDatos octubre;
	
	@ManyToOne
	@JoinColumn(name = "noviembre", nullable = false)
	private PlanSiembraDatos noviembre;
	
	@ManyToOne
	@JoinColumn(name = "diciembre", nullable = false)
	private PlanSiembraDatos diciembre;
	
	@ManyToOne
	@JoinColumn(name = "cultivo_id", nullable = false)
	private Cultivo cultivoId;
	
	public PlanSiembra() {
	}
	
	public PlanSiembra(int year) {
		this.year = year;
	}

	public PlanSiembra(int year, PlanSiembraDatos enero, PlanSiembraDatos febrero, PlanSiembraDatos marzo,
			PlanSiembraDatos abril, PlanSiembraDatos mayo, PlanSiembraDatos junio, PlanSiembraDatos julio,
			PlanSiembraDatos agosto, PlanSiembraDatos septiembre, PlanSiembraDatos octubre, PlanSiembraDatos noviembre,
			PlanSiembraDatos diciembre, Cultivo cultivoId) {
		this.year = year;
		this.enero = enero;
		this.febrero = febrero;
		this.marzo = marzo;
		this.abril = abril;
		this.mayo = mayo;
		this.junio = junio;
		this.julio = julio;
		this.agosto = agosto;
		this.septiembre = septiembre;
		this.octubre = octubre;
		this.noviembre = noviembre;
		this.diciembre = diciembre;
		this.cultivoId = cultivoId;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public PlanSiembraDatos getEnero() {
		return enero;
	}

	public void setEnero(PlanSiembraDatos enero) {
		this.enero = enero;
	}

	public PlanSiembraDatos getFebrero() {
		return febrero;
	}

	public void setFebrero(PlanSiembraDatos febrero) {
		this.febrero = febrero;
	}

	public PlanSiembraDatos getMarzo() {
		return marzo;
	}

	public void setMarzo(PlanSiembraDatos marzo) {
		this.marzo = marzo;
	}

	public PlanSiembraDatos getAbril() {
		return abril;
	}

	public void setAbril(PlanSiembraDatos abril) {
		this.abril = abril;
	}

	public PlanSiembraDatos getMayo() {
		return mayo;
	}

	public void setMayo(PlanSiembraDatos mayo) {
		this.mayo = mayo;
	}

	public PlanSiembraDatos getJunio() {
		return junio;
	}

	public void setJunio(PlanSiembraDatos junio) {
		this.junio = junio;
	}

	public PlanSiembraDatos getJulio() {
		return julio;
	}

	public void setJulio(PlanSiembraDatos julio) {
		this.julio = julio;
	}

	public PlanSiembraDatos getAgosto() {
		return agosto;
	}

	public void setAgosto(PlanSiembraDatos agosto) {
		this.agosto = agosto;
	}

	public PlanSiembraDatos getSeptiembre() {
		return septiembre;
	}

	public void setSeptiembre(PlanSiembraDatos septiembre) {
		this.septiembre = septiembre;
	}

	public PlanSiembraDatos getOctubre() {
		return octubre;
	}

	public void setOctubre(PlanSiembraDatos octubre) {
		this.octubre = octubre;
	}

	public PlanSiembraDatos getNoviembre() {
		return noviembre;
	}

	public void setNoviembre(PlanSiembraDatos noviembre) {
		this.noviembre = noviembre;
	}

	public PlanSiembraDatos getDiciembre() {
		return diciembre;
	}

	public void setDiciembre(PlanSiembraDatos diciembre) {
		this.diciembre = diciembre;
	}

	public Cultivo getCultivoId() {
		return cultivoId;
	}

	public void setCultivoId(Cultivo cultivoId) {
		this.cultivoId = cultivoId;
	}
	
	
}

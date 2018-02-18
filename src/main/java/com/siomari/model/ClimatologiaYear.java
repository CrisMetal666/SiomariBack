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
@Table(name = "climatologia_year", catalog = "siomari_db")
public class ClimatologiaYear {

	@Id
	private int year;
	
	@ManyToOne
	@JoinColumn(name = "enero", nullable = false)
	private ClimatologiaDatos enero;
	
	@ManyToOne
	@JoinColumn(name = "febrero", nullable = false)
	private ClimatologiaDatos febrero;
	
	@ManyToOne
	@JoinColumn(name = "marzo", nullable = false)
	private ClimatologiaDatos marzo;
	
	@ManyToOne
	@JoinColumn(name = "abril", nullable = false)
	private ClimatologiaDatos abril;
	
	@ManyToOne
	@JoinColumn(name = "mayo", nullable = false)
	private ClimatologiaDatos mayo;
	
	@ManyToOne
	@JoinColumn(name = "junio", nullable = false)
	private ClimatologiaDatos junio;
	
	@ManyToOne
	@JoinColumn(name = "julio", nullable = false)
	private ClimatologiaDatos julio;
	
	@ManyToOne
	@JoinColumn(name = "agosto", nullable = false)
	private ClimatologiaDatos agosto;
	
	@ManyToOne
	@JoinColumn(name = "septiembre", nullable = false)
	private ClimatologiaDatos septiembre;
	
	@ManyToOne
	@JoinColumn(name = "octubre", nullable = false)
	private ClimatologiaDatos octubre;
	
	@ManyToOne
	@JoinColumn(name = "noviembre", nullable = false)
	private ClimatologiaDatos noviembre;
	
	@ManyToOne
	@JoinColumn(name = "diciembre", nullable = false)
	private ClimatologiaDatos diciembre;

	public ClimatologiaYear() {
	}
	
	public ClimatologiaYear(int year) {
		this.year = year;
	}
	
	public ClimatologiaYear(int year, ClimatologiaDatos enero, ClimatologiaDatos febrero, ClimatologiaDatos marzo,
			ClimatologiaDatos abril, ClimatologiaDatos mayo, ClimatologiaDatos junio, ClimatologiaDatos julio,
			ClimatologiaDatos agosto, ClimatologiaDatos septiembre, ClimatologiaDatos octubre,
			ClimatologiaDatos noviembre, ClimatologiaDatos diciembre) {
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
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public ClimatologiaDatos getEnero() {
		return enero;
	}

	public void setEnero(ClimatologiaDatos enero) {
		this.enero = enero;
	}

	public ClimatologiaDatos getFebrero() {
		return febrero;
	}

	public void setFebrero(ClimatologiaDatos febrero) {
		this.febrero = febrero;
	}

	public ClimatologiaDatos getMarzo() {
		return marzo;
	}

	public void setMarzo(ClimatologiaDatos marzo) {
		this.marzo = marzo;
	}

	public ClimatologiaDatos getAbril() {
		return abril;
	}

	public void setAbril(ClimatologiaDatos abril) {
		this.abril = abril;
	}

	public ClimatologiaDatos getMayo() {
		return mayo;
	}

	public void setMayo(ClimatologiaDatos mayo) {
		this.mayo = mayo;
	}

	public ClimatologiaDatos getJunio() {
		return junio;
	}

	public void setJunio(ClimatologiaDatos junio) {
		this.junio = junio;
	}

	public ClimatologiaDatos getJulio() {
		return julio;
	}

	public void setJulio(ClimatologiaDatos julio) {
		this.julio = julio;
	}

	public ClimatologiaDatos getAgosto() {
		return agosto;
	}

	public void setAgosto(ClimatologiaDatos agosto) {
		this.agosto = agosto;
	}

	public ClimatologiaDatos getSeptiembre() {
		return septiembre;
	}

	public void setSeptiembre(ClimatologiaDatos septiembre) {
		this.septiembre = septiembre;
	}

	public ClimatologiaDatos getOctubre() {
		return octubre;
	}

	public void setOctubre(ClimatologiaDatos octubre) {
		this.octubre = octubre;
	}

	public ClimatologiaDatos getNoviembre() {
		return noviembre;
	}

	public void setNoviembre(ClimatologiaDatos noviembre) {
		this.noviembre = noviembre;
	}

	public ClimatologiaDatos getDiciembre() {
		return diciembre;
	}

	public void setDiciembre(ClimatologiaDatos diciembre) {
		this.diciembre = diciembre;
	}
	
	

}

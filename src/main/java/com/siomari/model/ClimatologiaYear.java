package com.siomari.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "enero", nullable = true)
	private ClimatologiaDatos enero;

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "febrero", nullable = true)
	private ClimatologiaDatos febrero;

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "marzo", nullable = true)
	private ClimatologiaDatos marzo;

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "abril", nullable = true)
	private ClimatologiaDatos abril;

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "mayo", nullable = true)
	private ClimatologiaDatos mayo;

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "junio", nullable = true)
	private ClimatologiaDatos junio;

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "julio", nullable = true)
	private ClimatologiaDatos julio;

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "agosto", nullable = true)
	private ClimatologiaDatos agosto;

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "septiembre", nullable = true)
	private ClimatologiaDatos septiembre;

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "octubre", nullable = true)
	private ClimatologiaDatos octubre;

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "noviembre", nullable = true)
	private ClimatologiaDatos noviembre;

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "diciembre", nullable = true)
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

	public ClimatologiaYear(int year, int enero, int febrero, int marzo, int abril, int mayo, int junio, int julio,
			int agosto, int septiembre, int octubre, int noviembre, int diciembre) {
		this.year = year;
		this.enero = new ClimatologiaDatos(enero);
		this.febrero = new ClimatologiaDatos(febrero);
		this.marzo = new ClimatologiaDatos(marzo);
		this.abril = new ClimatologiaDatos(abril);
		this.mayo = new ClimatologiaDatos(mayo);
		this.junio = new ClimatologiaDatos(junio);
		this.julio = new ClimatologiaDatos(julio);
		this.agosto = new ClimatologiaDatos(agosto);
		this.septiembre = new ClimatologiaDatos(septiembre);
		this.octubre = new ClimatologiaDatos(octubre);
		this.noviembre = new ClimatologiaDatos(noviembre);
		this.diciembre = new ClimatologiaDatos(diciembre);
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

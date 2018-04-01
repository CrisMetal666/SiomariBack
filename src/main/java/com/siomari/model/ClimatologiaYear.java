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
	private Decada enero;

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "febrero", nullable = true)
	private Decada febrero;

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "marzo", nullable = true)
	private Decada marzo;

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "abril", nullable = true)
	private Decada abril;

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "mayo", nullable = true)
	private Decada mayo;

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "junio", nullable = true)
	private Decada junio;

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "julio", nullable = true)
	private Decada julio;

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "agosto", nullable = true)
	private Decada agosto;

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "septiembre", nullable = true)
	private Decada septiembre;

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "octubre", nullable = true)
	private Decada octubre;

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "noviembre", nullable = true)
	private Decada noviembre;

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "diciembre", nullable = true)
	private Decada diciembre;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Decada getEnero() {
		return enero;
	}

	public void setEnero(Decada enero) {
		this.enero = enero;
	}

	public Decada getFebrero() {
		return febrero;
	}

	public void setFebrero(Decada febrero) {
		this.febrero = febrero;
	}

	public Decada getMarzo() {
		return marzo;
	}

	public void setMarzo(Decada marzo) {
		this.marzo = marzo;
	}

	public Decada getAbril() {
		return abril;
	}

	public void setAbril(Decada abril) {
		this.abril = abril;
	}

	public Decada getMayo() {
		return mayo;
	}

	public void setMayo(Decada mayo) {
		this.mayo = mayo;
	}

	public Decada getJunio() {
		return junio;
	}

	public void setJunio(Decada junio) {
		this.junio = junio;
	}

	public Decada getJulio() {
		return julio;
	}

	public void setJulio(Decada julio) {
		this.julio = julio;
	}

	public Decada getAgosto() {
		return agosto;
	}

	public void setAgosto(Decada agosto) {
		this.agosto = agosto;
	}

	public Decada getSeptiembre() {
		return septiembre;
	}

	public void setSeptiembre(Decada septiembre) {
		this.septiembre = septiembre;
	}

	public Decada getOctubre() {
		return octubre;
	}

	public void setOctubre(Decada octubre) {
		this.octubre = octubre;
	}

	public Decada getNoviembre() {
		return noviembre;
	}

	public void setNoviembre(Decada noviembre) {
		this.noviembre = noviembre;
	}

	public Decada getDiciembre() {
		return diciembre;
	}

	public void setDiciembre(Decada diciembre) {
		this.diciembre = diciembre;
	}

}

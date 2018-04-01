package com.siomari.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "decada", catalog = "siomari_db")
public class Decada {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "decada_1", nullable = true)
	private ClimatologiaDatos decada1;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "decada_2", nullable = true)
	private ClimatologiaDatos decada2;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "decada_3", nullable = true)
	private ClimatologiaDatos decada3;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ClimatologiaDatos getDecada1() {
		return decada1;
	}

	public void setDecada1(ClimatologiaDatos decada1) {
		this.decada1 = decada1;
	}

	public ClimatologiaDatos getDecada2() {
		return decada2;
	}

	public void setDecada2(ClimatologiaDatos decada2) {
		this.decada2 = decada2;
	}

	public ClimatologiaDatos getDecada3() {
		return decada3;
	}

	public void setDecada3(ClimatologiaDatos decada3) {
		this.decada3 = decada3;
	}
	
	

}

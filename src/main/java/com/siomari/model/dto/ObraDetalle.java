package com.siomari.model.dto;

import java.util.List;

import com.siomari.model.CanalObra;

/**
 * agrupara las obras de los canales, nombre de la obra y con sus respectivos
 * canalObra
 * 
 * @author crismetal
 *
 */
public class ObraDetalle {

	private String obra;
	private List<CanalObra> lstCanalObra;

	public String getObra() {
		return obra;
	}

	public void setObra(String obra) {
		this.obra = obra;
	}

	public List<CanalObra> getLstCanalObra() {
		return lstCanalObra;
	}

	public void setLstCanalObra(List<CanalObra> lstCanalObra) {
		this.lstCanalObra = lstCanalObra;
	}

}

package com.siomari.model.dto;

import java.util.List;

/**
 * guardara la informacion de la lamina neta, lamina bruta, eficiencia y fechas,
 * para ser graficados
 * 
 * @author crismetal
 *
 */
public class LnLamEficiencia {

	private List<Double> lstLn;
	private List<Double> lstLam;
	private List<Double> lstEfic;
	private List<String> lstFecha;

	public List<Double> getLstLn() {
		return lstLn;
	}

	public void setLstLn(List<Double> lstLn) {
		this.lstLn = lstLn;
	}

	public List<Double> getLstLam() {
		return lstLam;
	}

	public void setLstLam(List<Double> lstLam) {
		this.lstLam = lstLam;
	}

	public List<Double> getLstEfic() {
		return lstEfic;
	}

	public void setLstEfic(List<Double> lstEfic) {
		this.lstEfic = lstEfic;
	}

	public List<String> getLstFecha() {
		return lstFecha;
	}

	public void setLstFecha(List<String> lstFecha) {
		this.lstFecha = lstFecha;
	}

}

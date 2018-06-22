package com.siomari.model.dto;

import java.util.List;

/**
 * 
 * @author crismetal
 *
 */
public class Facturacion {

	private String nombreUsuario;
	private String nombrePredio;
	private List<EntregaInfo> lstEntregaInfo;

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getNombrePredio() {
		return nombrePredio;
	}

	public void setNombrePredio(String nombrePredio) {
		this.nombrePredio = nombrePredio;
	}

	public List<EntregaInfo> getLstEntregaInfo() {
		return lstEntregaInfo;
	}

	public void setLstEntregaInfo(List<EntregaInfo> lstEntregaInfo) {
		this.lstEntregaInfo = lstEntregaInfo;
	}

}

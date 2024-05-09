package com.nttdata.demo.models;

import jakarta.validation.constraints.NotBlank;

public class ModeloJson {

	@NotBlank
	private String documento;
	@NotBlank
	private String tipoDocument;
	
	
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getTipoDocument() {
		return tipoDocument;
	}
	public void setTipoDocument(String tipoDocument) {
		this.tipoDocument = tipoDocument;
	}
	
	
}

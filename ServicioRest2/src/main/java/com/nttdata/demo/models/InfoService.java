package com.nttdata.demo.models;

public class InfoService {

	private String codigo;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String findOne(Long id) {
		return "El id es: "+id;
	}
}

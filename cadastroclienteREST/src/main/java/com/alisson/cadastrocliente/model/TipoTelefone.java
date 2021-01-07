package com.alisson.cadastrocliente.model;

public enum TipoTelefone {
	
	RESIDENCIAL("Residencial"),
	COMERCIAL("Comercial"),
	CELULAR("Celular");

	private String descricao;
	
	TipoTelefone(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return this.descricao;
	}
}

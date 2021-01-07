package com.alisson.cadastrocliente.model;

public class Usuario {
	
	private String mensagem;

	public Usuario(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	@Override
	public String toString() {
		return "Usuario [mensagem=" + mensagem + "]";
	}
	
}

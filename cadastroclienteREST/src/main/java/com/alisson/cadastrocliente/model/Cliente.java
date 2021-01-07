package com.alisson.cadastrocliente.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_cliente", unique=true, nullable=false, precision=4, scale=0)
	private Long id;


	@NotNull(message = "Nome é obrigatório")
	@Size(min = 3, message = "O Nome deve conter no mínimo 3 caracteres")
	@Size(max = 100, message = "O Nome deve conter no máximo 100 caracteres")
	private String nome;
	
	@NotNull(message = "CPF é obrigatório")
	private String cpf;
	  
	@Transient
	//@NotNull(message = "É obrigatório informar ao menos 1 endereço")
	private List<Endereco> enderecos;
  
	@Transient
	//@NotNull(message = "É obrigatório informar ao menos 1 telefone")
	private List<Telefone> telefones;
  
	@Transient
	//@NotNull(message = "É obrigatório informar ao menos 1 email")
	private List<Email> emails ;
		

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public List<Email> getEmails() {
		return emails;
	}

	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}


	 
}

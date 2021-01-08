package com.alisson.cadastrocliente.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Transient;

@Entity
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotBlank(message = "Nome é obrigatório")
	@Size(min = 3, message = "O Nome deve conter no mínimo 3 caracteres")
	@Size(max = 100, message = "O Nome deve conter no máximo 100 caracteres")
	private String nome;
	
	@NotBlank(message = "CPF é obrigatório")
	private String cpf;
	
	/*
	 * private Endereco endereco; private Telefone telefone; private Email email;
	 * 
	 * //@NotEmpty(message = "Endereço é obrigatório")
	 * 
	 * @OneToMany(fetch = FetchType.EAGER) private List<Endereco> enderecos;
	 * 
	 * //@NotEmpty(message = "Telfone é obrigatório")
	 * 
	 * @OneToMany(fetch = FetchType.EAGER) private List<Telefone> telefones;
	 * 
	 * //@NotEmpty(message = "Email é obrigatório")
	 * 
	 * @OneToMany(fetch = FetchType.EAGER) private List<Email> emails ;
	 */
	
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
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

	/*
	 * @Transient public Endereco getEndereco() { return endereco; }
	 * 
	 * public void setEndereco(Endereco endereco) { this.endereco = endereco; }
	 * 
	 * @Transient public Telefone getTelefone() { return telefone; }
	 * 
	 * public void setTelefone(Telefone telefone) { this.telefone = telefone; }
	 * 
	 * @Transient public Email getEmail() { return email; }
	 * 
	 * public void setEmail(Email email) { this.email = email; }
	 * 
	 * public List<Endereco> getEnderecos() { return enderecos; }
	 * 
	 * public void setEnderecos(List<Endereco> enderecos) { this.enderecos =
	 * enderecos; }
	 * 
	 * public List<Telefone> getTelefones() { return telefones; }
	 * 
	 * public void setTelefones(List<Telefone> telefones) { this.telefones =
	 * telefones; }
	 * 
	 * public List<Email> getEmails() { return emails; }
	 * 
	 * public void setEmails(List<Email> emails) { this.emails = emails; }
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}

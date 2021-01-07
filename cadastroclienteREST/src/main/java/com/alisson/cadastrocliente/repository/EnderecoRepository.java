package com.alisson.cadastrocliente.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alisson.cadastrocliente.model.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco,Long>{
	public void deleteByIdCliente(Long idCliente);
	public List<Endereco> findByIdCliente(Long idCliente);
}

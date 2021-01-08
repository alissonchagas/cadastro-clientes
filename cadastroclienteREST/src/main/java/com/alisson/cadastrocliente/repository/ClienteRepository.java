package com.alisson.cadastrocliente.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alisson.cadastrocliente.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,Long>{
	public List<Cliente> findByNomeContaining(String nome);
}

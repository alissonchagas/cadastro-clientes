package com.alisson.cadastrocliente.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alisson.cadastrocliente.model.Telefone;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone,Long>{
	public void deleteByIdCliente(Long idCliente);
	public List<Telefone> findByIdCliente(Long idCliente);
}

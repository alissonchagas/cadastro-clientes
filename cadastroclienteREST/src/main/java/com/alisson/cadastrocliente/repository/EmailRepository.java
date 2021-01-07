package com.alisson.cadastrocliente.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alisson.cadastrocliente.model.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email,Long>{
	public void deleteByIdCliente(Long idCliente);
	public List<Email> findByIdCliente(Long idCliente);
}

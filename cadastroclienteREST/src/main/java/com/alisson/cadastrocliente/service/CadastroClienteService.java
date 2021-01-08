package com.alisson.cadastrocliente.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alisson.cadastrocliente.model.Cliente;
import com.alisson.cadastrocliente.repository.ClienteRepository;

@Service
public class CadastroClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public void salvar(Cliente cliente) {
		clienteRepository.save(cliente);
	}
	
	public void excluir(Long codigo) {
		clienteRepository.deleteById(codigo);
	}
	
	public List<Cliente> filtrar(Cliente filtro){
		String nome = filtro.getNome()==null?"%":filtro.getNome();
		return  clienteRepository.findByNomeContaining(nome);
	}
	
}

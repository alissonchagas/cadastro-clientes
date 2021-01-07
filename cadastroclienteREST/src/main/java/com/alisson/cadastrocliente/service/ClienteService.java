package com.alisson.cadastrocliente.service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alisson.cadastrocliente.model.Cliente;
import com.alisson.cadastrocliente.model.Email;
import com.alisson.cadastrocliente.model.Endereco;
import com.alisson.cadastrocliente.model.Telefone;
import com.alisson.cadastrocliente.repository.ClienteRepository;
import com.alisson.cadastrocliente.repository.EmailRepository;
import com.alisson.cadastrocliente.repository.EnderecoRepository;
import com.alisson.cadastrocliente.repository.TelefoneRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private TelefoneRepository telefoneRepository;
	
	@Autowired
	private EmailRepository emailRepository;
	
	
	public List<Cliente> listarTodos(){
		List<Cliente> clientes = clienteRepository.findAll();
		for (Cliente cliente : clientes) {
			cliente.setEnderecos(enderecoRepository.findByIdCliente(cliente.getId()));
			cliente.setTelefones(telefoneRepository.findByIdCliente(cliente.getId()));
			cliente.setEmails(emailRepository.findByIdCliente(cliente.getId()));
		}
		
		return clientes;
	}
	
	public Cliente salvar(Cliente cliente) throws ValidationException{
		clienteRepository.saveAndFlush(cliente);
		salvarEnderecos(cliente);
		salvarTelefones(cliente);
		salvarEmails(cliente);
		return cliente;
	}
	
	public Cliente recuperar(Long idCliente) {
		Cliente cliente = clienteRepository.findById(idCliente).get();
		cliente.setEnderecos(enderecoRepository.findByIdCliente(idCliente));
		cliente.setTelefones(telefoneRepository.findByIdCliente(idCliente));
		cliente.setEmails(emailRepository.findByIdCliente(idCliente));
		return cliente;
	}
	   
	@Transactional
	public void excluir(Long idCliente) {
		enderecoRepository.deleteByIdCliente(idCliente);
		telefoneRepository.deleteByIdCliente(idCliente);
		emailRepository.deleteByIdCliente(idCliente);
		clienteRepository.deleteById(idCliente);
	}
	
	public void salvarEnderecos(Cliente cliente) {
		for(Endereco endereco: cliente.getEnderecos()) {
			endereco.setCliente(cliente);
		}
		cliente.setEnderecos(enderecoRepository.saveAll(cliente.getEnderecos()));
	}
	
	public void salvarTelefones(Cliente cliente) {
		for(Telefone telefone: cliente.getTelefones()) {
			telefone.setCliente(cliente);
		}
		cliente.setTelefones(telefoneRepository.saveAll(cliente.getTelefones()));
	}
	
	public void salvarEmails(Cliente cliente) {
		for(Email email: cliente.getEmails()) {
			email.setCliente(cliente);
		}
		cliente.setEmails(emailRepository.saveAll(cliente.getEmails()));
	}
	
	public void excluirEnderecos(Cliente cliente) {
		enderecoRepository.deleteAll(cliente.getEnderecos());
	}
	
	public void excluirTelefones(Cliente cliente) {
		telefoneRepository.deleteAll(cliente.getTelefones());
	}
	
	public void excluirEmails(Cliente cliente) {
		emailRepository.deleteAll(cliente.getEmails());
	}
	
	public void excluirEndereco(Long idEndereco) throws NoSuchElementException{
		try{
			enderecoRepository.findById(idEndereco).get();
			enderecoRepository.deleteById(idEndereco);
		}catch (Exception e) {
			throw new NoSuchElementException();
		}
	}
	
	public void excluirTelefone(Long idTelefone) throws NoSuchElementException {
		try{
			telefoneRepository.findById(idTelefone).get();
			telefoneRepository.deleteById(idTelefone);
		}catch (Exception e) {
			throw new NoSuchElementException();
		}
	}
	
	public void excluirEmail(Long idEmail) throws NoSuchElementException {
		try{
			emailRepository.findById(idEmail).get();
			emailRepository.deleteById(idEmail);
		}catch (Exception e) {
			throw new NoSuchElementException();
		}
	}
	
}

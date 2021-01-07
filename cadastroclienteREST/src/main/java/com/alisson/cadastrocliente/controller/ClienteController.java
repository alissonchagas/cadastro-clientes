package com.alisson.cadastrocliente.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alisson.cadastrocliente.model.Cliente;
import com.alisson.cadastrocliente.model.Usuario;
import com.alisson.cadastrocliente.service.ClienteService;

@CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
@RestController
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping("/clientes")
	public List<Cliente> listar(){
		return clienteService.listarTodos();
	}
	
	@GetMapping("/clientes/{id}")
	public ResponseEntity<Cliente> recuperar(@PathVariable Long id) {
		try {
			Cliente cliente = clienteService.recuperar(id);
			return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
		}catch (NoSuchElementException e) {
			return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/clientes")
	public ResponseEntity<Cliente> adicionar(@Valid @RequestBody Cliente cliente) {
		try {
			cliente = clienteService.salvar(cliente);
			return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<Cliente>(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> atualizar(@RequestBody Cliente cliente, @PathVariable Long id) {
		try {
			Cliente clienteExistente = clienteService.recuperar(id);
			clienteService.salvar(cliente); 
			return new ResponseEntity<>(HttpStatus.OK);
		}catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id) {
		try {
			Cliente clienteExistente = clienteService.recuperar(id);
			clienteService.excluir(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/clientes/endereco/{id}")
	public ResponseEntity<?> excluirEndereco(@PathVariable Long id) {
		try {
			clienteService.excluirEndereco(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/clientes/telefone/{id}")
	public ResponseEntity<?> excluirTelefone(@PathVariable Long id) {
		try {
			clienteService.excluirTelefone(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/clientes/email/{id}")
	public ResponseEntity<?> excluirEmail(@PathVariable Long id) {
		try {
			clienteService.excluirEmail(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(path = "/basicauth")
    public Usuario validaAutenticacao() {
        return new Usuario("VOCE ESTÁ AUTENTICADO");
    }
	
}

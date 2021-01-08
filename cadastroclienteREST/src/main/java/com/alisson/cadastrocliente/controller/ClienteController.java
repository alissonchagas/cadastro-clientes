package com.alisson.cadastrocliente.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alisson.cadastrocliente.model.Cliente;
import com.alisson.cadastrocliente.service.CadastroClienteService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
	
	private static final String CADASTRO_VIEW = "CadastroCliente";
	
	@Autowired
	private CadastroClienteService cadastroClienteService;

	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Cliente());
		return mv;
	}
	
	@RequestMapping
	public ModelAndView pesquisar(@ModelAttribute("filtro") Cliente filtro){
		List<Cliente> todosClientes = cadastroClienteService.filtrar(filtro);		
		
		ModelAndView mv = new ModelAndView("PesquisaClientes");
		mv.addObject("clientes",todosClientes);
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Cliente cliente, Errors errors, RedirectAttributes attributes) {
		if(errors.hasErrors()){
			return CADASTRO_VIEW;
		}
		try {
			cadastroClienteService.salvar(cliente);
			attributes.addFlashAttribute("mensagem", "Cliente salvo com sucesso!");
			return "redirect:/clientes/novo";
		}catch (Exception e) {
			return CADASTRO_VIEW;
		}
	}
	
	@RequestMapping(value="{codigo}" ,method= RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes){
		cadastroClienteService.excluir(codigo);
		
		attributes.addFlashAttribute("mensagem", "Cliente excluído com sucesso!");
		return "redirect:/clientes";
	}
}

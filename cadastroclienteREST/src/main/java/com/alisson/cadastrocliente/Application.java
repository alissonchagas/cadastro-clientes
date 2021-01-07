package com.alisson.cadastrocliente;

import java.util.Arrays;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.alisson.cadastrocliente.model.Cliente;
import com.alisson.cadastrocliente.model.Email;
import com.alisson.cadastrocliente.model.Endereco;
import com.alisson.cadastrocliente.model.Telefone;
import com.alisson.cadastrocliente.model.TipoTelefone;
import com.alisson.cadastrocliente.repository.ClienteRepository;
import com.alisson.cadastrocliente.repository.EmailRepository;
import com.alisson.cadastrocliente.repository.EnderecoRepository;
import com.alisson.cadastrocliente.repository.TelefoneRepository;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

@Component
class DemoCommandLineRunner implements CommandLineRunner{

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private TelefoneRepository telefoneRepository;
	
	@Autowired
	private EmailRepository emailRepository;

	@Override
	public void run(String... args) throws Exception {

		Random rand = new Random();
		for (int i = 0; i < 5; i++) {
			Cliente cliente = new Cliente();
			cliente.setNome("CLIENTE "+generateRandomWords(3));
			cliente.setCpf(randomNumero("CPF"));
			cliente = clienteRepository.save(cliente);
			
			Endereco end = new Endereco();
			end.setCep(randomNumero("CEP"));
			end.setLogradouro(generateRandomWords(4));
			end.setBairro(generateRandomWords(1));
			end.setCidade(generateRandomWords(2));
			end.setUf("DF");
			end.setComplemento(generateRandomWords(3));
			end.setIdCliente(cliente.getId());	
			enderecoRepository.save(end);
			Telefone tel = new Telefone();
			tel.setNumero(randomNumero("TEL_C"));
			tel.setTipo(TipoTelefone.CELULAR);
			tel.setIdCliente(cliente.getId());
			telefoneRepository.save(tel);
			Email email = new Email();
			email.setNome("teste"+rand.nextInt(99)+"@teste.com");
			email.setIdCliente(cliente.getId());
			emailRepository.save(email);
			
			for (int j = 0; j < rand.nextInt(3); j++) {
				end = new Endereco();
				end.setCep(randomNumero("CEP"));
				end.setLogradouro(generateRandomWords(4));
				end.setBairro(generateRandomWords(1));
				end.setCidade(generateRandomWords(2));
				end.setUf("DF");
				end.setComplemento(generateRandomWords(3));
				end.setIdCliente(cliente.getId());	
				enderecoRepository.save(end);
			}
			
			for (int j = 0; j < rand.nextInt(3); j++) {
				tel = new Telefone();
				int r = rand.nextInt(9);
				if(r<6){
					tel.setNumero(randomNumero("TEL_F"));
					tel.setTipo(TipoTelefone.COMERCIAL);
				}else {
					tel.setNumero(randomNumero("TEL_F"));
					tel.setTipo(TipoTelefone.RESIDENCIAL);					
				}
				tel.setIdCliente(cliente.getId());
				telefoneRepository.save(tel);
			}
			
			for (int j = 0; j < rand.nextInt(3); j++) {
				email = new Email();
				email.setNome("teste"+rand.nextInt(99)+"@teste.com");
				email.setIdCliente(cliente.getId());
				emailRepository.save(email);
			}			
		}

	}

	public String randomNumero(String tipo) {
		StringBuilder sb = new StringBuilder();
		Random rand = new Random();
		switch (tipo) {
		case "CPF":
			for (int i = 0; i < 11; i++) {
				sb.append(rand.nextInt(9));
			}
			return sb.toString();
		case "CEP":
			for (int i = 0; i < 8; i++) {
				sb.append(rand.nextInt(9));
			}
			return sb.toString();
		case "TEL_C":
			for (int i = 0; i < 9; i++) {
				sb.append(rand.nextInt(9));
			}
			return sb.toString();
		case "TEL_F":
			for (int i = 0; i < 8; i++) {
				sb.append(rand.nextInt(9));
			}
			return sb.toString();
		default:
			return null;
		}
	}
		
	public String generateRandomWords(int numberOfWords){
		String[] randomStrings = new String[numberOfWords];
		Random random = new Random();
		for(int i = 0; i < numberOfWords; i++)
		{
			char[] word = new char[random.nextInt(8)+3]; 
			for(int j = 0; j < word.length; j++)
			{
				word[j] = (char)('a' + random.nextInt(26));
			}
			randomStrings[i] = new String(word);
		}
		return Arrays.toString(randomStrings);
	}
}

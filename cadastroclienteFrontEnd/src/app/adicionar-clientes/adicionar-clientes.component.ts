import { Component, OnInit,ApplicationRef } from '@angular/core';
import { CadastroClienteService } from '../cadastro-cliente.service';
import {FormControl,FormGroup,Validators} from '@angular/forms';
import { Cliente } from '../models/cliente';
import { EnderecoCliente } from '../models/endereco';
import { Telefone } from '../models/telefone';
import { Email } from '../models/email';
import { NgxViacepService, Endereco, ErroCep, ErrorValues } from '@brunoc/ngx-viacep';
import { ActivatedRoute ,Router  } from '@angular/router';

@Component({
  selector: 'app-adicionar-clientes',
  templateUrl: './adicionar-clientes.component.html',
  styleUrls: ['./adicionar-clientes.component.css']
})
export class AdicionarClientesComponent implements OnInit {

  idCliente:0;
  cliente : Cliente= new Cliente;
  atualizarCliente = false;
  exibirCamposEndereco = false;
  exibirCamposTelefone = false;
  exibirCamposEmail = false;
  regexPattern = "[a-zA-Z0-9 ]+";
  
  tiposTelefone = [{id:1,nome:'CELULAR'},{id:2,nome:'COMERCIAL'},{id:3,nome:'RESIDENCIAL'}];
  clienteForm:any; 
  
  constructor(private clienteService: CadastroClienteService, 
    private appRef: ApplicationRef, 
    private viacep: NgxViacepService,
    private router: Router,
    private activatedRoute: ActivatedRoute ) { 
      
    }
  
  ngOnInit(): void { 
    this.idCliente = this.activatedRoute.snapshot.params['id'];  
    this.initializeForm();
    if(this.idCliente>0){
      this.clienteService.recuperar(this.idCliente).toPromise().then(r => {
        this.editarCliente(r)
        return r;
      }).catch(error => {
        return Promise.reject(error);
      });
    }else{
      this.idCliente = 0;
    } 
  }

  initializeForm(){
    this.clienteForm = new FormGroup({
      cliente_id: new FormControl(''),
      cliente_nome: new FormControl('',[Validators.required,Validators.minLength(3),Validators.maxLength(100)]),
      cliente_cpf: new FormControl('',[Validators.required]),
      cliente_enderecos: new FormControl(''),
      cliente_telefones: new FormControl(''),
      cliente_emails: new FormControl(''),
      endereco_id: new FormControl(''),
      endereco_cep: new FormControl('',[Validators.required]),
      endereco_logradouro: new FormControl('',[Validators.required]),
      endereco_bairro: new FormControl('',[Validators.required]),
      endereco_cidade: new FormControl('',[Validators.required]),
      endereco_uf: new FormControl('',[Validators.required]),
      endereco_complemento: new FormControl(''),
      endereco_idCliente: new FormControl(''),
      telefone_id: new FormControl(''),
      telefone_numero: new FormControl(''),
      telefone_tipo: new FormControl(''),
      telefone_idCliente: new FormControl(''),
      email_id: new FormControl(''),
      email_nome: new FormControl('',[Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]),
      email_idCliente: new FormControl('')
    })
  }

  salvarCliente(cliente_){
    this.cliente.id = this.ClienteId.value;
    this.cliente.nome = this.ClienteNome.value;
    this.cliente.cpf = this.ClienteCpf.value;
    this.cliente.enderecos = this.ClienteEnderecos.value;
    this.cliente.telefones = this.ClienteTelefones.value;
    this.cliente.emails = this.ClienteEmails.value;
    if(this.idCliente==0){
      this.salvar();
    }else{
      this.atualizar();
    }
    this.limparEndereco();
    this.limparTelefone();
    this.limparEmail();
    this.idCliente = 0;
    this.router.navigate(['/listar-clientes']);
  }
  
  salvar() {
    this.clienteService.adicionar(this.cliente)
      .subscribe(data => console.log(data), 
                error => console.log(error)
    );
    this.cliente = new Cliente();
  }

  editarCliente(cliente_:Cliente){
    this.clienteForm.patchValue({
      cliente_id:cliente_.id,
      cliente_nome:cliente_.nome,
      cliente_cpf:cliente_.cpf,
      cliente_enderecos:cliente_.enderecos,
      cliente_telefones:cliente_.telefones,
      cliente_emails:cliente_.emails
    });
    this.appRef.tick();
  }

  atualizar() {
    this.clienteService.atualizar(this.cliente.id,this.cliente)
      .subscribe(data => console.log(data), 
                error => console.log(error)
    );
    this.cliente = new Cliente;
  }

  adicionarEndereco(){
    if(!this.EnderecoCep.invalid &&
      !this.EnderecoLogradouro.invalid &&
      !this.EnderecoBairro.invalid &&
      !this.EnderecoCidade.invalid &&
      !this.EnderecoUf.invalid){
        var endereco = {id:null, cep:'', logradouro:null, bairro:null,cidade:'',uf:'',complemento:''}
        endereco.cep = this.EnderecoCep.value;
        endereco.logradouro = this.EnderecoLogradouro.value;
        endereco.bairro = this.EnderecoBairro.value;
        endereco.cidade = this.EnderecoCidade.value;
        endereco.uf = this.EnderecoUf.value;
        endereco.complemento = this.EnderecoComplemento.value;  
        var enderecos:[any] = this.ClienteEnderecos.value==''?[]:this.ClienteEnderecos.value;
        enderecos.push(endereco);       
        this.clienteForm.patchValue({cliente_enderecos:enderecos});
        this.limparEndereco();
        this.exibeCamposEndereco();
    }
  }

  adicionarTelefone(){
    if(!this.TelefoneNumero.invalid&&
      !this.TelefoneTipo.invalid && this.TelefoneTipo.value.id!=0){
        var telefone = {id:null, numero:'', tipo:null}
        telefone.numero = this.TelefoneNumero.value;
        telefone.tipo = this.TelefoneTipo.value;  
        var telefones:[any] = this.ClienteTelefones.value==''?[]:this.ClienteTelefones.value;
        telefones.push(telefone);
        this.clienteForm.patchValue({cliente_telefones:telefones});  
        this.limparTelefone();
        this.exibeCamposTelefone();
      }
  }

  adicionarEmail(){
    if(!this.EmailNome.invalid){
      var email = {id:null, nome:''}
      email.nome = this.EmailNome.value;   
      var emails:[any] = this.ClienteEmails.value==''?[]:this.ClienteEmails.value;
        emails.push(email);
        this.clienteForm.patchValue({cliente_emails:emails});   
      this.limparEmail();
      this.exibeCamposEmail();
    }
  }

  exibeCamposEndereco(){
    this.exibirCamposEndereco = !this.exibirCamposEndereco;
    this.appRef.tick();
  } 

  exibeCamposTelefone(){
    this.exibirCamposTelefone = !this.exibirCamposTelefone;
    this.appRef.tick();
  }
  
  exibeCamposEmail(){
    this.exibirCamposEmail = !this.exibirCamposEmail;
    this.appRef.tick();
  }

  removeEndereco(endereco:EnderecoCliente){
    var enderecos:EnderecoCliente[] = this.removeElement(this.ClienteEnderecos.value,endereco);
    this.clienteForm.patchValue({cliente_enderecos:enderecos});
    if(endereco.id){
      this.clienteService.excluirEndereco(endereco.id)
      .subscribe(data => console.log(data), 
                  error => console.log(error));
    }
    this.appRef.tick();
  }

  removeTelefone(telefone:Telefone){
    var telefones:Telefone[] = this.removeElement(this.ClienteTelefones.value,telefone);
    this.clienteForm.patchValue({cliente_telefones:telefones}); 
    if(telefone.id){
      this.clienteService.excluirTelefone(telefone.id)
      .subscribe(data => console.log(data), 
                  error => console.log(error));
    }
    this.appRef.tick();
  }

  removeEmail(email:Email){
    var emails:Email[] =  this.removeElement(this.ClienteEmails.value,email);
    this.clienteForm.patchValue({cliente_emails:emails});
    if(email.id){
      this.clienteService.excluirEmail(email.id)
      .subscribe(data => console.log(data), 
                  error => console.log(error));
    }
    this.appRef.tick();
  }

  limparEndereco(){
    this.clienteForm.patchValue({endereco_cep:null});
    this.clienteForm.patchValue({endereco_logradouro:null});
    this.clienteForm.patchValue({endereco_bairro:null});
    this.clienteForm.patchValue({endereco_cidade:null});
    this.clienteForm.patchValue({endereco_uf:null});
    this.clienteForm.patchValue({endereco_complemento:null});
  }

  limparTelefone(){
    this.clienteForm.patchValue({telefone_numero:null});
    this.clienteForm.patchValue({telefone_tipo:null});
  }

  limparEmail(){
    this.clienteForm.patchValue({email_nome:null});
  }

  consultaCEP(event: any){  
    
    this.viacep.buscarPorCep(event.target.value).then( ( endereco: Endereco ) => {
        this.clienteForm.patchValue({endereco_logradouro:endereco.logradouro});
        this.clienteForm.patchValue({endereco_bairro:endereco.bairro});
        this.clienteForm.patchValue({endereco_cidade:endereco.localidade});
        this.clienteForm.patchValue({endereco_uf:endereco.uf});
        this.clienteForm.patchValue({endereco_complemento:endereco.complemento});
      }).catch( (error: ErroCep) => {
          console.log(error.message);
      });
  }

  removeElement = function (arrOriginal, elementToRemove){
    return arrOriginal.filter(function(el){return el !== elementToRemove});
  }
  
  get ClienteId(){
    return this.clienteForm.get('cliente_id');
  }

  get ClienteNome(){
    return this.clienteForm.get('cliente_nome');
  }

  get ClienteCpf(){
    return this.clienteForm.get('cliente_cpf');
  }

  get EnderecoCep(){
    return this.clienteForm.get('endereco_cep');
  }

  get EnderecoLogradouro(){
    return this.clienteForm.get('endereco_logradouro');
  }

  get EnderecoBairro(){
    return this.clienteForm.get('endereco_bairro');
  }

  get EnderecoCidade(){
    return this.clienteForm.get('endereco_cidade');
  }

  get EnderecoUf(){
    return this.clienteForm.get('endereco_uf');
  }

  get EnderecoComplemento(){
    return this.clienteForm.get('endereco_complemento');
  }

  get TelefoneNumero(){
    return this.clienteForm.get('telefone_numero');
  }

  get TelefoneTipo(){
    return this.clienteForm.get('telefone_tipo');
  }

  get EmailNome(){
    return this.clienteForm.get('email_nome');
  }

  get ClienteEnderecos(){
    return this.clienteForm.get('cliente_enderecos');
  }

  get ClienteTelefones(){
    return this.clienteForm.get('cliente_telefones');
  }

  get ClienteEmails(){
    return this.clienteForm.get('cliente_emails');
  }
 
}

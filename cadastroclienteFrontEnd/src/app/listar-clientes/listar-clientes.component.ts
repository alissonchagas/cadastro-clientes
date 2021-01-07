import { Component, OnInit, ApplicationRef } from '@angular/core';
import { CadastroClienteService } from '../cadastro-cliente.service';
import { Router  } from '@angular/router';

@Component({
  selector: 'app-listar-clientes',
  templateUrl: './listar-clientes.component.html',
  styleUrls: ['./listar-clientes.component.css']
})
export class ListarClientesComponent implements OnInit {

  clientes: Array<any>; 

  constructor(private clienteService: CadastroClienteService, private appRef: ApplicationRef,private router: Router) { }

  ngOnInit(): void {
    this.listar();
  }

  listar(){
    this.clientes =null;
    this.clienteService.listar().subscribe(dados => this.clientes = dados);
  }

  editar(id){
    this.router.navigate(['/adicionar-clientes/'+id]);
  }


  excluirCliente(id){
    this.clienteService.excluir(id)
      .subscribe((data) =>{console.log(data);
        this.ngOnInit();
      },error => console.log(error)
    );
  }
}

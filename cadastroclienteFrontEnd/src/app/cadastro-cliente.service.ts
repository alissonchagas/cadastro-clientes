import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class CadastroClienteService {

  clientesUrl = 'http://localhost:8080/clientes'

  constructor(private http: HttpClient) { }
  
  listar(): Observable<any> {
    return this.http.get(`${this.clientesUrl}`,{headers:{'Cache-Control': 'no-cache'}})
  }

  recuperar(id:number): Observable<any>{
    return this.http.get(`${this.clientesUrl}/${id}`,{headers:{'Cache-Control': 'no-cache'}})
  }

  adicionar(cliente: object): Observable<any> {
    return this.http.post(`${this.clientesUrl}`,cliente);
  }

  atualizar(id: number, cliente: any): Observable<any>{
    return this.http.put(`${this.clientesUrl}/${id}`,cliente)
  }

  excluir(id: number): Observable<any>{
    return this.http.delete(`${this.clientesUrl}/${id}`, { responseType: 'text' , headers:{'Content-Type':'application/json'} })
  }

  excluirEndereco(id: number): Observable<any>{
    return this.http.delete(`${this.clientesUrl}/endereco/${id}`, { responseType: 'text' , headers:{'Content-Type':'application/json'} })
  }

  excluirTelefone(id: number): Observable<any>{
    return this.http.delete(`${this.clientesUrl}/telefone/${id}`, { responseType: 'text' , headers:{'Content-Type':'application/json'} })
  }

  excluirEmail(id: number): Observable<any>{
    return this.http.delete(`${this.clientesUrl}/email/${id}`, { responseType: 'text' , headers:{'Content-Type':'application/json'} })
  }

}

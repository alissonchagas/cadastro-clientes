import { Email } from "./email";
import { EnderecoCliente } from "./endereco";
import { Telefone } from "./telefone";

export class Cliente {   
    id:number;
    nome:String;
    cpf:String;
    enderecos:EnderecoCliente[] = [];
    telefones:Telefone[] = [];
    emails:Email[] = [];
}

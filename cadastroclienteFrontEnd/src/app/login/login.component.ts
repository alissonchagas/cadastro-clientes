import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from './auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username: string;
  password : string;
  errorMessage = 'Erro na autenticação';
  successMessage: string;
  invalidLogin = false;
  loginSuccess = false;
  
  constructor(
    private router: Router,
    private authenticationService: AuthenticationService
  ) { }

  ngOnInit(): void {
  }

  handleLogin() {
    this.authenticationService.authenticationService(this.username, this.password).subscribe((result)=> {
      this.invalidLogin = false;
      this.loginSuccess = true;
      this.successMessage = 'Logado com successo.';
      this.router.navigate(['/listar-clientes']);
    }, () => {
      this.invalidLogin = true;
      this.loginSuccess = false;
    });      
  }

}

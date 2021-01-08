import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  // BASE_PATH: 'http://localhost:8080'
  USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser'
  PASS_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedPass'
  ROLE_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedRole'

  public username: String;
  public password: String;
  public role: string;

  constructor(private http: HttpClient,private router: Router) {

  }

  authenticationService(username: String, password: String) {
    return this.http.get<any>(`http://localhost:8080/validaAutenticacao/${username}`,
      { headers: { authorization: this.createBasicAuthToken(username, password) } }).pipe(map((user) => {
        this.username = username;
        this.password = password;
        this.registerSuccessfulLogin(username, password,user.permissoes);
      }));
  }

  createBasicAuthToken(username: String, password: String) {
    return 'Basic ' + window.btoa(username + ":" + password)
  }

  registerSuccessfulLogin(username, password, roles) {
    sessionStorage.setItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME, username);
    sessionStorage.setItem(this.PASS_NAME_SESSION_ATTRIBUTE_NAME, password);
    sessionStorage.setItem(this.ROLE_NAME_SESSION_ATTRIBUTE_NAME, roles);
  }

  logout() {
    sessionStorage.removeItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME);
    sessionStorage.removeItem(this.PASS_NAME_SESSION_ATTRIBUTE_NAME);
    sessionStorage.removeItem(this.ROLE_NAME_SESSION_ATTRIBUTE_NAME);
    this.username = null;
    this.password = null;
    this.role = null;
    this.router.navigate(['/login']);
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME)
    if (user === null) return false
    return true
  }

  getLoggedInUserName() {
    let user = sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME)
    if (user === null) return ''
    return user
  }

  getLoggedInUserPass() {
    let pass = sessionStorage.getItem(this.PASS_NAME_SESSION_ATTRIBUTE_NAME)
    if (pass === null) return ''
    return pass
  }

  isLoggedInUserTemPermissao() {
    let role = sessionStorage.getItem(this.ROLE_NAME_SESSION_ATTRIBUTE_NAME).match("ADMIN");
    if (role === null)
      return false;
    else 
      return role.includes("ADMIN");
  }
}

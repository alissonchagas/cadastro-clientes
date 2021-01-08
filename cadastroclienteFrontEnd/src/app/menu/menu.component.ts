import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../login/auth.service';


@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  isLoggedIn = false;
  temPermissao = false;

  constructor(private authenticationService: AuthenticationService) { }

  ngOnInit() {
    this.isLoggedIn = this.authenticationService.isUserLoggedIn();
    this.temPermissao = this.authenticationService.isLoggedInUserTemPermissao();
  }

  handleLogout() {
    this.authenticationService.logout();
  }
}

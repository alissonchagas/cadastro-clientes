import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { AuthenticationService } from './login/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate{

  constructor(
      private router: Router,
      private authenticationService: AuthenticationService
  ) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
      const isUserLoggedIn = this.authenticationService.isUserLoggedIn();
      if (isUserLoggedIn) {
          if (!this.authenticationService.isLoggedInUserTemPermissao()) {
              this.router.navigate(['/listar-clientes']);
              return false;
          }
          return true;
      }

      this.router.navigate(['/login'], { queryParams: { returnUrl: state.url } });
      return false;
  }
}

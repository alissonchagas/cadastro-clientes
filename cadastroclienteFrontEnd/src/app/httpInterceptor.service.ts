import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpHeaders } from '@angular/common/http';
import { Observable,throwError  } from 'rxjs';
import { AuthenticationService } from './login/auth.service';
import { catchError } from 'rxjs/operators';

@Injectable()
export class HttpInterceptorService implements HttpInterceptor{

  constructor(private authenticationService: AuthenticationService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (this.authenticationService.isUserLoggedIn() && req.url.indexOf('basicauth') === -1) {
          const authReq = req.clone({
              headers: new HttpHeaders({
                  'Content-Type': 'application/json',
                  'Authorization': `Basic ${window.btoa(this.authenticationService.getLoggedInUserName() + ":" + this.authenticationService.getLoggedInUserPass())}`
              })
          });
          return next.handle(authReq).pipe(catchError(err => {
                    if ([401, 403].indexOf(err.status) !== -1) {
                        this.authenticationService.logout();
                    }
                    const error = err.error.message || err.statusText;
                    return throwError(error);
                }));
      } else {
          return next.handle(req);
      }
  }
}

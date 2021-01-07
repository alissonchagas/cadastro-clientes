import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS  } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { DataTablesModule } from 'angular-datatables'; 
import { AppComponent } from './app.component';
import { CadastroClienteService } from './cadastro-cliente.service';
import { ListarClientesComponent } from './listar-clientes/listar-clientes.component';
import { AdicionarClientesComponent } from './adicionar-clientes/adicionar-clientes.component';
import { NgxViacepModule } from '@brunoc/ngx-viacep';
import { NgxMaskModule, IConfig } from 'ngx-mask';
import { MenuComponent } from './menu/menu.component';
import { LoginComponent } from './login/login.component';
import { HttpInterceptorService } from './httpInterceptor.service';



@NgModule({
  declarations: [
    AppComponent,
    ListarClientesComponent,
    AdicionarClientesComponent,
    MenuComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    DataTablesModule,
    NgxViacepModule,
    NgxMaskModule.forRoot()
  ],
  providers: [CadastroClienteService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpInterceptorService,
      multi: true
    }],
  
  bootstrap: [AppComponent]
})
export class AppModule { }

export const options: Partial<IConfig> | (() => Partial<IConfig>) = null;

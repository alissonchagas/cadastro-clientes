import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ListarClientesComponent } from './listar-clientes/listar-clientes.component';
import { AdicionarClientesComponent } from './adicionar-clientes/adicionar-clientes.component';
import { LoginComponent } from './login/login.component';
import { AuthGuard } from './auth-guard.service';

const routes: Routes = [
  { path: '', component: LoginComponent ,canActivate: [AuthGuard]},
  { path: 'login', component: LoginComponent},
  { path: 'logout', component: LoginComponent},
  { path: 'listar-clientes', component: ListarClientesComponent },
  { path: 'adicionar-clientes', children:[{path:'',component:AdicionarClientesComponent},{path:':id',component:AdicionarClientesComponent}] ,canActivate: [AuthGuard]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {onSameUrlNavigation: 'reload'})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
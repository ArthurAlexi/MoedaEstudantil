import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { EmpresaComponent } from './empresa/empresa.component';
import { AlunoComponent } from './aluno/aluno.component';
import { ParceirosComponent } from './parceiros/parceiros.component';

const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'criarEmpresa', component: EmpresaComponent },
  {path: 'criarAluno', component: AlunoComponent},
  {path: 'parceiros', component: ParceirosComponent},
  {path: 'alunos', component: AlunoComponent},
  {path: '', redirectTo: 'home', pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

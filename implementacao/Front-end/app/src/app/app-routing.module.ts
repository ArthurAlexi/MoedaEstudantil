import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { EmpresaComponent } from './empresa/empresa.component';
import { AlunoComponent } from './aluno/aluno.component';
import { ParceirosComponent } from './parceiros/parceiros.component';
import { UserAlunoComponent } from './user-aluno/user-aluno.component';
import { ProfessorComponent } from './professor/professor.component';
import { TransacoesComponent } from './transacoes/transacoes.component';
import { VantagensComponent } from './vantagens/vantagens.component';
import { ComprasComponent } from './compras/compras.component';

const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'criarEmpresa', component: EmpresaComponent },
  {path: 'criarAluno', component: AlunoComponent},
  {path: 'parceiros', component: ParceirosComponent},
  {path: 'professor', component: ProfessorComponent},
  {path: 'user-aluno', component: UserAlunoComponent},
  {path: 'transacoes', component: TransacoesComponent},
  {path: 'vantagens', component: VantagensComponent},
  {path: 'compras', component: ComprasComponent},
  {path: '', redirectTo: 'home', pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

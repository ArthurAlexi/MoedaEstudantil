import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AlunoComponent } from './aluno/aluno.component';
import { EmpresaComponent } from './empresa/empresa.component';
import { HomeComponent } from './home/home.component';
import { MatIconModule } from '@angular/material/icon';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { LoginComponent } from './login/login.component';
import { HttpClientModule } from '@angular/common/http';
import { ParceirosComponent } from './parceiros/parceiros.component';
import { UserAlunoComponent } from './user-aluno/user-aluno.component';
import { FormsModule } from '@angular/forms';
import { ProfessorComponent } from './professor/professor.component';
import { TransacoesComponent } from './transacoes/transacoes.component';
import { RouterModule } from '@angular/router';


@NgModule({
  declarations: [
    AppComponent,
    AlunoComponent,
    EmpresaComponent,
    HomeComponent,
    LoginComponent,
    ParceirosComponent,
    ProfessorComponent,
    UserAlunoComponent,
    TransacoesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatIconModule,
    NgbModule,
    HttpClientModule,
    FormsModule,
    RouterModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

import { HttpClient } from '@angular/common/http';
import { Component, ViewChild } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-aluno',
  templateUrl: './aluno.component.html',
  styleUrls: ['./aluno.component.css']
})
export class AlunoComponent {

  constructor(private http: HttpClient, private router: Router) { }

  @ViewChild("nome") nome: any;
  @ViewChild("email") email: any;
  @ViewChild("cpf") cpf: any;
  @ViewChild("instituicao") instituicao: any;
  @ViewChild("senha") senha: any;
  @ViewChild("rg") rg: any;
  @ViewChild("profissao") profissao: any;
  @ViewChild("curso") curso: any;

  cadastrar() {
    const nome = this.nome.nativeElement.value;
    const email = this.email.nativeElement.value;
    const cpf = this.cpf.nativeElement.value;
    const rg = this.rg.nativeElement.value;
    const instituicao = this.instituicao.nativeElement.value;
    const senha = this.senha.nativeElement.value;
    const curso = this.curso.nativeElement.value;

    const url = '';
    let body = {
      "name": nome,
      "password": senha,
      "email": email,
      "cpf": cpf,
      "rg": rg,
      "instituicao": instituicao,
      "curso": curso,
    };

    this.http.post(url, body).subscribe(response => {
      console.log('res', response)
      alert("Cadastro realizado com sucesso")
      this.router.navigate(['/login']);
    }, error => {
      console.log('Erro: ', error);
      alert("Não foi possível realizar o cadastro")
    });


  }
}

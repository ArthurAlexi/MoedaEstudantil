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
  @ViewChild("senha") senha: any;
  @ViewChild("rg") rg: any;
  @ViewChild("endereco") endereco: any;
  @ViewChild("curso") curso: any;

  cadastrar() {
    const nome = this.nome.nativeElement.value;
    const email = this.email.nativeElement.value;
    const cpf = this.cpf.nativeElement.value;
    const rg = this.rg.nativeElement.value;
    const senha = this.senha.nativeElement.value;
    const curso = this.curso.nativeElement.value;
    const endereco = this.endereco.nativeElement.value;
    const url = 'http://localhost:8081/api/v1/aluno/insereAluno';
    let body = {
      "nome" : nome,
      "cpf" : cpf,
      "senha": senha,
      "email": email,
      "endereco" : endereco,
      "rg": rg,
      "curso": {
        "nome" : curso,
        "instituicao" : {
          "nome" : "puc"
        }
      }
    };
    console.log(body)
    this.http.post(url, body).subscribe(response => {
      console.log('res', response)
      this.router.navigate(['/login']);
    }, error => {
      console.log('Erro: ', error);
      this.router.navigate(['/login']);
    });


  }
}

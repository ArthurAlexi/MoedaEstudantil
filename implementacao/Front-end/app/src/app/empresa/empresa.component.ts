import { HttpClient } from '@angular/common/http';
import { Component, ViewChild } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-empresa',
  templateUrl: './empresa.component.html',
  styleUrls: ['./empresa.component.css']
})
export class EmpresaComponent {

  @ViewChild("nome") nome: any;
  @ViewChild("email") email: any;
  @ViewChild("cnpj") cnpj: any;
  @ViewChild("instituicao") instituicao: any;
  @ViewChild("senha") senha: any;

  constructor(private http: HttpClient, private router: Router) { }

  cadastrar() {

    const nome = this.nome.nativeElement.value;
    const email = this.email.nativeElement.value;
    const cnpj = this.cnpj.nativeElement.value;
    const instituicao = this.instituicao.nativeElement.value;
    const senha = this.senha.nativeElement.value;

    const url = 'http://localhost:8081/api/v1/empresa/insereEmpresa';
    let body = {
      "nome": nome,
      "senha": senha,
      "email": email,
      "cnpj": cnpj,
      "instituicao": {
        "nome" : instituicao
      },
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

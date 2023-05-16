import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-user-aluno',
  templateUrl: './user-aluno.component.html',
  styleUrls: ['./user-aluno.component.css']
})
export class UserAlunoComponent implements OnInit{

  constructor(
    private router: Router,
    private http: HttpClient
  ){}

  aluno: any;

  ngOnInit(): void {
    this.aluno = JSON.parse(localStorage.getItem('user') as any);
  }

  modalAberto = false;
  alunoEdit: any = {};

  editarAluno() {
    this.modalAberto = true;
  }

  fecharModal() {
    this.modalAberto = false;
  }

  editar() {
    const url = 'http://localhost:8080/api/v1/aluno/alteraAluno';

    let alunoEditado = {
      'nome': this.alunoEdit.nome,
      'rg': this.alunoEdit.rg,
      'cpf': this.alunoEdit.cpf,
      'endereco': this.alunoEdit.endereco,
      'creditos': this.aluno.creditos,
      'email': this.aluno.email,
      'senha': this.aluno.senha,
      'id': this.aluno.id,
      'curso': {
        'id': this.aluno.curso.id,
        'nome' : this.aluno.curso.nome,
        'instituicao' : {
          'id': this.aluno.curso.instituicao.id,
          'nome' : this.aluno.curso.instituicao.nome
        }
      }
    }
    console.log(alunoEditado)
    this.http.put(url, alunoEditado).subscribe(response => {
      console.log('res', response)
      alert('Aluno alterado com sucesso!');
      this.fecharModal();
    }, error => {
      console.log('Erro: ', error);
      alert('Não foi possível alterar o aluno!');
      this.fecharModal();
    });
  }

  editarEmpresa(){
    const url = 'http://localhost:8080/api/v1/empresa/alteraEmpresa';

    let alunoEditado = {
      'nome': this.alunoEdit.nome,
      'cnpj': this.alunoEdit.cnpj,
      'email': this.aluno.email,
      'senha': this.aluno.senha,
      'id': this.aluno.id,
      'instituicao' : {
        'id': this.aluno.instituicao.id,
          'nome' : this.aluno.instituicao.nome
      }
    }
    console.log(alunoEditado)
    this.http.put(url, alunoEditado).subscribe(response => {
      console.log('res', response)
      alert('Aluno alterado com sucesso!');
      this.fecharModal();
    }, error => {
      console.log('Erro: ', error);
      alert('Não foi possível alterar o aluno!');
      this.fecharModal();
    });

  }

  deletar() {
    const id = this.aluno.id;
    const url = `http://localhost:8081/api/v1/aluno/deletaAlunoPeloId/${id}`;

    this.http.delete(url).subscribe(response => {
      console.log('res', response)
      alert('Aluno deletado com sucesso!');
      this.router.navigate(['/home']);
    }, error => {
      console.log('Erro: ', error);
      this.router.navigate(['/home']);
        });
  }

  deletarEmpresa() {
    const id = this.aluno.id;
    const url = `http://localhost:8081/api/v1/empresa/deletaEmpresaPeloId/${id}`;

    this.http.delete(url).subscribe(response => {
      console.log('res', response)
      alert('Emrpesa deletado com sucesso!');
      this.router.navigate(['/home']);
    }, error => {
      console.log('Erro: ', error);
      alert('Emrpesa deletado com sucesso!');
      this.router.navigate(['/home']);
        });
  }

  logout() {
    localStorage.removeItem('user')
    this.router.navigate(['home'])
  }

}

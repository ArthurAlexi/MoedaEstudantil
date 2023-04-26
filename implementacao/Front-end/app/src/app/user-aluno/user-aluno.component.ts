import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-user-aluno',
  templateUrl: './user-aluno.component.html',
  styleUrls: ['./user-aluno.component.css']
})
export class UserAlunoComponent implements OnInit{

  constructor(
    private route: ActivatedRoute,
    private http: HttpClient
  ){}

  ngOnInit(): void {
    this.exibirAluno();
  }

  modalAberto = false;
  alunoEdit: any = {};
  aluno: any;

  editarAluno() {
    this.modalAberto = true;
  }

  fecharModal() {
    this.modalAberto = false;
  }

  editar() {
    const url = 'http://localhost:8080/api/v1/aluno/alteraAluno';

    let alunoEditado = {
      nome: this.alunoEdit.nome,
      curso: this.alunoEdit.curso,
      rg: this.alunoEdit.rg,
      cpf: this.alunoEdit.cpf,
      endereco: this.alunoEdit.endereco,
      instituicao: {
        nome: this.alunoEdit.instituicao
      }
    }

    this.http.put(url, alunoEditado).subscribe(response => {
      console.log('res', response)
    }, error => {
      console.log('Erro: ', error);
    });
    this.fecharModal();
  }

  exibirAluno() {
    const id = this.route.snapshot.params['id'];
    console.log(id)
    const url = `http://localhost:8080/api/v1/aluno/retornaAlunoPeloId/${id}`;

    this.http.get(url).subscribe(response => {
      console.log('res', response)
      this.aluno = response as any;
    }, error => {
      console.log('Erro: ', error);
    });
  }

  deletar() {
    const id = this.route.snapshot.params['id'];
    const url = `http://localhost:8080/api/v1/aluno/deletaAlunoPeloId/${id}`;

    this.http.delete(url).subscribe(response => {
      console.log('res', response)
      alert('Aluno deletado com sucesso!');
    }, error => {
      console.log('Erro: ', error);
      alert('Não foi possivel deletar o aluno!');
    });
  }

}

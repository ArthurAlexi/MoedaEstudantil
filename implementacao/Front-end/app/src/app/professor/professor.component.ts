import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-professor',
  templateUrl: './professor.component.html',
  styleUrls: ['./professor.component.css']
})
export class ProfessorComponent implements OnInit{
  constructor(
    private http: HttpClient,
    private router: Router
  ){}

  professor: any;
  profInfos : any
  transicoes : any

  ngOnInit() {
    this.exibirAlunos();
    this.professor = JSON.parse(localStorage.getItem('user') as any);
    this.profInfos = JSON.parse(localStorage.getItem('prof') as any);
    this.transicoes = JSON.parse(localStorage.getItem('transicoes') as any);
    console.log(this.transicoes)
  }

  alunos: any[] = [];


  exibirAlunos() {

    const url = 'http://localhost:8081/api/v1/aluno/retornaTodosAlunos';

    this.http.get(url).subscribe(response => {
      console.log('res', response)
      this.alunos = response as any[];
    }, error => {
      console.log('Erro: ', error);
    });
  }

  pontuarAluno(aluno: any, creditos: any) {
    /*
    const url = 'http://localhost:8081/api/v1/aluno/pontuarAluno';
    console.log(this.professor.id)

    const body = {
      "idProfessor": this.professor.id,
      "idAluno": aluno.id,
      "creditos": Number(creditos.value),
    };

    this.http.put(url, body).subscribe(response => {
      console.log('res', response)
      this.alunos = response as any[];
    }, error => {
      console.log('Erro: ', error);
    });
    */
    if(this.profInfos === null || this.transicoes === null)
      return
    const temp = Number(creditos.value)
    if(this.profInfos?.creditos < temp){
      alert('créditos insuficientes')
      return
    }
    this.profInfos.creditos -= temp
    const body = {
      "data" : Date(),
      "idAluno": aluno.id,
      "nomeAluno" : aluno?.nome,
      "nomeProf" : this.profInfos.nome,
      "creditos": temp
    };

    this.transicoes.transicoes.push(body)
    alert("Parece que deu certo")
    console.log(this.transicoes)

    // tacando tudo no local storage

    localStorage.setItem('prof', JSON.stringify(this.profInfos))
    localStorage.setItem('transicoes', JSON.stringify(this.transicoes))

  }



}

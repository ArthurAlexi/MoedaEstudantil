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


  ngOnInit() {
    this.exibirAlunos();
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

    const url = 'http://localhost:8081/api/v1/aluno/pontuarAluno';

    const professor = JSON.parse(localStorage.getItem('user') as any);      
    console.log(professor.id)

    const body = {
      "idProfessor": professor.id,
      "idAluno": aluno.id,
      "creditos": Number(creditos.value),
    };

    this.http.put(url, body).subscribe(response => {
      console.log('res', response)
      this.alunos = response as any[];
    }, error => {
      console.log('Erro: ', error);
    });
  }

}
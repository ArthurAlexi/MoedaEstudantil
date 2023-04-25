import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-all-alunos',
  templateUrl: './all-alunos.component.html',
  styleUrls: ['./all-alunos.component.css']
})
export class AllAlunosComponent implements OnInit{

  constructor(
    private http: HttpClient,
    private router: Router
  ){}


  ngOnInit() {
    this.exibirAlunos();
  }


  alunos: any[] = [];

  exibirAlunos() {

    const url = 'http://localhost:8080/api/v1/aluno/retornaTodosAluno';

    this.http.get(url).subscribe(response => {
      console.log('res', response)
      this.alunos = response as any[];
    }, error => {
      console.log('Erro: ', error);
    });
  }

}

import { DatePipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-professor',
  templateUrl: './professor.component.html',
  styleUrls: ['./professor.component.css']
})
export class ProfessorComponent implements OnInit {
  constructor(
    private http: HttpClient,
    private router: Router,
    public datepipe: DatePipe
  ) { }

  professor: any;
  transicoes: any

  ngOnInit() {
    this.exibirAlunos();
    this.professor = JSON.parse(localStorage.getItem('user') as any);
    console.log(this.professor)
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

    const url = 'http://localhost:8081/api/v1/transacao/realizaTransacao';
    console.log(this.professor.id)

    if (creditos.value <= 0) {
      alert('O valor da transferência deve ser positivo')
      return
    }

    if (this.professor.creditos < creditos.value) {
      alert('Créditos insuficientes!')
      return
    }

    const body = {
      "id_professor": this.professor.id,
      "id_aluno": aluno.id,
      "data": this.datepipe.transform(Date(), 'yyyy-MM-dd'),
      "valor": creditos.value,
    };

    this.professor.creditos -= creditos.value;
    localStorage.setItem('user', JSON.stringify(this.professor));

    this.http.post(url, body).subscribe(response => {
      console.log('res', response)
      alert('transação realiado com sucesso')
    }, error => {
      console.log('Erro: ', error);
    });


  }



}

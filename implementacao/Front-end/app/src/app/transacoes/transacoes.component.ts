import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-transacoes',
  templateUrl: './transacoes.component.html',
  styleUrls: ['./transacoes.component.css']
})
export class TransacoesComponent {

  transacoes: any[] = [];

  constructor(
    private http: HttpClient,
  ){}


  ngOnInit() {
    const data = JSON.parse(localStorage.getItem('transicoes') as any)
    const user = JSON.parse(localStorage.getItem('user') as any)
    const isProfessor = JSON.parse(localStorage.getItem('isProfessor') as any);
    if(!isProfessor)
      this.transacoes = data.transicoes.filter((trans : any)=> trans.idAluno === user.id)
    else
      this.transacoes = data.transicoes
    console.log(data.transicoes)

  }

  exibirTransacoes() {

    const user = JSON.parse(localStorage.getItem('user') as any);


    const url = `http://localhost:8081/api/v1/transacao/retornaTodasTransacoes/${user.id}`;

    this.http.get(url).subscribe(response => {
      console.log('res', response)
      this.transacoes = response as any[];
    }, error => {
      console.log('Erro: ', error);
    });
  }

}

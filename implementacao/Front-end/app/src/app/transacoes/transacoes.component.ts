import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-transacoes',
  templateUrl: './transacoes.component.html',
  styleUrls: ['./transacoes.component.css']
})
export class TransacoesComponent {

  transacoes: any[] = [];
  user : any

  constructor(
    private http: HttpClient,
  ){}


  ngOnInit() {

    this.user = JSON.parse(localStorage.getItem('user') as any)
    console.log(this.user)
    this.exibirTransacoes()

  }

  exibirTransacoes() {

    const user = JSON.parse(localStorage.getItem('user') as any);


    const url = `http://localhost:8081/api/v1/transacao/retornaTodasTransacoes`;

    this.http.get(url).subscribe(response => {
      console.log('res', response)
      this.transacoes = response as any[];
      console.log(this.transacoes)
      if(this.user.email === "charles@gmail.com")
        this.transacoes = this.transacoes.filter(trans => trans.professor.id === this.user.id)
      else
        this.transacoes = this.transacoes.filter(trans => trans.aluno.id === this.user.id)
    }, error => {
      console.log('Erro: ', error);
    });
  }

}

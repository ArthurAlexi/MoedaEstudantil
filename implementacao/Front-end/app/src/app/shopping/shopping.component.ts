import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-shopping',
  templateUrl: './shopping.component.html',
  styleUrls: ['./shopping.component.css']
})
export class ShoppingComponent implements OnInit{

  user: any;
  vantagens: any[] = [];

  constructor(
    private router: Router,
    private http: HttpClient
  ){}

  ngOnInit(){
    this.user = JSON.parse(localStorage.getItem('user') as any);
    this.listarVantagens();
  }

  comprar(vantagem: any){
    const url = `http://localhost:8081/api/v1/compra/criarCompra`;

    let compra = {
      'idEmpresa': vantagem.empresaID,
      'idAluno': this.user.id,
      'valor': vantagem.valor,
    }

    this.http.post(url, compra).subscribe(response => {
      console.log('res', response)
      this.atualizarCreditosAluno();
      alert('Compra realizada com sucesso!');
    }, error => {
      console.log('Erro: ', error);
      alert('Não foi possivel realizar a compra!');
    });
  }

  listarVantagens(){
    const url = `http://localhost:8081/api/v1/vantagem/retornaTodasVantagens`;

    this.http.get(url).subscribe(response => {
      console.log('res', response)
      this.vantagens = response as any[];
    }, error => {
      console.log('Erro: ', error);
    });
  }

  atualizarCreditosAluno() {
    const url = 'http://localhost:8080/api/v1/aluno/alteraAluno';

    let alunoEditado = {
      ...this.user,
      'creditos': this.user.creditos,
    }
    console.log(alunoEditado)
    this.http.put(url, alunoEditado).subscribe(response => {
      console.log('res', response)
    }, error => {
      console.log('Erro: ', error);
    });
  }
}

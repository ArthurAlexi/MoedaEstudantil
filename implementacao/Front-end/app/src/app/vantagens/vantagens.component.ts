import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-vantagens',
  templateUrl: './vantagens.component.html',
  styleUrls: ['./vantagens.component.css']
})
export class VantagensComponent implements OnInit{

  vantagens: any[] = [];
  clickVantagem: boolean = false;
  vantagem: any = {};
  modalAberto: boolean = true;
  empresaID: any;

  constructor(
    private http: HttpClient,
    private router: Router,
  ){}

  ngOnInit(){
    this.empresaID = JSON.parse(localStorage.getItem('user') as any);
  }

  exibirVantagens() {

    const url = `http://localhost:8081/api/v1/vatagem/retornaVantagemPorEmpresa/${this.empresaID}`;

    this.http.get(url).subscribe(response => {
      console.log('res', response)
      this.vantagens = response as any[];
    }, error => {
      console.log('Erro: ', error);
    });
  }

  vantagemClick() {
    this.clickVantagem = true;
  }

  cadastrarVantagem() {
    const url = 'http://localhost:8080/api/v1/vantagem/criarVantagem';

    let vantagemEdit = {
      'idEmpresa': this.empresaID,
      'nome': this.vantagem.nome,
      'descricao': this.vantagem.descricao,
      'valor': this.vantagem.valor,
    }
    console.log(vantagemEdit)

    this.http.post(url, vantagemEdit).subscribe(response => {
      console.log('res', response)
      alert('Vantagem cadastrada com sucesso!');
      this.fecharModal();
    }, error => {
      console.log('Erro: ', error);
      alert('Não foi possível cadastrar a vantagem!');
      this.fecharModal();
    });
  }

  fecharModal() {
    this.modalAberto = false;
  }


}

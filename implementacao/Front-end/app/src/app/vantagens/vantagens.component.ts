import { HttpClient } from '@angular/common/http';
import { Component, OnInit, SimpleChanges } from '@angular/core';
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
  user: any;

  constructor(
    private http: HttpClient,
    private router: Router,
  ){}

  ngOnInit(){
    this.user = JSON.parse(localStorage.getItem('user') as any);
  }

  exibirVantagens() {

    const url = `http://localhost:8081/api/v1/vantagem/retornaVantagemPorEmpresa/${this.user.id}`;

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
      'idEmpresa': this.user.id,
      'nome': this.vantagem.nome,
      'descricao': this.vantagem.descricao,
      'valor': this.vantagem.valor,
      'foto': this.vantagem.foto,
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
    this.clickVantagem = false;
  }


}

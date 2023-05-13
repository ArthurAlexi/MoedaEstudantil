import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-compras',
  templateUrl: './compras.component.html',
  styleUrls: ['./compras.component.css']
})
export class ComprasComponent {

  constructor(
    private http: HttpClient,
  ){}

  compras: any[] =[];

  listarCompras() {
    const user = JSON.parse(localStorage.getItem('user') as any);


    const url = `http://localhost:8081/api/v1/compra/retornaTodasCompras/${user.id}`;

    this.http.get(url).subscribe(response => {
      console.log('res', response)
      this.compras = response as any[];
    }, error => {
      console.log('Erro: ', error);
    });
  }

}

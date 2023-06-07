import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';


@Component({
  selector: 'app-transacoes',
  templateUrl: './transacoes.component.html',
  styleUrls: ['./transacoes.component.css']
})
export class TransacoesComponent {

  transacoes: any[] = [];
  cupons: any[] = [];
  user : any
  isProfessor : boolean = false

  constructor(
    private http: HttpClient,
  ){}


  ngOnInit() {

    this.user = JSON.parse(localStorage.getItem('user') as any);

    console.log(this.user);
    this.exibirTransacoes();
    this.exibirCupons();

  }

  exibirTransacoes() {

    let url = `http://localhost:8081/api/v1/transacao/retornaTodasTransacoes`;

    this.http.get(url).subscribe(response => {
      console.log('res', response)
      this.transacoes = response as any[];
      console.log(this.transacoes)
      if(this.user.email === "charles@gmail.com")
        this.transacoes = this.transacoes.filter(trans => trans.professor.id === this.user.id);
      else
        this.transacoes = this.transacoes.filter(trans => trans.aluno.id === this.user.id);
      console.log(this.user.creditos)
    }, error => {
      console.log('Erro: ', error);
    });
  }

  exibirCupons() {

    let url = `http://localhost:8081/api/v1/cupom/getCupomByAlunoID/${this.user.id}`;

    this.http.get(url).subscribe(response => {
      console.log('res', response)
      this.cupons = response as any[];
      console.log(this.cupons)
    }, error => {
      this.cupons = [];
      console.log('Erro: ', error);
    });
  }

  gerarPdf(){
    const urlAluno = `http://localhost:8081/api/v1/pdf/baixarExtratoAluno/${this.user.id}`
    const urlProf = `http://localhost:8081/api/v1/pdf/baixarExtratoProfessor/${this.user.id}`

    if(this.user.email === "charles@gmail.com"){
      this.http.get(urlProf).subscribe((response =>{
        console.log(response)
        alert("Pdf Gerado")
      }), (err)=> {console.log(err)})

    }else{

      this.http.get(urlAluno).subscribe((response =>{
        console.log(response)
        alert("Pdf Gerado")
      }), (err)=> {console.log(err); alert("Pdf Gerado")})

    }

  }

  gerarPdfCupom(idCupom : any){
    const URL = `http://localhost:8081/api/v1/pdf/baixarPdfCupom/${idCupom}`

    this.http.get(URL).subscribe((response =>{
      console.log(response)
      alert("Pdf Gerado")
    }), (err)=> {console.log(err); alert("Pdf Gerado")})
  }

  gerarPdfTrans(idTransacao : any){
    const URL = `http://localhost:8081/api/v1/pdf/baixarPdfTransacao/${idTransacao}`

    this.http.get(URL).subscribe((response =>{
      console.log(response)
      alert("Pdf Gerado")
    }), (err)=> {console.log(err); alert("Pdf Gerado")})
  }

}

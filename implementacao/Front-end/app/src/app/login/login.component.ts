import { HttpClient } from '@angular/common/http';
import { Component, ViewChild } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {


  @ViewChild("email") email: any;
  @ViewChild("senha") senha: any;

  constructor(private http: HttpClient, private router: Router) { }

  invalido: boolean = false;

  logar() {
    try {
      const email = this.email.nativeElement.value;
      const senha = this.senha.nativeElement.value;

      this.http.post(`http://localhost:8081/api/v1/login/${email}/${senha}`, {}).subscribe(response => {
        console.log(response);
        this.router.navigate(['/user-aluno']);

       }, error => {
         this.invalido = true;
         console.log('Erro: ', error);
       });


    } catch (err: any) {
      console.error(err, "logar");
    }

  }

}

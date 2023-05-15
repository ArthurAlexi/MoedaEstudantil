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
  isChecked = false;

  logar() {
    const url = 'http://localhost:8081/api/v1/login'
    const email = this.email.nativeElement.value;
    const senha = this.senha.nativeElement.value;
    try {
      if(this.isChecked){
        this.http.post(`${url}/${email}/${senha}`, {email, senha}).subscribe(response => {
        console.log(response);
        const user = response as any
        localStorage.setItem('user', JSON.stringify(user));
        this.router.navigate(['/professor']);
       }, error => {
         this.invalido = true;
         console.log('Erro: ', error);
       });

      }else{
      localStorage.setItem('isProfessor', JSON.stringify(this.isChecked))
      this.http.post(`http://localhost:8081/api/v1/login/${email}/${senha}`, {email, senha}).subscribe(response => {
        console.log(response);
        const user = response as any
        localStorage.setItem('user', JSON.stringify(user));
        this.router.navigate(['/user-aluno']);

       }, error => {
         this.invalido = true;
         console.log('Erro: ', error);
       });

      }
    } catch (err: any) {
      console.error(err, "logar");
    }

  }

}

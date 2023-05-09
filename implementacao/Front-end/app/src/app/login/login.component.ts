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
  @ViewChild("souProfessor") souProfessor: any;

  constructor(private http: HttpClient, private router: Router) { }

  invalido: boolean = false;

  logar() {
    try {
      const email = this.email.nativeElement.value;
      const senha = this.senha.nativeElement.value;
      const souProfessor = this.souProfessor.nativeElement.value;
      if(souProfessor)
        console.log('souProfessor ', souProfessor)
      this.http.post(`http://localhost:8081/api/v1/login/${email}/${senha}`, {email, senha}).subscribe(response => {
        console.log(response);
        const user = response as any
        localStorage.setItem('user', JSON.stringify(user));
        if(localStorage.getItem('creditosAluno') === null)
          localStorage.setItem('creditosAluno', user?.creditos);
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

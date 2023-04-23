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

      this.http.get(`http://localhost:8080/api/usuario/retornaUsuarioPeloEmail/${email}`).subscribe(response => {
        const user = response as any
        if(user?.password === senha ){
          localStorage.setItem('user', JSON.stringify(user));
          this.router.navigate(['/user'])
        }
       }, error => {
         this.invalido = true;
         console.log('Erro: ', error);
       });


    } catch (err: any) {
      console.error(err, "logar");
    }

  }

}

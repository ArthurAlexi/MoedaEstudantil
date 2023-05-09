import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})

export class HomeComponent {

  ngOnInit() {

    if(localStorage.getItem('prof') !== null){
        localStorage.removeItem('prof')
    }

    const profBody = {
      'name' : 'Prof',
      'email' : 'prof@prof',
      'senha' : '123',
      'creditos' : 1000.0
    }

    const transicoes = {
      'transicoes' :[]
    }

    localStorage.setItem('prof', JSON.stringify(profBody))
    if(localStorage.getItem('transicoes') === null)
      localStorage.setItem('transicoes', JSON.stringify(transicoes))

  }

}

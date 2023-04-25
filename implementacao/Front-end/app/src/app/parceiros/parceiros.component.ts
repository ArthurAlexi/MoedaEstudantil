import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-parceiros',
  templateUrl: './parceiros.component.html',
  styleUrls: ['./parceiros.component.css']
})
export class ParceirosComponent {
  parceiros: any
  URL = 'http://localhost:8081/api/v1/empresa/retornaTodasEmpresas'

  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit(): void {

    this.http.get(this.URL).subscribe((response) => {
      this.parceiros = response as any
      console.log(this.parceiros)
    })

  }

}

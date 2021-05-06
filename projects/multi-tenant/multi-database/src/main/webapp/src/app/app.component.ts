import { Component } from '@angular/core';
import { HttpModule, Http } from '@angular/http';
import { Observable } from "rxjs/Observable";

import 'rxjs/add/operator/map';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  greetings: string;
  title = 'app works!';
  results: Observable<string>;
  
  constructor(private http: Http) {
    this.results = this.http.get('http://localhost:8080/api/greetings')
    .map(res => res.json());
  }
  
  ngOnInit() {
    this.results.subscribe(data => {
      console.log('>>', data);
    });
  }
}

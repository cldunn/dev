import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { ApiService } from "../../shared/service/api.service";

import { AppService } from '../../app.service';

@Component({
  templateUrl: './home.component.html'
})
export class HomeComponent {

  title = 'Demo';
  greeting = {id: '', content: ''};

  constructor(private appService: AppService, private apiService: ApiService) {
      // apiService.uiGet('home/resource').subscribe(data => this.greeting = data);
      apiService.get('home/resource').subscribe(data => this.greeting = data);
  }

  authenticated() { return this.appService.authenticated; }

}
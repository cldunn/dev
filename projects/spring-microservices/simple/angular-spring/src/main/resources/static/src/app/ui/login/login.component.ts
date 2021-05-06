import { Component } from '@angular/core';
import { Router } from '@angular/router';


import { ApiService } from "../../shared/service/api.service";

import { AppService } from '../../app.service';
import { Credentials } from './login.data';

@Component({
  templateUrl: './login.component.html'
})
export class LoginComponent {
  
  credentials = new Credentials();
  
  constructor(private appService: AppService, private apiService: ApiService, private router: Router) { 
      
  }

  login() {
      this.appService.authenticate(this.credentials, () => {
          this.router.navigateByUrl('/');
      });
      
      return false;
  }

  get loginError() {
      return this.appService.loginError;
  }
}

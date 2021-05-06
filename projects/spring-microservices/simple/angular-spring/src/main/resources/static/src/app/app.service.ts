import { Injectable } from '@angular/core';
import { HttpParams, HttpHeaders } from '@angular/common/http';

import { ApiService } from "./shared/service/api.service";

@Injectable({
  providedIn: 'root'
})
export class AppService {

  loginError = false;
  authenticated = false;

  constructor(private apiService: ApiService) {
  }

  authenticate(credentials, callback) {
        
          
        const headers = credentials ? { authorization : 'Basic ' + btoa(credentials.username + ':' + credentials.password) } : {};
        
        this.apiService.uiGet('login/user', {}, headers).subscribe(response => {
            this.loginError = false;
            if (response['name']) {
                this.authenticated = true;
            } else {
                this.authenticated = false;
            }
            return callback && callback();
        }, error => {
            this.loginError = true;
        });

    }

}
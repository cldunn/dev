import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { finalize } from 'rxjs/operators';

import { ApiService } from "./shared/service/api.service";

import { AppService } from './app.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  constructor(private appService: AppService, private apiService: ApiService, private router: Router) {
      this.appService.authenticate(undefined, undefined);
  }
  
  logout() {
      this.apiService.post('logout', {}).pipe(finalize(() => {
          this.appService.authenticated = false;
          this.router.navigateByUrl('/login');
      })).subscribe();
  }

  /*
  constructor(
      private appConfigService: AppConfigService,
      private apiService: ApiService) {
      
      console.log('enableDebugTools: ', appConfigService.enableDebugTools);
      console.log('production: ', appConfigService.production);
      console.log('logLevel: ', appConfigService.logLevel);
      console.log('hostUrl: ', appConfigService.hostUrl);
      
      this.apiService.uiGet("/home/resource", new HttpParams()).subscribe((resp) => {
          this.greeting = resp;
      });
  }
  
  ngOnInit() {
      const params = new HttpParams()
          .set('name', 'Cliff');

      this.apiService.uiPost("/login/pageConfig").subscribe((resp) => {
          console.log('result: ', resp);
      });
  
      const params = new HttpParams().set('firstName', 'Cliff');
      this.apiService.uiGet("/login/pageConfig", params).subscribe((resp) => {
          console.log('resp: ', resp);
      },(error) => {
          console.log('error', error);
      });
  }
  */  
}

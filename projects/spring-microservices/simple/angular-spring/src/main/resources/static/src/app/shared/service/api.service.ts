import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';

import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { AppConfigService } from "../../app-config.service";

const UI_BASE: string = "/rest/ui/";
const API_BASE: string = "/rest/api/";

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(
      private http: HttpClient, 
      private appConfig: AppConfigService) { 
      
  }
  
  private handleError(error: any) {
      return throwError(error);
  }
  
  get(url: string, params: any = {}, headers: any = {}): Observable<any> {
      let httpParams: HttpParams  = new HttpParams();
      for (let [key, value] of Object.entries(params)) {
          httpParams.set(key, value as string);
      }
      
      const httpOptions = {
          params: httpParams,
          headers: new HttpHeaders(headers)
      };
      
      return this.http.get(url, httpOptions)
          .pipe(catchError(this.handleError));
  }

  post(url: string, body: any, httpOptions: any = {withCredentials: true}): Observable<any> {
      return this.http.post(url, body, httpOptions)
          .pipe(catchError(this.handleError));
  }
  
  uiGet(url: string, params: any = {}, headers: any = {}): Observable<any> {
      let targetUrl: string = this.appConfig.hostUrl + UI_BASE + url;
      
      let httpParams: HttpParams  = new HttpParams();
      for (let [key, value] of Object.entries(params)) {
          httpParams.set(key, value as string);
      }
      
      const httpOptions = {
          params: httpParams,
          headers: new HttpHeaders(headers)
      };
      
      return this.http.get(targetUrl, httpOptions)
          .pipe(catchError(this.handleError));
  }

  uiPost(url: string, body: any, headers: any = {}): Observable<any> {
      let targetUrl: string = this.appConfig.hostUrl + UI_BASE + url;  
      
      const httpOptions = {
          headers: new HttpHeaders(headers)
      };
  
      return this.http.post(targetUrl, body, httpOptions)
          .pipe(catchError(this.handleError));
  }
  
  apiGet(url: string, params: any = {}, headers: any = {}): Observable<any> {
      let targetUrl: string = this.appConfig.hostUrl + API_BASE + url;  
  
      let httpParams: HttpParams  = new HttpParams();
      for (let [key, value] of Object.entries(params)) {
          httpParams.set(key, value as string);
      }
      
      const httpOptions = {
          params: httpParams,
          withCredentials: true,
          headers: new HttpHeaders(headers)
      };
          
      return this.http.get(targetUrl, httpOptions)
          .pipe(catchError(this.handleError));
  }

  apiPost(url: string, body: any, httpOptions: any = {withCredentials: true}): Observable<any> {
      let targetUrl: string = this.appConfig.hostUrl + API_BASE + url;  
      
      return this.http.post(targetUrl, body, httpOptions)
          .pipe(catchError(this.handleError));
  }

}

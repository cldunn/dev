import { Injectable } from '@angular/core';

import { environment } from '../environments/environment';
import { EnvService } from "src/app/shared/service/env.service";

@Injectable({
  providedIn: 'root'
})
export class AppConfigService {

  constructor(private env: EnvService) { }
  
  get enableDebugTools() {
      return this.env.enableDebugTools || environment.enableDebugTools;
  }

  get production() {
      return environment.production;
  }

  get logLevel() {
      return this.env.logLevel || environment.logLevel;
  }

  get hostUrl() {
      const url = location.href;
      const baseUrl = url.substring(0, url.indexOf('/', 14));
      return baseUrl + "/angularSpring";
  }
}

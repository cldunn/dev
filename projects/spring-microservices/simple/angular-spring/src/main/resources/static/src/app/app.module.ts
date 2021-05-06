import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AppService } from './app.service';

import { EnvServiceProvider } from './shared/service/env.service.provider';
import { HttpInterceptorService } from "./shared/service/http-interceptor.service";
import { ServerErrorComponent } from './shared/component/server-error/server-error.component';

import { HomeModule } from './ui/home/home.module';
import { LoginModule } from './ui/login/login.module';

@NgModule({
  declarations: [
    AppComponent,
    ServerErrorComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule,
    HomeModule,
    LoginModule
  ],
  providers: [
    AppService,
    EnvServiceProvider,
    { provide: HTTP_INTERCEPTORS, useClass: HttpInterceptorService, multi: true}
  ],
  bootstrap: [AppComponent],
  entryComponents: [ServerErrorComponent]
})
export class AppModule { }

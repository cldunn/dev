import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { HomeModule } from './ui/home/home.module';
import { HomeComponent } from "./ui/home/home.component";
import { LoginModule } from './ui/login/login.module';
import { LoginComponent } from "./ui/login/login.component";

const routes: Routes = [
    { path: '', pathMatch: 'full', redirectTo: 'home'},
    { path: 'home', component: HomeComponent},
    { path: 'login', component: LoginComponent}
];


@NgModule({
  imports: [
    RouterModule.forRoot(routes),
    HomeModule,
    LoginModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }

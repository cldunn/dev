import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeComponent } from './home.component';

export const COMPONENTS = [
  HomeComponent
]

@NgModule({
  declarations: [
    ...COMPONENTS
  ],
  imports: [
    CommonModule
  ],
  exports: [HomeComponent],
  entryComponents: [...COMPONENTS]
})
export class HomeModule { }

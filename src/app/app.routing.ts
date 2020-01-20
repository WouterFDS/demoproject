import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {LoginComponent} from './login/login.component';
import { RouterModule, Routes } from '@angular/router';
import {OpdrachtLijstComponent} from './opdracht/opdracht.lijst/opdracht.lijst.component';



const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: '', component: LoginComponent },
  { path: 'lijst', component: OpdrachtLijstComponent },


];

export const routing = RouterModule.forRoot(routes);

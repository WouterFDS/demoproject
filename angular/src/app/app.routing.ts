import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {LoginComponent} from './login/login.component';
import { RouterModule, Routes } from '@angular/router';
import {OpdrachtLijstComponent} from './opdracht/opdracht.lijst/opdracht.lijst.component';
import {OpdrachtCreateComponent} from './opdracht/opdracht.create/opdracht.create.component';
import {OpdrachtLijstDetailsComponent} from './opdracht/opdracht.lijst-details/opdracht.lijst-details.component';



const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: '', component: LoginComponent },
  { path: 'lijst', component: OpdrachtLijstComponent },
  { path: 'maak', component: OpdrachtCreateComponent },
  { path: 'lijstD', component: OpdrachtLijstDetailsComponent },

];

export const routing = RouterModule.forRoot(routes);

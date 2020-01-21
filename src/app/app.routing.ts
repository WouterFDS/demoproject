import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {LoginComponent} from './login/login.component';
import { RouterModule, Routes } from '@angular/router';
import {OpdrachtLijstComponent} from './opdracht/opdracht.lijst/opdracht.lijst.component';
import {OpdrachtEditComponent} from './opdracht/opdracht.edit/opdracht.edit.component';
import {OpdrachtCreateComponent} from './opdracht/opdracht.create/opdracht.create.component';
import {OpdrachtDeleteComponent} from './opdracht/opdracht.delete/opdracht.delete.component';



const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: '', component: LoginComponent },
  { path: 'lijst', component: OpdrachtLijstComponent },
  { path: 'wijzig', component: OpdrachtEditComponent },
  { path: 'maak', component: OpdrachtCreateComponent },
  { path: 'verwijder', component: OpdrachtDeleteComponent },


];

export const routing = RouterModule.forRoot(routes);

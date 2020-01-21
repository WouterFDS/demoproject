import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import {routing} from './app.routing';
import {ReactiveFormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { OpdrachtLijstComponent } from './opdracht/opdracht.lijst/opdracht.lijst.component';
import { OpdrachtEditComponent } from './opdracht/opdracht.edit/opdracht.edit.component';
import { OpdrachtCreateComponent } from './opdracht/opdracht.create/opdracht.create.component';
import { OpdrachtDeleteComponent } from './opdracht/opdracht.delete/opdracht.delete.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    OpdrachtLijstComponent,
    OpdrachtEditComponent,
    OpdrachtCreateComponent,
    OpdrachtDeleteComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    routing,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

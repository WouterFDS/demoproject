import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import {routing} from './app.routing';
import {ReactiveFormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { OpdrachtLijstComponent } from './opdracht/opdracht.lijst/opdracht.lijst.component';
import { OpdrachtCreateComponent } from './opdracht/opdracht.create/opdracht.create.component';
import {ApiService} from './service/api.service';
import {TokenInterceptor} from './core/interceptor';
import { HeaderComponent } from './header/header.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    OpdrachtLijstComponent,
    OpdrachtCreateComponent,
    HeaderComponent,

  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    routing,
    ReactiveFormsModule
  ],
  providers: [ApiService, {provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi : true}],
  bootstrap: [
    AppComponent,
  ]
})
export class AppModule { }

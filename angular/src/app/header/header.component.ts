import { Component, OnInit } from '@angular/core';
import {AppComponent} from '../app.component';
import {NavigationEnd, Router, RouterEvent} from '@angular/router';
import {filter} from 'rxjs/operators';
import {logger} from 'codelyzer/util/logger';



@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  ingelogd: boolean;

  constructor(private router: Router) {
    this.checkLogIn();
    router.events.pipe(
      filter(e => e instanceof NavigationEnd
      )
    ).subscribe(e => {
      this.checkLogIn();
    });

  }

  ngOnInit() {

  }

  checkLogIn(){
    if(window.localStorage.getItem('token')){
      this.ingelogd=true;
    }
    else{
      this.ingelogd=false;
    }

  }

  logOut(){
    window.localStorage.removeItem('token');
    this.router.navigate(['login'])
  }



}

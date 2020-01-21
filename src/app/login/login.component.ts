import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {ApiService} from '../service/api.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginFormulier: FormGroup;
  fouteGegevens = false;


  constructor(private formBuilder: FormBuilder, private router: Router, private apiService: ApiService) { }

  onSubmit() {

    if (this.loginFormulier.invalid) {
      return;
    }
    const loginDetails = {
      naam: this.loginFormulier.controls.naam.value,
      wachtwoord: this.loginFormulier.controls.wachtwoord.value
    };
    this.apiService.login(loginDetails).subscribe(info => {
        if (info.status === 200) {
          if(!info.resultaat){
            this.fouteGegevens = true;
          }
          else{
            this.router.navigate(['lijst'])
          }
        } else {
          this.fouteGegevens = true;
        }
    });

  }

  ngOnInit() {
    this.loginFormulier = this.formBuilder.group({
      naam: ['', Validators.compose([Validators.required])],
      wachtwoord: ['', Validators.required]
    });
  }


}

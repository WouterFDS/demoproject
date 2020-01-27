import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {ApiService} from '../../service/api.service';
import {Gebruiker} from '../../domain/gebruiker.domain';

@Component({
  selector: 'app-opdracht.create',
  templateUrl: './opdracht.create.component.html',
  styleUrls: ['./opdracht.create.component.css']
})
export class OpdrachtCreateComponent implements OnInit {

  opdrachtId : number;
  opdrachtFormulier: FormGroup;

  constructor(private formBuilder: FormBuilder, private router: Router, private apiService: ApiService) {
    const state = this.router.getCurrentNavigation().extras.state;
    this.opdrachtId=0;
    var opdrachtNaam ='';
    var opdrachtBelang='';
    if(state!=null){
      const opdracht = state.opdracht;
      this.opdrachtId= opdracht.id;
      opdrachtNaam = opdracht.naam;
      opdrachtBelang = opdracht.belangrijkheid;
    }
    this.opdrachtFormulier = this.formBuilder.group({
      naam: [opdrachtNaam, [Validators.required]],
      belangrijkheid: [opdrachtBelang, [Validators.required,Validators.min(1),Validators.max(10)]]
    });
  }

  ngOnInit() {
    if (!window.localStorage.getItem('token')) {
      this.router.navigate(['login']);
      return;
    }
    if (window.atob(window.localStorage.getItem('isAdmin')).split(window.localStorage.getItem('token'))[1]!='true') {
      this.router.navigate(['lijst']);
      return;
    }

  }

  onSubmit(){
    if (this.opdrachtFormulier.invalid) {
      return;
    }
    const opdrachtDetails = {
      id: this.opdrachtId,
      naam: this.opdrachtFormulier.controls.naam.value,
      belangrijkheid: this.opdrachtFormulier.controls.belangrijkheid.value
    };
    this.apiService.maakOpdracht(opdrachtDetails).subscribe(info => {
      if (info.status === 200) {
        if(!info.resultaat){
          alert("opdracht kon niet aangemaakt/gewijzigt worden");
        }
        else{
          this.router.navigate(['lijst'])
        }
      } else {
      }
    });
  }

}

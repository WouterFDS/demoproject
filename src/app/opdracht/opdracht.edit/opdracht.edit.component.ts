import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {Opdracht} from '../../domain/opdracht.domain';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ApiService} from '../../service/api.service';


@Component({
  selector: 'app-opdracht.edit',
  templateUrl: './opdracht.edit.component.html',
  styleUrls: ['./opdracht.edit.component.css']
})
export class OpdrachtEditComponent implements OnInit {

  opdracht : Opdracht;
  opdrachtFormulier: FormGroup;

  constructor(private formBuilder: FormBuilder, private router: Router, private apiService: ApiService) {
    const state = this.router.getCurrentNavigation().extras.state;
    this.opdracht = state.opdracht;
    this.opdrachtFormulier = this.formBuilder.group({
      naam: [this.opdracht.naam, [Validators.required]],
      belangrijkheid: [this.opdracht.belangrijkheid, Validators.required]
    });

  }

  ngOnInit() {


  }

  onSubmit() {
    if (this.opdrachtFormulier.invalid) {
      return;
    }
    const opdrachtDetails = {
      id: this.opdracht.id,
      naam: this.opdrachtFormulier.controls.naam.value,
      belangrijkheid: this.opdrachtFormulier.controls.belangrijkheid.value
    };
    this.apiService.maakOpdracht(opdrachtDetails).subscribe(info => {
      if (info.status === 200) {
        if(!info.resultaat){
          alert("opdracht kon niet worden aangepast");
        }
        else{
          this.router.navigate(['lijst'])
        }
      } else {
      }
    });
  }
}

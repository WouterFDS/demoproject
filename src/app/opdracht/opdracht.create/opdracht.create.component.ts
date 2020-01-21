import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {ApiService} from '../../service/api.service';

@Component({
  selector: 'app-opdracht.create',
  templateUrl: './opdracht.create.component.html',
  styleUrls: ['./opdracht.create.component.css']
})
export class OpdrachtCreateComponent implements OnInit {

  opdrachtFormulier: FormGroup;

  constructor(private formBuilder: FormBuilder, private router: Router, private apiService: ApiService) { }

  ngOnInit() {
    this.opdrachtFormulier = this.formBuilder.group({
      naam: ['', [Validators.required]],
      belangrijkheid: ['', Validators.required]
    });
  }

  onSubmit(){
    if (this.opdrachtFormulier.invalid) {
      return;
    }
    const opdrachtDetails = {
      naam: this.opdrachtFormulier.controls.naam.value,
      belangrijkheid: this.opdrachtFormulier.controls.belangrijkheid.value
    };
    this.apiService.maakOpdracht(opdrachtDetails).subscribe(info => {
      if (info.status === 200) {
        if(!info.resultaat){
          alert("opdracht kon niet aangemaakt worden");
        }
        else{
          this.router.navigate(['lijst'])
        }
      } else {
      }
    });
  }

}

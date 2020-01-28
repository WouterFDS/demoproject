import {Component, OnInit} from '@angular/core';
import {Opdracht} from '../../domain/opdracht.domain';
import {Router} from '@angular/router';
import {ApiService} from '../../service/api.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-opdrachtlijst-details',
  templateUrl: './opdracht.lijst-details.component.html',
  styleUrls: ['./opdracht.lijst-details.component.css']
})
export class OpdrachtLijstDetailsComponent implements OnInit {
  opdrachten: Opdracht[];
  currentOpdr: Opdracht;
  opdrachtFormulier: FormGroup;
  bewerkbaar: boolean;

  constructor(private formBuilder: FormBuilder,private router: Router, private apiService: ApiService) { }

  ngOnInit() {
    if (!window.localStorage.getItem('token')) {
      this.router.navigate(['login']);
      return;
    }

    this.apiService.getOpdrachten().subscribe(info => {

      if (info.status === 200) {
        this.opdrachten = info.resultaat;
      } else {

      }

      this.currentOpdr =this.opdrachten[1];

      this.opdrachtFormulier = this.formBuilder.group({
        naam: [this.currentOpdr.naam, [Validators.required]],
        belangrijkheid: [this.currentOpdr.belangrijkheid, [Validators.required,Validators.min(1),Validators.max(10)]]
      });

    });
    this.bewerkbaar=false;
  }

  details(opdracht: Opdracht) {
    this.currentOpdr = opdracht;
    this.opdrachtFormulier.controls.naam.setValue(this.currentOpdr.naam);
    this.opdrachtFormulier.controls.belangrijkheid.setValue(this.currentOpdr.belangrijkheid);
    this.bewerkbaar=false;
  }

  onSubmit() {
    if (this.opdrachtFormulier.invalid) {
      return;
    }
    const opdrachtDetails = {
      id: this.currentOpdr.id,
      naam: this.opdrachtFormulier.controls.naam.value,
      belangrijkheid: this.opdrachtFormulier.controls.belangrijkheid.value
    };
    this.apiService.maakOpdracht(opdrachtDetails).subscribe(info => {
      if (info.status === 200) {
        if(!info.resultaat){
          alert("opdracht kon niet gewijzigt worden");
        }
        else{
          this.apiService.getOpdrachten().subscribe(info => {

            if (info.status === 200) {
              this.opdrachten = info.resultaat;
              this.bewerk();
            } else {

            }
          });
        }
      } else {
      }
    });
  }

  bewerk() {
    this.bewerkbaar=!this.bewerkbaar;
  }
  getAllowed() {
    return window.atob(window.localStorage.getItem('isAdmin')).split(window.localStorage.getItem('token'))[1]==='true';
  }
}

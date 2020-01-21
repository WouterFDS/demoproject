import { Component, OnInit } from '@angular/core';
import {FormBuilder} from '@angular/forms';
import {Router} from '@angular/router';
import {ApiService} from '../../service/api.service';
import {Opdracht} from '../../domain/opdracht.domain';

@Component({
  selector: 'app-opdracht.lijst',
  templateUrl: './opdracht.lijst.component.html',
  styleUrls: ['./opdracht.lijst.component.css']
})
export class OpdrachtLijstComponent implements OnInit {

  opdrachten: Opdracht[];

  constructor(private router: Router, private apiService: ApiService) { }

  ngOnInit() {
    this.apiService.getOpdrachten().subscribe(info => {
      if (info.status === 200) {
        this.opdrachten = info.resultaat;
      } else {

      }
    });
  }

  maakOpdracht() {
    this.router.navigate(['maak'])
  }

  verwijder(opdracht: Opdracht) {
    this.apiService.verwijder(opdracht).subscribe(info => {
      if (info.status === 200) {
        this.opdrachten = info.resultaat;
      } else {

      }
    });
  }
}

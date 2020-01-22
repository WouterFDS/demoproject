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
  sortMethod: string;
  richting :number;

  constructor(private router: Router, private apiService: ApiService) { }

  ngOnInit() {
    this.richting=1;
    this.sortMethod='id';
    this.apiService.getOpdrachten().subscribe(info => {

      if (info.status === 200) {
        this.opdrachten = info.resultaat;
        this.updateList();
      } else {

      }
    });

  }

  maakOpdracht() {
    this.router.navigate(['maak']);
  }

  verwijder(opdracht: Opdracht) {
    this.apiService.verwijder(opdracht).subscribe(info => {
      if (info.status === 200) {
        this.opdrachten = info.resultaat;
        this.updateList();
      } else {

      }
    });

  }

  wijzig(opdracht: Opdracht) {
    this.router.navigate(['maak'],{state:{opdracht: opdracht}});
  }

  private sortOn(a:Opdracht , b:Opdracht) {
    var value = -1*this.richting;
    if(a[this.sortMethod]>b[this.sortMethod]){
      value=-1*value;
    }
    return value;
  }

  veranderSortMethod(filter:string){
    if(filter===this.sortMethod){
        this.richting=-1*this.richting;
    }
    else{
      this.richting=1;
      this.sortMethod=filter;
    }
  }

  updateList(){
    this.opdrachten.sort(( a, b) =>(this.sortOn(a,b)));
  }

}

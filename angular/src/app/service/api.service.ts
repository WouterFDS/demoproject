
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from 'rxjs';
import {ApiResponse} from '../domain/api.response';

@Injectable({
  providedIn: 'root',
})
export class ApiService {

  constructor(private http: HttpClient) { }

  login(loginDetails): Observable<ApiResponse> {
    return this.http.post<ApiResponse>('http://localhost:8080/authenticator/', loginDetails);
  }

  getOpdrachten(): Observable<ApiResponse> {
    return this.http.post<ApiResponse>('http://localhost:8080/opdracht/lijst',"");
  }

  maakOpdracht(opdrachtDetails):  Observable<ApiResponse> {
    return this.http.post<ApiResponse>('http://localhost:8080/opdracht/maak',opdrachtDetails);
  }

  verwijder(opdracht): Observable<ApiResponse> {
    return this.http.post<ApiResponse>('http://localhost:8080/opdracht/verwijder',opdracht);
  }

}


import {Injectable} from '@angular/core';
import {HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<any> {
    let token: string;
    token = window.localStorage.getItem('token');
    if (!token) {
    } else {

      request = request.clone({
        setHeaders: {
          Authorization: 'Bearer ' + token
        }
      });
    }
    return next.handle(request);
  }
}

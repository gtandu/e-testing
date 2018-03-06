import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import { HttpHeaders, HttpParams, HttpErrorResponse } from '@angular/common/http';
import { AuthentificationService } from '../authentification.service';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';


@Injectable()
export class QcmService {

  headers = new Headers({ 'Authorization': 'Token ' + this.authService.token });
  options = new RequestOptions({ headers: this.headers });

  constructor(private http: Http, private authService: AuthentificationService) {}
  
}
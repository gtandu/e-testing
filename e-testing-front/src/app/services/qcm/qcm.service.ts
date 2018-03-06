import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
<<<<<<< HEAD
import { Qcm } from '../../models/qcm';
import { Paths } from '../../models/paths';
import { Observable } from 'rxjs';
import { AuthentificationService } from '../authentification.service';
import 'rxjs/add/operator/map';

=======
import { HttpHeaders, HttpParams, HttpErrorResponse } from '@angular/common/http';
import { AuthentificationService } from '../authentification.service';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';


>>>>>>> cmapella
@Injectable()
export class QcmService {

  headers = new Headers({ 'Authorization': 'Token ' + this.authService.token });
  options = new RequestOptions({ headers: this.headers });

<<<<<<< HEAD
  constructor(private http: Http, private authService: AuthentificationService) { }

  getQcmById(qcmId: string): Observable<Qcm[]> {
    return this.http.get(this.authService.server + Paths.QCM + `/${qcmId}`, this.options)
      .map((response: Response) => response.json());
  }

}
=======
  constructor(private http: Http, private authService: AuthentificationService) {}
  
}
>>>>>>> cmapella

import { Injectable } from "@angular/core";
import { Http, Headers, RequestOptions, Response } from "@angular/http";
import { Qcm } from "../../models/qcm";
import { Paths } from "../../models/paths";
import { Observable } from "rxjs";
import { AuthentificationService } from "../authentification.service";
import "rxjs/add/operator/map";

@Injectable()
export class QcmService {
  headers = new Headers({ Authorization: "Token " + this.authService.token });
  options = new RequestOptions({ headers: this.headers });

  constructor(
    private http: Http,
    private authService: AuthentificationService
  ) {}

  getQcmById(qcmId: number): Observable<Qcm> {
    return this.http
      .get(this.authService.server + Paths.QCM + `/${qcmId}`, this.options)
      .map((response: Response) => response.json());
  }

  postQcm(qcm: Qcm): Observable<Qcm> {
    console.log(this.options);
    return this.http
      .post(this.authService.server + Paths.QCM + `/${qcm.id}`, this.options)
      .map((response: Response) => response.json());
  }
}

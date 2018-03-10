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

  currentQcm: Qcm = new Qcm();

  constructor(
    private http: Http,
    private authService: AuthentificationService
  ) {}


  getAllQcm(): Observable<Qcm[]>{
      return this.http
        .get(this.authService.server + Paths.QCM, this.options)
        .map((response: Response) => response.json());
  }

  getQcmById(qcmId: number): Observable<Qcm> {
    return this.http
      .get(this.authService.server + Paths.QCM + `/${qcmId}`, this.options)
      .map((response: Response) => response.json());
  }

  resetQcmById(qcmId: number): Observable<Qcm> {
    return this.http
      .patch(this.authService.server + Paths.QCM + `/${qcmId}`, null, this.options)
      .map((response: Response) => response.json());
  }

  savedQcm(qcm: Qcm): Observable<Qcm> {
    return this.http
      .put(
        this.authService.server + Paths.QCM + `/${qcm.id}`,
        qcm,
        this.options
      )
      .map((response: Response) => response.json());
  }

  correctQcm(qcm: Qcm): Observable<Qcm> {
    return this.http
      .post(
        this.authService.server + Paths.QCM + `/${qcm.id}`,
        qcm,
        this.options
      )
      .map((response: Response) => response.json());
  }
}

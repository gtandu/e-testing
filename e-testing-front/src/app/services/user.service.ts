import { Injectable } from "@angular/core";
import { Http, Headers, RequestOptions, Response } from "@angular/http";
import { AuthentificationService } from "./authentification.service";
import { Observable } from "rxjs";
import "rxjs/add/operator/map";
import { User } from "../models/user";

@Injectable()
export class UserService {
  headers = new Headers({ Authorization: "Token " + this.authService.token });
  options = new RequestOptions({ headers: this.headers });

  constructor(
    private http: Http,
    private authService: AuthentificationService
  ) {}

  getUserRoles(user: User): Observable<string[]> {
    return this.http
      .get(
        this.authService.server + "/account" + `/${user.mail}` + "/roles",
        this.options
      )
      .map((response: Response) => response.json());
  }
}

import { Injectable } from "@angular/core";
import { Router, CanActivate, ActivatedRouteSnapshot } from "@angular/router";
import { AuthentificationService } from "../services/authentification.service";
import { User } from "../models/user";
import * as decode from "jwt-decode";

@Injectable()
export class RoleGuardService implements CanActivate {
  constructor(public auth: AuthentificationService, public router: Router) {}

  canActivate(route: ActivatedRouteSnapshot): boolean {
    // this will be passed from the route config
    // on the data property
    const expectedRole = "Administrateur";
    const user: User = JSON.parse(localStorage.getItem("currentUser"));
    const token = user.token;
    let activate: boolean = false;

    // decode the token to get its payload
    const tokenPayload = decode(token);

    tokenPayload.role.forEach(element => {
      if (this.auth.isLogged || element.role === expectedRole) {
        activate = true;
      }
      else{
        this.router.navigate(["login"]);
        activate = false;
      }
    });
    return activate;
  }
}

import { Injectable } from "@angular/core";
import { CanActivate, Router } from "@angular/router";
import { JwtHelper } from 'angular2-jwt';
import { AuthentificationService } from "../services/authentification.service";

@Injectable()
export class AuthGuard implements CanActivate {
  constructor(public auth: AuthentificationService, private router: Router, public jwtHelper: JwtHelper) {}

  canActivate() {
    if (localStorage.getItem('currentUser')) {
      return true;
    }
    else{
      this.router.navigate(['/login']);
      return true;
    }
  }
}

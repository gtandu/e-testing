import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { AuthentificationService } from "../services/authentification.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {


  model: any = {};
  errorMsg: String;
  loader: boolean = false;
  constructor(private router: Router, private authService: AuthentificationService) { }

  ngOnInit() {
    this.authService.logout();
  }

  login(){
    console.log(this.model);

    this.authService.login(this.model.email, this.model.password).subscribe(
      result => {
        if (result === true) {
          // login successful
          console.log("TOKEN OK REDIRECT APP");
          this.errorMsg = null;
          this.loader = true;
          this.router.navigate(['/localhost/home']);
        } else {
          // login failed
          console.log("ERROR LOGIN");
        }
      },
      error => {
        if(error.status === 401){
          this.errorMsg = "L'email ou le mot de passe est incorrect !"
        }
        else{
          this.errorMsg = "Une erreur technique est survenue !"
        }
      });
  }

}

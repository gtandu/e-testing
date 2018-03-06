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
    document.body.classList.add('login-background');
  }

  login(){
    console.log(this.model);

    this.authService.login(this.model.email, this.model.password).subscribe(
      result => {
        if (result === true) {
          // login successful
          this.errorMsg = null;
          this.loader = true;
<<<<<<< HEAD
          document.body.classList.remove('login-background');
=======
          document.body.classList.remove('login-background')
>>>>>>> cmapella
          this.router.navigate(['/home']);
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
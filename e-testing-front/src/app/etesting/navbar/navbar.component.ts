import { Component, OnInit } from "@angular/core";
import { AuthentificationService } from "../../services/authentification.service";
import { User } from "../../models/user";

@Component({
  selector: "app-navbar",
  templateUrl: "./navbar.component.html",
  styleUrls: ["./navbar.component.css"]
})
export class NavbarComponent implements OnInit {
  public isAdmin: boolean;
  constructor(private authService: AuthentificationService) {}

  ngOnInit() {
    this.isAdmin = this.authService.user.isAdmin;
  }

  logout() {
    this.authService.logout();
  }
}

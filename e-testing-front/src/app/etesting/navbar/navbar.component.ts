import { Component, OnInit, AfterViewChecked } from "@angular/core";
import { AuthentificationService } from "../../services/authentification.service";
import { User } from "../../models/user";

@Component({
  selector: "app-navbar",
  templateUrl: "./navbar.component.html",
  styleUrls: ["./navbar.component.css"]
})
export class NavbarComponent implements OnInit {
  public isAdmin: boolean = false;
  constructor(private authService: AuthentificationService) {}

  ngOnInit() {
    const user: User = JSON.parse(localStorage.getItem("currentUser"));
    this.isAdmin = user.isAdmin;
  }

  logout() {
    this.authService.logout();
  }
}

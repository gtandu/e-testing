import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { AuthentificationService } from "../services/authentification.service";
import { QcmService } from "../services/qcm/qcm.service";
import { AdminComponent } from "./admin/admin.component";
import { ETestingRoutingModule } from "./etesting-routing.module";
import { EditQcmComponent } from "./admin/edit-qcm/edit-qcm.component";
import { HomeComponent } from "./home/home.component";
import { NavbarComponent } from "./navbar/navbar.component";
import { QcmComponent } from "./qcm/qcm.component";
import { FileUploadModule } from "ng2-file-upload";
import { FileUploadComponent } from "./admin/fileupload/fileupload.component";
import { FormsModule } from "@angular/forms";
import { JwtHelper } from "angular2-jwt";
import { RoleGuardService } from "../guards/role-guard.service";
import { AuthGuard } from "../guards/auth-guard.service";
import { ListQcmComponent } from "./list-qcm/list-qcm.component";

@NgModule({
  imports: [CommonModule, FormsModule, ETestingRoutingModule, FileUploadModule],
  declarations: [
    NavbarComponent,
    QcmComponent,
    ListQcmComponent,
    HomeComponent,
    AdminComponent,
    EditQcmComponent,
    FileUploadComponent
  ],
  providers: [JwtHelper, AuthGuard, RoleGuardService, QcmService]
})
export class EtestingModule {}

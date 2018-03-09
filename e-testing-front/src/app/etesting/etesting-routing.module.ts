import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { HomeComponent } from "./home/home.component";
import { AuthGuard } from "../guards/auth-guard.service";
import { FileUploadComponent } from "./admin/fileupload/fileupload.component";
import { AdminComponent } from "./admin/admin.component";
import { RoleGuardService } from "../guards/role-guard.service";
import { QcmComponent } from "./qcm/qcm.component";
import { CorrectQcmComponent } from "./correct-qcm/correct-qcm.component";

const etestingRoutes: Routes = [
  {
    path: 'etesting',
    canActivate: [AuthGuard],
    children: [
      { path: 'home', component: HomeComponent },
      {
        path: "qcm/:id",
        component: QcmComponent
      },
      {
        path: "qcm/:id/answer",
        component: CorrectQcmComponent
      },
      {
        path: "admin",
        canActivate: [RoleGuardService],
        component: AdminComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(etestingRoutes)],
  exports: [RouterModule]
})
export class ETestingRoutingModule {}

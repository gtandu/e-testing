import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './etesting/home/home.component';
import { FileUploadComponent } from './etesting/admin/fileupload/fileupload.component';
import { QcmComponent } from './etesting/qcm/qcm.component';

// routes
const appRoutes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'home', component: HomeComponent },
  { path: 'editqcm', component: FileUploadComponent },
  { path: 'qcm', component: QcmComponent },
  { path: '', redirectTo: 'login', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { FileUploadModule } from 'ng2-file-upload';
import { AuthentificationService } from './services/authentification.service';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './etesting/home/home.component';
import { NavbarComponent } from './etesting/navbar/navbar.component';
import { QcmComponent } from './etesting/qcm/qcm.component';
import * as $ from 'jquery';
import { EditQcmComponent } from './etesting/edit-qcm/edit-qcm.component';
import { FileUploadComponent } from './etesting/fileupload/fileupload.component';
import { QcmService } from './services/qcm/qcm.service';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    EditQcmComponent,
    NavbarComponent,
    QcmComponent,
    FileUploadComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    FormsModule,
    AppRoutingModule,
    FileUploadModule
  ],
  providers: [AuthentificationService,QcmService],
  bootstrap: [AppComponent]
})
export class AppModule { }
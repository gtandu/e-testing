import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { AuthentificationService } from './services/authentification.service';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import * as $ from 'jquery';
import { QcmService } from './services/qcm/qcm.service';
import { UserService } from './services/user.service';
import { EtestingModule } from './etesting/etesting.module';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    FormsModule,
    EtestingModule,
    AppRoutingModule
  ],
  providers: [AuthentificationService],
  bootstrap: [AppComponent]
})
export class AppModule { }
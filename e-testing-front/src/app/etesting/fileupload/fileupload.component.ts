import { Component, OnInit } from '@angular/core';
import { AuthentificationService } from '../../services/authentification.service';
import { FileUploader, FileUploaderOptions, FileItem, ParsedResponseHeaders } from 'ng2-file-upload';
import { Qcm } from '../../models/qcm';

@Component({
  selector: 'app-fileupload',
  templateUrl: './fileupload.component.html',
  styleUrls: ['./fileupload.component.css']
})
export class FileUploadComponent implements OnInit {

  qcmUploaded: Qcm;
  url: string;
  authToken: string;
  loading = false;

  public hasBaseDropZoneOver = false;
  public hasAnotherDropZoneOver = false;

  public uploader;

  options: FileUploaderOptions = {
    url: this.authService.server + '/convertToQcm',
    authToken: 'Token ' + this.authService.token,
    authTokenHeader: 'Authorization',
    autoUpload: true
  };


  constructor(private authService: AuthentificationService) { }

  ngOnInit() {
    this.uploader = new FileUploader(this.options);
    this.uploader.onSuccessItem = (item, response, status, headers) => this.onSuccessItem(item, response, status, headers);
  }

  onSuccessItem(item: FileItem, response: string, status: number, headers: ParsedResponseHeaders): any {
    console.log(JSON.parse(response));
    this.qcmUploaded = JSON.parse(response);
    this.loading = true;
  }

  public fileOverBase(e: any): void {
    this.hasBaseDropZoneOver = e;
  }

  public fileOverAnother(e: any): void {
    this.hasAnotherDropZoneOver = e;
  }

}

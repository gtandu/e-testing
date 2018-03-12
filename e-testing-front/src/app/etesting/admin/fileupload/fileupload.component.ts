import { Component, OnInit } from "@angular/core";
import { AuthentificationService } from "../../../services/authentification.service";
import {
  FileUploader,
  FileUploaderOptions,
  FileItem,
  ParsedResponseHeaders
} from "ng2-file-upload";
import { Qcm } from "../../../models/qcm";
import { Paths } from "../../../models/paths";
import { ActivatedRoute } from "@angular/router";

@Component({
  selector: "app-fileupload",
  templateUrl: "./fileupload.component.html",
  styleUrls: ["./fileupload.component.css"]
})
export class FileUploadComponent implements OnInit {
  qcmUploaded: Qcm;
  url: string;
  authToken: string;
  loading = false;
  editMode: boolean;

  public hasBaseDropZoneOver = false;
  public hasAnotherDropZoneOver = false;

  public uploader;

  options: FileUploaderOptions = {
    url: this.authService.server + Paths.QCM_XML,
    authToken: "Token " + this.authService.token,
    authTokenHeader: "Authorization",
    autoUpload: true
  };

  constructor(
    private authService: AuthentificationService,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.uploader = new FileUploader(this.options);
    this.uploader.onSuccessItem = (item, response, status, headers) =>
      this.onSuccessItem(item, response, status, headers);
    this.route.params.subscribe(params => {
      console.log(params.id);
      if (params.id !== undefined) {
        this.editMode = true;
      }
    });
  }

  onSuccessItem(
    item: FileItem,
    response: string,
    status: number,
    headers: ParsedResponseHeaders
  ): any {
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

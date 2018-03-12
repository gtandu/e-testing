import { Component, OnInit } from "@angular/core";
import { Qcm } from "../../models/qcm";
import { QcmService } from "../../services/qcm/qcm.service";
import { Router } from "@angular/router";
import { Paths } from "../../models/paths";
import { User } from "../../models/user";

@Component({
  selector: "app-list-qcm",
  templateUrl: "./list-qcm.component.html",
  styleUrls: ["./list-qcm.component.css"]
})
export class ListQcmComponent implements OnInit {
  qcms: Qcm[];
  qcm: Qcm;
  id: number;

  public isAdmin: boolean = false;

  constructor(private qcmService: QcmService, private router: Router) {}

  ngOnInit() {
    this.qcmService.getAllQcm().subscribe(qcm => {
      this.qcms = qcm;
    });
    const user: User = JSON.parse(localStorage.getItem("currentUser"));
    this.isAdmin = user.isAdmin;
  }

  takeQcm(id) {
    this.router.navigate(["etesting/" + Paths.QCM + `/${id}`]);
  }

  editQcm(qcmId : number){
    this.router.navigate(["etesting/" + Paths.EDIT_QCM + `/${qcmId}`]);
  }

  exportQcm(qcmId: number) {
    const nameOfFileToDownload = "qcm.xml";
    this.qcmService.exportQcm(qcmId).subscribe(xmlQcmFromDb => {
      const blob = new Blob([xmlQcmFromDb["_body"]], { type: "text/xml" });
      let a = document.createElement("a");
      a.href = URL.createObjectURL(blob);
      a.download = nameOfFileToDownload;
      document.body.appendChild(a);
      a.click();
      document.body.removeChild(a);
    });
  }
}

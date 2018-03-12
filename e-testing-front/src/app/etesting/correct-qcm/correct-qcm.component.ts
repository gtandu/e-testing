import { Component, OnInit, AfterViewChecked } from "@angular/core";
import { QcmService } from "../../services/qcm/qcm.service";
import { RouterLink, ActivatedRoute, Router } from "@angular/router";
import { Qcm } from "../../models/qcm";

@Component({
  selector: "app-correct-qcm",
  templateUrl: "./correct-qcm.component.html",
  styleUrls: ["./correct-qcm.component.css"]
})
export class CorrectQcmComponent implements OnInit {
  qcm: Qcm = new Qcm();
  id: number;

  constructor(
    private qcmService: QcmService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.id = params.id;
    });
    // this.qcmService.getQcmById(this.id).subscribe(qcmFromDb => {
    //   this.qcm = qcmFromDb;
    // });
    this.qcm = this.qcmService.currentQcm;
    console.log(this.qcm);
  }
}

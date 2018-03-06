import { Component, OnInit } from '@angular/core';
import { QcmService } from '../../services/qcm/qcm.service';
import { Qcm } from '../../models/qcm';

@Component({
  selector: 'app-qcm',
  templateUrl: './qcm.component.html',
  styleUrls: ['./qcm.component.css']
})
export class QcmComponent implements OnInit {

  qcm : Qcm;

  constructor(private qcmService : QcmService) { }

  ngOnInit() {
    this.qcmService.getQcmById(1).subscribe(qcmFromDb => {
      this.qcm=qcmFromDb;
    });
    console.log(this.qcm);
  }

}
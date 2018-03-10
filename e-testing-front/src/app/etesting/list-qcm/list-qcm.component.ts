import { Component, OnInit } from '@angular/core';
import { Qcm } from '../../models/qcm';
import { QcmService } from '../../services/qcm/qcm.service';

@Component({
  selector: 'app-list-qcm',
  templateUrl: './list-qcm.component.html',
  styleUrls: ['./list-qcm.component.css']
})
export class ListQcmComponent implements OnInit {

  qcms : Qcm[];

  constructor(private qcmService: QcmService) { }

  ngOnInit() {
    this.qcmService.getAllQcm().subscribe(qcm => {
      this.qcms = qcm;
    });
  }

}

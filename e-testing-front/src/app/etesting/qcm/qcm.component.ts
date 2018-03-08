import { Component, OnInit } from '@angular/core';
import { QcmService } from '../../services/qcm/qcm.service';
import { Qcm } from '../../models/qcm';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-qcm',
  templateUrl: './qcm.component.html',
  styleUrls: ['./qcm.component.css']
})
export class QcmComponent implements OnInit {

  qcm: Qcm = new Qcm();
  id: number;

  constructor(private qcmService: QcmService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params.subscribe( params => {this.id = params.id; });
    this.qcmService.getQcmById(this.id).subscribe(qcmFromDb => {
      this.qcm = qcmFromDb;
    });
  }

}
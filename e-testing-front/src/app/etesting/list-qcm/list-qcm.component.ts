import { Component, OnInit } from '@angular/core';
import { Qcm } from '../../models/qcm';
import { QcmService } from '../../services/qcm/qcm.service';
import { Router } from '@angular/router';
import { Paths } from '../../models/paths';

@Component({
  selector: 'app-list-qcm',
  templateUrl: './list-qcm.component.html',
  styleUrls: ['./list-qcm.component.css']
})
export class ListQcmComponent implements OnInit {

  qcms : Qcm[];
  qcm : Qcm;
  id : number;

  constructor(private qcmService: QcmService, private router: Router) { }

  ngOnInit() {
    this.qcmService.getAllQcm().subscribe(qcm => {
      this.qcms = qcm;
    });
  }

  takeQcm(id){
    console.log(id);
    this.router.navigate(['etesting/' + Paths.QCM + `/${id}`]);
  }

}

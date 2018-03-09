import { Component, OnInit } from '@angular/core';
import { QcmService } from '../../services/qcm/qcm.service';
import { Qcm } from '../../models/qcm';
import { ActivatedRoute, Router } from '@angular/router';
import { Paths } from '../../models/paths';

@Component({
  selector: 'app-qcm',
  templateUrl: './qcm.component.html',
  styleUrls: ['./qcm.component.css']
})
export class QcmComponent implements OnInit {

  qcm: Qcm = new Qcm();
  id: number;

  constructor(private qcmService: QcmService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.route.params.subscribe( params => {this.id = params.id; });
    this.qcmService.getQcmById(this.id).subscribe(qcmFromDb => {
      this.qcm = qcmFromDb;
    });
  }

  submitQcm(){
    this.qcmService.correctQcm(this.qcm).subscribe( qcmCorrige => {
      console.log(qcmCorrige);
    });
    this.router.navigate(['etesting/' + Paths.QCM + `/${this.qcm.id}` + '/answer']);
  }

}
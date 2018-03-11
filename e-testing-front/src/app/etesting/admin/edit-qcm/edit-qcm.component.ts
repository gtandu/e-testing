import { Component, OnInit, OnChanges, SimpleChanges, SimpleChange, Input, Output, EventEmitter} from '@angular/core';
import { Qcm } from '../../../models/qcm';
import { QcmService } from '../../../services/qcm/qcm.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-edit-qcm',
  templateUrl: './edit-qcm.component.html',
  styleUrls: ['./edit-qcm.component.css']
})
export class EditQcmComponent implements OnInit, OnChanges {

  @Input() qcmUploaded: Qcm;

  @Output() notify: EventEmitter<string> = new EventEmitter<string>();

  constructor(private qcmService: QcmService, private router: Router) { }

  ngOnInit() {
  }

  ngOnChanges(changes: SimpleChanges) {
  }

  saveQcm() {
    this.qcmService.savedQcm(this.qcmUploaded).subscribe(qcmSaved => {
      this.qcmUploaded = qcmSaved;
    } );
    this.router.navigate(['etesting/home']);
  }

  addQuestionReponse(){
    this.qcmService.addQuestionReponse(this.qcmUploaded).subscribe(qcmSaved =>{
      this.qcmUploaded = qcmSaved;
    });
  }

  deleteQuestionReponse(questionReponseId: number){
    this.qcmService.deleteQuestionReponse(this.qcmUploaded, questionReponseId).subscribe(qcmSaved =>{
      this.qcmUploaded = qcmSaved;
    });
  }

  addReponse(questionReponseId: number){
    this.qcmService.addReponse(this.qcmUploaded,questionReponseId).subscribe(qcmSaved =>{
      this.qcmUploaded = qcmSaved;
    });
  }

  deleteReponse(questionReponseId: number,reponseId: number){
    this.qcmService.deleteReponse(this.qcmUploaded, questionReponseId, reponseId).subscribe(qcmSaved =>{
      this.qcmUploaded = qcmSaved;
    });
  }
}

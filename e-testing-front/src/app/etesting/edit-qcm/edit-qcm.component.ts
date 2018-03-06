import { Component, OnInit, OnChanges, SimpleChanges, SimpleChange, Input, Output, EventEmitter} from '@angular/core';
import { Qcm } from '../../models/qcm';
import { QcmService } from '../../services/qcm/qcm.service';

@Component({
  selector: 'app-edit-qcm',
  templateUrl: './edit-qcm.component.html',
  styleUrls: ['./edit-qcm.component.css']
})
export class EditQcmComponent implements OnInit, OnChanges {

  @Input() qcmUploaded: Qcm;

  @Output() notify: EventEmitter<string> = new EventEmitter<string>();

  constructor(private qcmService: QcmService) { }

  ngOnInit() {
  }

  ngOnChanges(changes: SimpleChanges) {
    console.log(this.qcmUploaded);
  }
  saveQcm(){
    console.log("Submit");
    console.log(this.qcmUploaded);
    this.qcmService.postQcm(this.qcmUploaded).subscribe(qcmSaved => {
      this.qcmUploaded = qcmSaved;
    } );
  }

}

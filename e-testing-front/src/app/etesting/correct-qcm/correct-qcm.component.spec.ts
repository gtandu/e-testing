import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CorrectQcmComponent } from './correct-qcm.component';

describe('CorrectQcmComponent', () => {
  let component: CorrectQcmComponent;
  let fixture: ComponentFixture<CorrectQcmComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CorrectQcmComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CorrectQcmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

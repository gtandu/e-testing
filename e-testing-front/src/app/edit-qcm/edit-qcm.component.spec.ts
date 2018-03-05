import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditQcmComponent } from './edit-qcm.component';

describe('EditQcmComponent', () => {
  let component: EditQcmComponent;
  let fixture: ComponentFixture<EditQcmComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditQcmComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditQcmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

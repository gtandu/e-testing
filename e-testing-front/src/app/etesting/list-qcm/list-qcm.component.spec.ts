import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListQcmComponent } from './list-qcm.component';

describe('ListQcmComponent', () => {
  let component: ListQcmComponent;
  let fixture: ComponentFixture<ListQcmComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListQcmComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListQcmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

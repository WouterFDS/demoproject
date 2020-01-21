import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OpdrachtDeleteComponent } from './opdracht.delete.component';

describe('Opdracht.DeleteComponent', () => {
  let component: OpdrachtDeleteComponent;
  let fixture: ComponentFixture<OpdrachtDeleteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OpdrachtDeleteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OpdrachtDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

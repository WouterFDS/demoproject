import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OpdrachtEditComponent } from './opdracht.edit.component';

describe('OpdrachtEditComponent', () => {
  let component: OpdrachtEditComponent;
  let fixture: ComponentFixture<OpdrachtEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OpdrachtEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OpdrachtEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

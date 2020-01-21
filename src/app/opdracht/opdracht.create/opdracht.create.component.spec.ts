import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OpdrachtCreateComponent } from './opdracht.create.component';

describe('Opdracht.CreateComponent', () => {
  let component: OpdrachtCreateComponent;
  let fixture: ComponentFixture<OpdrachtCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OpdrachtCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OpdrachtCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

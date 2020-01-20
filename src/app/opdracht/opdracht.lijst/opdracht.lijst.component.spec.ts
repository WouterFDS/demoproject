import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OpdrachtLijstComponent } from './opdracht.lijst.component';

describe('Opdracht.LijstComponent', () => {
  let component: OpdrachtLijstComponent;
  let fixture: ComponentFixture<OpdrachtLijstComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OpdrachtLijstComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OpdrachtLijstComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OpdrachtLijstDetailsComponent } from './opdracht.lijst-details.component';

describe('Opdracht.LijstDetailsComponent', () => {
  let component: OpdrachtLijstDetailsComponent;
  let fixture: ComponentFixture<OpdrachtLijstDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OpdrachtLijstDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OpdrachtLijstDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

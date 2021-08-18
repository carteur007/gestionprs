import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LegionHomeComponent } from './legion-home.component';

describe('LegionHomeComponent', () => {
  let component: LegionHomeComponent;
  let fixture: ComponentFixture<LegionHomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LegionHomeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LegionHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

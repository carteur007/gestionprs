import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LegionAddComponent } from './legion-add.component';

describe('LegionAddComponent', () => {
  let component: LegionAddComponent;
  let fixture: ComponentFixture<LegionAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LegionAddComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LegionAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

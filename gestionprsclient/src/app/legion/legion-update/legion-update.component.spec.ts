import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LegionUpdateComponent } from './legion-update.component';

describe('LegionUpdateComponent', () => {
  let component: LegionUpdateComponent;
  let fixture: ComponentFixture<LegionUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LegionUpdateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LegionUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

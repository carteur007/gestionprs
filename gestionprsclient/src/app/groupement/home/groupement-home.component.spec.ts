import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GroupementHomeComponent } from './groupement-home.component';

describe('GroupementHomeComponent', () => {
  let component: GroupementHomeComponent;
  let fixture: ComponentFixture<GroupementHomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GroupementHomeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GroupementHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

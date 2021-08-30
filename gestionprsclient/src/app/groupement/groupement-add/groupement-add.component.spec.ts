import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GroupementAddComponent } from './groupement-add.component';

describe('GroupementAddComponent', () => {
  let component: GroupementAddComponent;
  let fixture: ComponentFixture<GroupementAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GroupementAddComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GroupementAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
